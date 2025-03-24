package hieu.statisticservice.controller;

import hieu.statisticservice.request.MessageRequest;
import hieu.statisticservice.request.StatisticRequest;
import hieu.statisticservice.response.ApiResponse;
import hieu.statisticservice.response.StatisticResponse;
import hieu.statisticservice.service.EmailService;
import hieu.statisticservice.service.StatisticService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;
    private final EmailService emailService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getStatistics() {
        List<StatisticResponse> responses = statisticService.getAll();
        return ResponseEntity.ok(ApiResponse.builder()
                        .data(responses)
                        .message("Get all statistics")
                .build());
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> add(@RequestBody StatisticRequest request) {
        StatisticResponse responses = statisticService.add(request);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(responses)
                .message("Get all statistics")
                .build());
    }

    @PostMapping("/email")
    public ResponseEntity<ApiResponse> send(@RequestBody MessageRequest request) throws MessagingException {
        emailService.sendEmail(request);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Email sent successfully")
                .build());
    }
}
