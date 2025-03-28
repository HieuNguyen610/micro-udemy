package hieu.microudemy.controller;

import hieu.microudemy.request.MessageRequest;
import hieu.microudemy.request.UserCreateRequest;
import hieu.microudemy.response.ApiResponse;
import hieu.microudemy.response.UserResponse;
import hieu.microudemy.service.RateLimitingService;
import hieu.microudemy.service.StatisticService;
import hieu.microudemy.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j(topic = "USER-CONTROLLER")
public class UserController {

    private final UserService userService;
    private final StatisticService statisticService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        MessageRequest msgRequest = MessageRequest.builder()
                .from(request.getName())
                .to(response.getName())
                .subject("Create user")
                .text("Congratulations for creating a new user with name : " + request.getName())
                .build();
        statisticService.send(msgRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Create user successfully")
                        .data(response)
                .build());
    }

    @GetMapping("")
//    @PreAuthorize("hasAuthority('SCOPE_read') && hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAllUsers() {
        // Implement logic to get all users
        log.info("Get all users ");
        List<UserResponse> responses = userService.findAllUsers();
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get all users successfully")
                        .data(responses) // Placeholder for actual data
                .build());
    }

    @CircuitBreaker(name = "getUserById", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.findUserById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get user by ID successfully")
                        .data(response) // Placeholder for actual data
                .build());
    }

    public ResponseEntity<ApiResponse> fallbackGetUserById(Long id, Throwable ex) {
        log.error("Fallback getUserById: " + id);
        return ResponseEntity.status(500)
                       .body(ApiResponse.builder()
                                .message("Failed to get user by ID (fallback from circuitbreaker) ")
                                .data("User not found with ID: " + id)
                        .build());
    }

    @GetMapping("/stats/all")
    public ResponseEntity<ApiResponse> getAllStatistic() {
        try {
            Thread.sleep(10_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statisticService.getAllStatistics();
    }
}
