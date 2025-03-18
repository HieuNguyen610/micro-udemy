package hieu.microudemy.service;

import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);
}
