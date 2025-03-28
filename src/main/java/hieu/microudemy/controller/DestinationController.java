package hieu.microudemy.controller;

import hieu.microudemy.entity.TravelDestination;
import hieu.microudemy.service.TravelDestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

    private final TravelDestinationService travelDestinationService;

    DestinationController(TravelDestinationService travelDestinationService){
        this.travelDestinationService = travelDestinationService;
    }


    //Rest API to test circuit breaker
    @GetMapping("/travelDestination")
    public ResponseEntity<TravelDestination> getDestinationDetails(@RequestParam(defaultValue = "hanoi") String location, @RequestParam(defaultValue = "vietnam") String country){
        TravelDestination travelDestination = travelDestinationService.getDestinationDetails(location,country);
        return new ResponseEntity<>(travelDestination, HttpStatus.OK);
    }

    //Rest API to test Rate limit
    @GetMapping("/travelAttractions")
    public ResponseEntity<String>  getAttractions(@RequestParam(defaultValue = "hanoi") String location, @RequestParam(defaultValue = "vietnam") String country){
        String attractions = travelDestinationService.getAttractions(location,country);
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }
}
