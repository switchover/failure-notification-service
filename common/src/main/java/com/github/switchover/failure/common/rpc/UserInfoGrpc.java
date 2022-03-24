package com.github.switchover.failure.common.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: UserInfo.proto")
public final class UserInfoGrpc {

  private UserInfoGrpc() {}

  public static final String SERVICE_NAME = "com.github.switchover.failure.common.rpc.UserInfo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam,
      com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> getGetUserListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserList",
      requestType = com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam.class,
      responseType = com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam,
      com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> getGetUserListMethod() {
    io.grpc.MethodDescriptor<com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam, com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> getGetUserListMethod;
    if ((getGetUserListMethod = UserInfoGrpc.getGetUserListMethod) == null) {
      synchronized (UserInfoGrpc.class) {
        if ((getGetUserListMethod = UserInfoGrpc.getGetUserListMethod) == null) {
          UserInfoGrpc.getGetUserListMethod = getGetUserListMethod =
              io.grpc.MethodDescriptor.<com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam, com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUserList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList.getDefaultInstance()))
              .setSchemaDescriptor(new UserInfoMethodDescriptorSupplier("getUserList"))
              .build();
        }
      }
    }
    return getGetUserListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserInfoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInfoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInfoStub>() {
        @java.lang.Override
        public UserInfoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInfoStub(channel, callOptions);
        }
      };
    return UserInfoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserInfoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInfoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInfoBlockingStub>() {
        @java.lang.Override
        public UserInfoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInfoBlockingStub(channel, callOptions);
        }
      };
    return UserInfoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserInfoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserInfoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserInfoFutureStub>() {
        @java.lang.Override
        public UserInfoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserInfoFutureStub(channel, callOptions);
        }
      };
    return UserInfoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserInfoImplBase implements io.grpc.BindableService {

    /**
     */
    public void getUserList(com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam request,
        io.grpc.stub.StreamObserver<com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserListMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam,
                com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList>(
                  this, METHODID_GET_USER_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class UserInfoStub extends io.grpc.stub.AbstractAsyncStub<UserInfoStub> {
    private UserInfoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInfoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInfoStub(channel, callOptions);
    }

    /**
     */
    public void getUserList(com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam request,
        io.grpc.stub.StreamObserver<com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserInfoBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserInfoBlockingStub> {
    private UserInfoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInfoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInfoBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList getUserList(com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserListMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserInfoFutureStub extends io.grpc.stub.AbstractFutureStub<UserInfoFutureStub> {
    private UserInfoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserInfoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserInfoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList> getUserList(
        com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER_LIST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserInfoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserInfoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_LIST:
          serviceImpl.getUserList((com.github.switchover.failure.common.rpc.UserInfoOuterClass.NicknameOrTeam) request,
              (io.grpc.stub.StreamObserver<com.github.switchover.failure.common.rpc.UserInfoOuterClass.UserList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserInfoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.github.switchover.failure.common.rpc.UserInfoOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserInfo");
    }
  }

  private static final class UserInfoFileDescriptorSupplier
      extends UserInfoBaseDescriptorSupplier {
    UserInfoFileDescriptorSupplier() {}
  }

  private static final class UserInfoMethodDescriptorSupplier
      extends UserInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserInfoMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserInfoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserInfoFileDescriptorSupplier())
              .addMethod(getGetUserListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
