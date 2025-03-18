package hieu.microudemy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.microudemy.entity.User;
import hieu.microudemy.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EntityConverter {

    private final ObjectMapper objectMapper;

    public UserResponse convertUserEntityToResponse(User entity) {
        return objectMapper.convertValue(entity, UserResponse.class);
    }
}
