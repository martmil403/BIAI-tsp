package pl.polsl.biai.domain;

import java.util.ArrayList;

/**
 * Travel list class model.
 * Holds a list of cities to visit.
 * 
 * @author Marta Miler
 * @author Michał Jakóbczyk
 */
public class TravelList {
    
    private final ArrayList<City> travelList;
    
    public TravelList() {
        travelList = new ArrayList<City>();
    }
    
    public void addToTravelList(City city) {
        travelList.add(city);
    }
    
    public void clearTravelList() {
        travelList.clear();
    }
    
    public City getCity(int index){
        return travelList.get(index);
    }
    
    public int citiesNumber() {
        return travelList.size();
    }
    
}
