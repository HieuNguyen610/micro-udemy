package hieu.microudemy.controller;

import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.ApiResponse;
import hieu.microudemy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
