package pl.polsl.biai.controller;

import pl.polsl.biai.domain.Population;
import pl.polsl.biai.domain.SingleTour;
import pl.polsl.biai.domain.TravelList;

/**
 *
 * @author Marta Miler
 */
public class GeneticAlgorithm {
    
    private final double mutationRate;
    private final TravelList travelList;
    private final boolean elitism;
    
    public GeneticAlgorithm(double mutationRate, TravelList travelList, boolean elitism) {
        this.mutationRate = mutationRate;
        this.travelList = travelList;
        this.elitism = elitism;
    }

    public Population evolvePopulation(Population population){
        
        Population newPopulation = new Population();
       
        //ELITISM
        int elitismEnabled = 0;
        if (this.elitism) {
            newPopulation.addSingleTourToPopulation(population.getTheShortest());
            elitismEnabled = 1;
        }
        //CROSSOVER
        for (int i = elitismEnabled; i< population.getPopulationSize(); i++) {
            //PARENTS
            SingleTour parent1 = parentSelection(population);
            SingleTour parent2 = parentSelection(population);
        
            //CHILD
            SingleTour child = crossover(parent1, parent2);
            newPopulation.addSingleTourToPopulation(child);
        }
        
        //MUTATE
        for(int i = elitismEnabled;i<population.getPopulationSize();i++) {
            mutate(newPopulation.getSingleTour(i));
        }
        
        return newPopulation;
        
    }
    
       private SingleTour parentSelection(Population population){
        Population parentPopulation = new Population();
        
        int parentCount = (int) (Math.random() * (population.getSingleTour(0).getSingleTourSize()))+2;
        
        //SELECT RANDOM PARENTS
        for (int i = 0; i<parentCount; i++){
            int randomInteger = (int) (Math.random() * (population.getPopulationSize()));
            parentPopulation.addSingleTourToPopulation(population.getSingleTour(randomInteger));
        }
        
        return parentPopulation.getTheShortest();
    }
        
    //SWAP TWO RANDOM LOCATIONS
    private void mutate(SingleTour tour) {
        for(int pos1 = 0; pos1 < tour.getSingleTourSize();pos1++){
            if(Math.random()<this.mutationRate) {
                int pos2 = (int) ((tour.getSingleTourSize()) * Math.random());
                tour.swapCities(pos1, pos2);
            }
        }
        
    }
    
    //SELECT A SUBSET FROM THE FIRST PARENT AND THEN ADD THE SUBSET TO THE OFFSPRING
    //ANY MISSING VALUES ARE THEN ADDING TO THE OFFSPRING FROM THE SECOND PARENT IN ORDER THEY ARE FOUND
    private SingleTour crossover(SingleTour parent1, SingleTour parent2) {
        SingleTour child = new SingleTour(this.travelList);
        
        int startPosition = (int) (Math.random() * (parent1.getSingleTourSize()));
        int endPosition = (int) (Math.random() * (parent1.getSingleTourSize()));
        if(startPosition > endPosition) {
            int tmp = startPosition;
            startPosition = endPosition;
            endPosition = tmp;
        }
        
        //PARENT 1
        for(int i = 0; i<child.getSingleTourSize(); i++) {
            if(i >= startPosition && i <= endPosition){
                child.addCityToSingleTour(parent1.getCityFromSingleTour(i),i);
            }
        }
        
        //PARENT 2
         for(int i = 0; i<parent2.getSingleTourSize();i++) {
            if(!child.containsCity(parent2.getCityFromSingleTour(i))) {
                int j=0;
                while(child.getCityFromSingleTour(j)!=null) {
                    j++;
                }
                child.addCityToSingleTour(parent2.getCityFromSingleTour(i),j);
            }
        }
               
        return child;
    }
   

}
