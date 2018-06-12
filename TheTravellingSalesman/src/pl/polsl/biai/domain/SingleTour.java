package pl.polsl.biai.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Single tour class model.
 * Represents a haul to be gone between two places.
 * 
 * @author Marta Miler
 * @author Michał Jakóbczyk
 */
public class SingleTour {
    
    private final ArrayList<City> tour;
    private int distance;
    
    // Create an empty tour, will be generated in the algorithm
    public SingleTour(TravelList travelList) {
        this.tour = new ArrayList<>();
        for (int i = 0; i < travelList.citiesNumber(); i++) {
            // Add null tour to indicate that there is a spot for one
            tour.add(null);
        }
        
        this.distance = 0;
    }
    
    public SingleTour(ArrayList<City> tour) {
        this.tour = tour;
        this.distance = 0;
    }
    
    public void generateSingleTour(TravelList travelList) {
        // Add every city from the TravelList passed as an argument
        // to the tour list
        for(int i=0; i<travelList.citiesNumber(); i++ ) {
            addCityToSingleTour(travelList.getCity(i), i);
        }
        
        // Shuffle is used to randomly permute tours collection.
        // We are going to obtain a random tour
        Collections.shuffle(tour);
    }
    
    public int getDistance() {
        // If the distance was not being count yet
        if (distance == 0){
            int singleTourDistance = 0;
            
            // Get through all Cities in the collection
            for (int i =0; i < tour.size();i++) {
                // If this is not our last city
                if ((i+1) < tour.size()) {
                    singleTourDistance += tour.get(i).measureDistance(tour.get(i+1));
                } 
                // If this is our last city
                else {
                    singleTourDistance += tour.get(i).measureDistance(tour.get(0));
                }
            }
            
            distance = singleTourDistance;
        }
        return distance;
    }
    
    public int getSingleTourSize() {
        return tour.size();
    }
    
    public void addCityToSingleTour(City city, int index) {
        tour.set(index, city);
        distance = 0;
    }
    
    public City getCityFromSingleTour(int index) {
        return tour.get(index);
    }
    
    public boolean containsCity(City city) {
        return tour.contains(city);
    }
    
    public void swapCities(int pos1, int pos2){
        City city1 = tour.get(pos1);
        City city2 = tour.get(pos2);
        
        tour.set(pos1, city2);
        tour.set(pos2, city1);
    }
       
}
