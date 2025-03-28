package hieu.microudemy.service;

import hieu.microudemy.entity.TravelDestination;

/**
 * @author Lejil
 *
 */
public interface TravelDestinationService {

	TravelDestination getDestinationDetails(String destinationName, String country);
	
	String getAttractions(String destinationName,String country);
}
