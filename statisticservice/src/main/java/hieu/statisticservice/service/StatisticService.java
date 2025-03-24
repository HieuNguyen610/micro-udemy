package hieu.statisticservice.service;

import hieu.statisticservice.request.StatisticRequest;
import hieu.statisticservice.response.StatisticResponse;

import java.util.List;

public interface StatisticService {
    StatisticResponse add(StatisticRequest request);
    List<StatisticResponse> getAll();
}
