package com.github.switchover.failure.group.rest;

import com.github.switchover.failure.common.rest.advice.ResourceAlreadyExistsException;
import com.github.switchover.failure.common.rest.advice.ResourceNotFoundException;
import com.github.switchover.failure.group.model.Group;
import com.github.switchover.failure.group.model.GroupAndUsers;
import com.github.switchover.failure.group.model.User;
import com.github.switchover.failure.group.repository.jpa.GroupAndUsersRepository;
import com.github.switchover.failure.group.repository.jpa.GroupRepository;
import com.github.switchover.failure.group.repository.jpa.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private final GroupRepository groupRepository;
    private final GroupAndUsersRepository groupAndUsersRepository;
    private final UserRepository userRepository;

    public GroupController(
        GroupRepository groupRepository,
        GroupAndUsersRepository groupAndUsersRepository,
        UserRepository userRepository
    ) {
        this.groupRepository = groupRepository;
        this.groupAndUsersRepository = groupAndUsersRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Group> selectGroupList() {
        return groupRepository.findAll();
    }

    @GetMapping("/{id}")
    public Group selectGroup(@PathVariable int id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        Optional<Group> byGroupName = groupRepository.findByGroupName(group.getGroupName());
        if (byGroupName.isPresent()) {
            throw new ResourceAlreadyExistsException("Group name is already exists.", group.getGroupName());
        }
        if (group.getId() != null) {
            throw new ResourceAlreadyExistsException("Call without group id.", group.getId());
        }

        return new ResponseEntity(groupRepository.save(group), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable int id, @RequestBody Group group) {
        Optional<Group> byId = groupRepository.findById(id);

        if (byId.isPresent()) {
            group.setId(id);
            return groupRepository.save(group);
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable int id) {
        Optional<GroupAndUsers> byId = groupAndUsersRepository.findById(id);

        if (byId.isPresent()) {
            groupAndUsersRepository.delete(byId.get());
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    @GetMapping("/{id}/users")
    public GroupAndUsers selectGroupUserList(@PathVariable int id) {
        Optional<GroupAndUsers> byId = groupAndUsersRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<GroupAndUsers> addGroupUser(@PathVariable int id, @RequestBody User user) {
        checkAndUpdateUserInfo(user);

        Optional<GroupAndUsers> groupAndUsers = groupAndUsersRepository.findById(id);

        if (groupAndUsers.isPresent()) {
            groupAndUsers.get().getUserList().add(user);

            groupAndUsersRepository.save(groupAndUsers.get());

            return new ResponseEntity(groupAndUsers.get(), HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    @DeleteMapping("/{id}/users/{nickname}")
    public void deleteGroupUser(@PathVariable int id, @RequestBody User user) {
        checkAndUpdateUserInfo(user);

        Optional<GroupAndUsers> groupAndUsers = groupAndUsersRepository.findById(id);

        if (groupAndUsers.isPresent()) {
            groupAndUsers.get().getUserList().remove(user);

            groupAndUsersRepository.save(groupAndUsers.get());
        } else {
            throw new ResourceNotFoundException("There is no group.", id);
        }
    }

    private void checkAndUpdateUserInfo(User user) {
        Optional<User> byNickName = userRepository.findByNickName(user.getNickName());

        if (byNickName.isPresent()) {
            user.setId(byNickName.get().getId());
        } else {
            throw new ResourceNotFoundException("There is no user.", user.getNickName());
        }
    }
}
