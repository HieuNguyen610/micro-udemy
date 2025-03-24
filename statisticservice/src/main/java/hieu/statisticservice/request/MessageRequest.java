package hieu.statisticservice.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageRequest {
    private String to;
    private String text;
    private String subject;
    private String from;
}
