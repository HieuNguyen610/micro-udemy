package hieu.statisticservice.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StatisticRequest {
    private String message;
    private Date createdDate;
}
