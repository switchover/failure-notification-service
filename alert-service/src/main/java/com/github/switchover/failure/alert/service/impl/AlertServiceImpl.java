package com.github.switchover.failure.alert.service.impl;

import com.github.switchover.failure.alert.dto.AlertRequest;
import com.github.switchover.failure.alert.message.MessageSender;
import com.github.switchover.failure.alert.service.AlertService;
import com.github.switchover.failure.common.dto.IntegratedAlertMessage;
import com.github.switchover.failure.common.rpc.UserInfoGrpc;
import com.github.switchover.failure.common.rpc.UserInfoOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {
    private final Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);

    @Value("${rpc.server.host}")
    private String rpcServerHost;
    @Value("${rpc.server.port}")
    private int rpcServerPort;
    @Value("${queue.message.size}")
    private int messageSize;

    private final MessageSender messageSender;

    public AlertServiceImpl(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public int sendAlerts(AlertRequest request) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(rpcServerHost, rpcServerPort)
            .usePlaintext()
            .build();

        UserInfoGrpc.UserInfoBlockingStub stub = UserInfoGrpc.newBlockingStub(channel);

        UserInfoOuterClass.UserList response = stub.getUserList(
            UserInfoOuterClass.NicknameOrTeam.newBuilder()
                .addAllName(request.getTarget())
                .build()
        );

        MessageIntegrator messageIntegrator = new MessageIntegrator(messageSize);
        messageIntegrator.setSeverity(request.getSeverity());
        messageIntegrator.setMessage(request.getMessage());
        for (UserInfoOuterClass.User user : response.getUserList()) {
            logger.debug("User info : {} / {} / {}", user.getNickname(), user.getToken(), user.getTarget());
            messageIntegrator.addMessengerInfo(user.getServiceType(), user.getToken(), user.getTarget());
        }

        channel.shutdown();

        for (IntegratedAlertMessage message : messageIntegrator.getIntegratedMessage()) {
            messageSender.sendMessage(message);
        }

        return response.getUserList().size();
    }
}
