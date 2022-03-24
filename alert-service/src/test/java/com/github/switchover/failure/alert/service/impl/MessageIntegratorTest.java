package com.github.switchover.failure.alert.service.impl;

import com.github.switchover.failure.common.dto.IntegratedAlertMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MessageIntegratorTest {
    private MessageIntegrator tester;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenNoMessage_whenGetIntegratedMessage_thenReturnEmptyList() {
        // given
        tester = new MessageIntegrator(10);

        // when // then
        assertThat(tester.getIntegratedMessage(), is(empty()));
    }

    @Test
    void givenLessMessageSize_whenGetIntegratedMessage_thenReturnPlusOneIntegratedMessageList() {
        // given
        tester = new MessageIntegrator(3);

        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");

        // when
        List<IntegratedAlertMessage> list = tester.getIntegratedMessage();

        // then
        assertThat(list.size(), is(1)); // integrated message list size
        assertThat(list.get(0).getMessengerInfoList().size(), is(2));   // individual messenger info
    }

    @Test
    void givenMatchedMessageSize_whenGetIntegratedMessage_thenReturnMatchedIntegratedMessageList() {
        // given
        tester = new MessageIntegrator(2);

        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");

        // when
        List<IntegratedAlertMessage> list = tester.getIntegratedMessage();

        // then
        assertThat(list.size(), is(2)); // integrated message list size
        assertThat(list.get(0).getMessengerInfoList().size(), is(2));   // individual messenger info
        assertThat(list.get(1).getMessengerInfoList().size(), is(2));   // individual messenger info
    }

    @Test
    void givenMoreMessageSize_whenGetIntegratedMessage_thenReturnPlusOneIntegratedMessageList() {
        // given
        tester = new MessageIntegrator(2);

        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");
        tester.addMessengerInfo("slack", "userToken", "channel");

        // when
        List<IntegratedAlertMessage> list = tester.getIntegratedMessage();

        // then
        assertThat(list.size(), is(2)); // integrated message list size
        assertThat(list.get(0).getMessengerInfoList().size(), is(2));   // individual messenger info
        assertThat(list.get(1).getMessengerInfoList().size(), is(1));   // individual messenger info
    }
}
