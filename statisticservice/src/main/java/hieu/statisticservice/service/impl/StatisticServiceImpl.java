package hieu.statisticservice.service.impl;

import hieu.statisticservice.enitity.Statistic;
import hieu.statisticservice.repository.StatisticRepository;
import hieu.statisticservice.request.StatisticRequest;
import hieu.statisticservice.response.StatisticResponse;
import hieu.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    @Override
    public StatisticResponse add(StatisticRequest request) {
        Statistic statistic = Statistic.builder()
                .message(request.getMessage())
                .createdDate(new Date())
                .build();
        Statistic response = statisticRepository.save(statistic);
        return convertEntityToResponse(response);
    }

    @Override
    public List<StatisticResponse> getAll() {
        return convertEntitiesToResponses(statisticRepository.findAll());
    }

    private StatisticResponse convertEntityToResponse(Statistic entity) {
        return StatisticResponse.builder()
               .id(entity.getId())
               .message(entity.getMessage())
               .createdDate(entity.getCreatedDate())
               .build();
    }

    private List<StatisticResponse> convertEntitiesToResponses(List<Statistic> entities) {
        return entities.stream()
               .map(this::convertEntityToResponse)
               .collect(toList());
    }
}
