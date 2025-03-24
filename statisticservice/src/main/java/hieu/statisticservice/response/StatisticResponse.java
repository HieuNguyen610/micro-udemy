package hieu.statisticservice.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StatisticResponse {
    private Long id;
    private String message;
    private Date createdDate;

}
