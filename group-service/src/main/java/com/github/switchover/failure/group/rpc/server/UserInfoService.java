package com.github.switchover.failure.group.rpc.server;

import com.github.switchover.failure.common.rpc.UserInfoGrpc;
import com.github.switchover.failure.common.rpc.UserInfoOuterClass;
import com.github.switchover.failure.group.model.Messenger;
import com.github.switchover.failure.group.model.User;
import com.github.switchover.failure.group.repository.jpa.GroupAndUsersRepository;
import com.github.switchover.failure.group.repository.jpa.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import javax.transaction.Transactional;

@GrpcService
@Profile("grpc")
public class UserInfoService extends UserInfoGrpc.UserInfoImplBase {
    private final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    private final UserRepository userRepository;
    private final GroupAndUsersRepository groupAndUsersRepository;

    public UserInfoService(
        UserRepository userRepository,
        GroupAndUsersRepository groupAndUsersRepository) {
        this.userRepository = userRepository;
        this.groupAndUsersRepository = groupAndUsersRepository;
    }

    @Transactional
    @Override
    public void getUserList(UserInfoOuterClass.NicknameOrTeam request, StreamObserver<UserInfoOuterClass.UserList> responseObserver) {
        UserInfoOuterClass.UserList.Builder listBuilder = UserInfoOuterClass.UserList.newBuilder();
        UserInfoOuterClass.User.Builder userBuilder = UserInfoOuterClass.User.newBuilder();

        if (request.getNameList().contains("@all")) {
            addAllUserInfo(listBuilder, userBuilder);
        } else {
            UserDuplicationChecker checker = new UserDuplicationChecker();

            for (String name : request.getNameList()) {
                if (name.startsWith("@@")) {
                    addGroupUserInfo(listBuilder, userBuilder, checker, name);
                } else if (name.startsWith("@")) {
                    addUserOnlyInfo(listBuilder, userBuilder, checker, name);
                } else {
                    returnErrorWith("Target name not validated : " + name, responseObserver);
                    return;
                }
            }
        }

        responseObserver.onNext(listBuilder.build());
        responseObserver.onCompleted();
    }

    private void addAllUserInfo(UserInfoOuterClass.UserList.Builder listBuilder, UserInfoOuterClass.User.Builder userBuilder) {
        for (User user : userRepository.findAll()) {
            for (Messenger messenger : user.getMessengers()) {
                listBuilder.addUser(userBuilder
                    .setNickname(user.getNickName())
                    .setServiceType(messenger.getServiceType())
                    .setToken(messenger.getToken())
                    .setTarget(messenger.getTarget())
                    .build());
            }
        }
    }

    private void addGroupUserInfo(UserInfoOuterClass.UserList.Builder listBuilder, UserInfoOuterClass.User.Builder userBuilder, UserDuplicationChecker checker, String name) {
        name = name.substring(2);
        groupAndUsersRepository.findByGroupName(name).ifPresent(groupAndUsers -> {
            for (User user : groupAndUsers.getUserList()) {
                addMessengerInfo(checker, user, listBuilder, userBuilder);
            }
        });
    }

    private void addUserOnlyInfo(UserInfoOuterClass.UserList.Builder listBuilder, UserInfoOuterClass.User.Builder userBuilder, UserDuplicationChecker checker, String name) {
        name = name.substring(1);
        userRepository.findByNickName(name).ifPresent(user -> {
            addMessengerInfo(checker, user, listBuilder, userBuilder);
        });
    }

    private void addMessengerInfo(UserDuplicationChecker checker, User user, UserInfoOuterClass.UserList.Builder builder, UserInfoOuterClass.User.Builder userBuilder) {
        if (checker.notContains(user.getNickName())) {
            for (Messenger messenger : user.getMessengers()) {
                builder.addUser(userBuilder
                    .setNickname(user.getNickName())
                    .setServiceType(messenger.getServiceType())
                    .setToken(messenger.getToken())
                    .setTarget(messenger.getTarget())
                    .build());
            }
            checker.addNickname(user.getNickName());
        }
    }

    private void returnErrorWith(String errorMessage, StreamObserver<UserInfoOuterClass.UserList> responseObserver) {
        logger.error(errorMessage);
        responseObserver.onError(new IllegalArgumentException(errorMessage));
    }
}
