package hieu.microudemy.service.impl;

import hieu.microudemy.entity.User;
import hieu.microudemy.repository.UserRepository;
import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.ApiResponse;
import hieu.microudemy.response.UserResponse;
import hieu.microudemy.service.UserService;
import hieu.microudemy.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityConverter entityConverter;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByName(request.getName())) {
            throw  new IllegalArgumentException("User name already exists");
        }

        User entity = User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .build();
        User savedUser = userRepository.save(entity);
        return entityConverter.convertUserEntityToResponse(savedUser);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        log.info("Get all users service");
        return entityConverter.convertUserEntitiesToResponses(userRepository.findAll());
    }

    @Override
    public UserResponse findUserById(Long id) {
        return entityConverter.convertUserEntityToResponse(userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Could not find user with id " + id)));
    }
}
