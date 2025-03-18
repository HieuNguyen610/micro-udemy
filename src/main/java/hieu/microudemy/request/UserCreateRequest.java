package hieu.microudemy.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateRequest {
    private String name;
    private String password;
}
