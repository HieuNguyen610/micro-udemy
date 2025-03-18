package hieu.microudemy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.microudemy.entity.User;
import hieu.microudemy.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class EntityConverter {

    private final ObjectMapper objectMapper;

    public UserResponse convertUserEntityToResponse(User entity) {
        return objectMapper.convertValue(entity, UserResponse.class);
    }

    public List<UserResponse> convertUserEntitiesToResponses(List<User> entities) {
        return entities.stream().map(entity -> convertUserEntityToResponse(entity)).toList();
    }
}
