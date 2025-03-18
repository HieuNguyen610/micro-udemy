package hieu.microudemy.service;

import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> findAllUsers();
}
