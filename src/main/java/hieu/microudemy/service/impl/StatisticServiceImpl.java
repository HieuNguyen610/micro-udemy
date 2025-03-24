package hieu.microudemy.service.impl;

import hieu.microudemy.request.MessageRequest;
import hieu.microudemy.response.ApiResponse;
import hieu.microudemy.service.StatisticService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticServiceImpl implements StatisticService {


    @Override
    public void send(MessageRequest request) {
        log.info("Sending email service is slow...");
    }

    @Override
    public ResponseEntity<ApiResponse> getAllStatistics() {
        log.info("Getting all statistics service is slow...");
        return null;
    }
}
