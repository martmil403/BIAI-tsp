package pl.polsl.biai.domain;

import java.util.ArrayList;

/**
 * Population model class.
 * Holds a population of possible tours between cities.
 * 
 * @author Mata Miler
 * @author Michał Jakóbczyk
 */
public class Population {
    
    private final ArrayList<SingleTour> tours;
    
    public Population() {
       tours = new ArrayList<>(); 
    }
    
    public Population(int populationSize, TravelList travelList) {
        tours = new ArrayList<>();
            for(int i=0; i<populationSize; i++) {
                SingleTour singleTour = new SingleTour(travelList);
                singleTour.generateSingleTour(travelList);
                tours.add(singleTour);
        }
    }
    
    public SingleTour getSingleTour(int index) {
        return tours.get(index);
    }
    
    public SingleTour getTheShortest() {
        int shortestIndex = 0;
        for(int i = 1; i < tours.size(); i++) {
            if(tours.get(shortestIndex).getDistance()>=tours.get(i).getDistance())
                shortestIndex = i;
        }
        
        return tours.get(shortestIndex);
    }
    
    public int getPopulationSize(){
        return tours.size();
    }
    
    public void addSingleTourToPopulation(SingleTour tour){
        tours.add(tour);
    }
    
}
