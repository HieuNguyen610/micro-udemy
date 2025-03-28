package hieu.microudemy.service.impl;

import hieu.microudemy.entity.TravelDestination;
import hieu.microudemy.service.TravelDestinationService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Lejil
 *
 */

@Service
@RequiredArgsConstructor
public class TravelDestinationServiceImpl implements TravelDestinationService {

	Logger log = LogManager.getLogger(TravelDestinationServiceImpl.class);

	private static final String NO_DETAILS_AVAILABLE = "No Details Available";
	private final RateLimiterRegistry rateLimiterRegistry;
	private final CircuitBreakerRegistry circuitBreakerRegistry;


	@Override
	@CircuitBreaker(name = "travelDestination", fallbackMethod = "fallbackTravelDestination")
	public TravelDestination getDestinationDetails(String destinationName, String country) throws InterruptedException {

		if (destinationName.equals("hcm")) {
			throw new IllegalArgumentException("Invalid destination");
		}
		if (destinationName.equals("hue")) {
			Thread.sleep(10_000);
		}
		return TravelDestination.builder()
				.name(destinationName)
				.country(country)
				.build();

	}

	public TravelDestination fallbackTravelDestination(String destinationName, String country, Exception ex) {
		TravelDestination destination = new TravelDestination();
		destination.setName(destinationName);
		destination.setCountry(country);
		destination.setAttractions(NO_DETAILS_AVAILABLE);
		destination.setDestinationId(NO_DETAILS_AVAILABLE);
		destination.setBestSeasonToVisit(NO_DETAILS_AVAILABLE);
		destination.setCity(NO_DETAILS_AVAILABLE);
		destination.setAttractions(NO_DETAILS_AVAILABLE);
		return destination;
	}

	@Override
	@RateLimiter(name = "travelAttractions", fallbackMethod = "fallbackRateLimit")
	public String getAttractions(String destinationName, String country) {
		return destinationName
				+ " is renowned for its stunning alpine scenery, abundant wildlife, and iconic trails such as Trail Ridge Road and Bear Lake.";
	}

	public String fallbackRateLimit(String destinationName, String country, RequestNotPermitted requestNotPermitted) {
		return "API rate limit exceeded. Please try again in one minute to check the attractions at ."
				+ destinationName;
	}

	@PostConstruct
	public void postConstruct() {
		rateLimiterEventPublisher();
		circuitBreakerEventPublisher();
	}

	private void rateLimiterEventPublisher() {
		io.github.resilience4j.ratelimiter.RateLimiter.EventPublisher rateLimitEventPublisher = rateLimiterRegistry
				.rateLimiter("travelAttractions").getEventPublisher();
		rateLimitEventPublisher.onEvent(event -> log.info("travelAttractions- On Event. Event Details: {}.", event));
		rateLimitEventPublisher
				.onSuccess(event -> log.info("travelAttractions - On Success. Event Details: {}.", event));
		rateLimitEventPublisher
				.onFailure(event -> log.info("travelAttractions - On Failure. Event Details: {}.", event));

	}

	private void circuitBreakerEventPublisher() {
		io.github.resilience4j.circuitbreaker.CircuitBreaker.EventPublisher circuitBreakerEventPublisher = circuitBreakerRegistry
				.circuitBreaker("travelDestination").getEventPublisher();
		circuitBreakerEventPublisher
				.onEvent(event -> log.info("travelDestination- On Event. Event Details: {}.", event));
		circuitBreakerEventPublisher
				.onSuccess(event -> log.info("travelDestination - On Success. Event Details:{}.", event));
		circuitBreakerEventPublisher.onFailureRateExceeded(
				event -> log.info("travelDestination - On FailureRateExceed. Event Details:{}.", event));
		circuitBreakerEventPublisher.onSlowCallRateExceeded(
				event -> log.info("travelDestination - On SlowCallRateExceeded Event Details: {}.", event));
	}
}
