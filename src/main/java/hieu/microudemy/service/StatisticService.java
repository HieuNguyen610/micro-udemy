package hieu.microudemy.service;

import hieu.microudemy.request.MessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:8081")
public interface StatisticService {

    @PostMapping("/statistics/email")
    void send(@RequestBody MessageRequest request);
}
