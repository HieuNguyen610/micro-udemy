package hieu.microudemy.service;

import hieu.microudemy.request.MessageRequest;
import hieu.microudemy.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "statistic-service", url = "http://localhost:8081")
public interface StatisticService {

    @PostMapping("/statistics/email")
    void send(@RequestBody MessageRequest request);

    @GetMapping("/statistics")
    ResponseEntity<ApiResponse> getAllStatistics();
}
