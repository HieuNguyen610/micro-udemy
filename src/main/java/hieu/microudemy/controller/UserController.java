package hieu.microudemy.controller;

import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.ApiResponse;
import hieu.microudemy.response.UserResponse;
import hieu.microudemy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Create user successfully")
                        .data(userService.createUser(request))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllUsers() {
        // Implement logic to get all users
        List<UserResponse> responses = userService.findAllUsers();
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get all users successfully")
                        .data(responses) // Placeholder for actual data
                .build());
    }
}
