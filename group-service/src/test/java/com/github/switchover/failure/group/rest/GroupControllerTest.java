package com.github.switchover.failure.group.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.switchover.failure.group.model.Group;
import com.github.switchover.failure.group.repository.jpa.ApplicationTestConfig;
import com.github.switchover.failure.group.rest.advice.ExceptionControllerAdvice;
import com.github.switchover.failure.group.rpc.server.UserInfoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@ActiveProfiles({"hsqldb", "spring-data-jpa"})
class GroupControllerTest {
    @Autowired
    private GroupController groupController;

    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(groupController)
            .setControllerAdvice(new ExceptionControllerAdvice())
            .build();
    }

    @AfterEach
    void tearDown() {
        // JdbcTestUtils.deleteFromTables(jdbcTemplate, "groups", "groups_users"); // w/o identity reset
        jdbcTemplate.execute("TRUNCATE TABLE groups RESTART IDENTITY AND COMMIT NO CHECK");
        jdbcTemplate.execute("TRUNCATE TABLE groups_users RESTART IDENTITY AND COMMIT NO CHECK");
    }

    private void givenTwoGroup() throws Exception {
        Group group = new Group();
        group.setGroupName("team1");
        ObjectMapper mapper = new ObjectMapper();
        String postAsJSON = mapper.writeValueAsString(group);

        this.mockMvc.perform(post("/v1/groups")
                .content(postAsJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated());

        // second group
        group.setGroupName("team2");
        postAsJSON = mapper.writeValueAsString(group);

        this.mockMvc.perform(post("/v1/groups")
                .content(postAsJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated());
    }

    @Test
    void givenEmptyGroup_whenSelectGroupList_thenReturnEmptyList() throws Exception {
        // given

        // when // then
        this.mockMvc.perform(get("/v1/groups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("[]"));
    }

    @Test
    void givenTwoGroup_whenSelectGroupList_thenReturnTwoList() throws Exception {
        // given
        givenTwoGroup();

        // when // then
        this.mockMvc.perform(get("/v1/groups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[1].id").value(1))
            .andExpect(jsonPath("$.[1].groupName").value("team2"));
    }

    @Test
    void givenTwoGroup_whenUpdateGroup_thenReturnUpdatedGroup() throws Exception {
        // given
        givenTwoGroup();

        Group group = new Group();
        group.setId(1);
        group.setGroupName("new-team");
        ObjectMapper mapper = new ObjectMapper();
        String postAsJSON = mapper.writeValueAsString(group);

        // when // then
        this.mockMvc.perform(put("/v1/groups/1")
                .content(postAsJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.groupName").value("new-team"));
    }

    @Test
    void givenTwoGroup_whenDeleteGroup_thenReturnOneGroup() throws Exception {
        // given
        givenTwoGroup();

        // when
        this.mockMvc.perform(delete("/v1/groups/0")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        // then
        this.mockMvc.perform(get("/v1/groups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].groupName").value("team2"));
    }
}
