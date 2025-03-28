package hieu.microudemy.service;

public interface RateLimitingService {
    boolean allowRequest(String apiKey);
}
