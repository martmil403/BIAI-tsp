package pl.polsl.biai.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.swing.JTextField;
import pl.polsl.biai.domain.City;
import pl.polsl.biai.domain.Population;
import pl.polsl.biai.domain.SingleTour;
import pl.polsl.biai.domain.TravelList;

/**
 * Travelling salesman problem controller.
 * Instatiates list of cities and travels.
 * Runs main algorithm.
 * 
 * @author Marta Miler
 * @author Michał Jakóbczyk
 */
public class TheTravellingSalesmanProblem {

    private final TravelList travelList;
    
    private Population pop;
    
    private String initialDistance;

    public TheTravellingSalesmanProblem() {
        travelList = new TravelList();
        pop = null;
        initialDistance = null;
    }

    public void generateRandomCities(int citiesNumber) {
        travelList.clearTravelList();
        pop = null;
        initialDistance = null;
        // Create and add our cities
        for (int i = 0; i < citiesNumber; i++) {
            City city = new City();
            travelList.addToTravelList(city);
        }
    }

    public boolean initTravelListFromFile(String file) {
        travelList.clearTravelList();
        String[] citiesFromFile = file.split(";");
        try {
            for (int i = 0; i < citiesFromFile.length; i++) {

                int x = Integer.parseInt(citiesFromFile[i].split(",")[0]);
                int y = Integer.parseInt(citiesFromFile[i].split(",")[1]);
                City city = new City(x, y);
                travelList.addToTravelList(city);
                pop = null;
                initialDistance = null;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public int getSize() {
        return travelList.citiesNumber();
    }

    public SingleTour initAlgorithm(
            int populationSize, double mutation, int generations, boolean newPopulation, int repeat, int index,
            String fileName, JTextField result, JTextField initial, boolean elitism) {

        GeneticAlgorithm genethicAlgorithm = new GeneticAlgorithm(mutation, travelList, elitism);
        // Initialize population
        if(newPopulation || pop == null) {
            pop = new Population(populationSize, travelList);
            initialDistance = Double.toString(pop.getTheShortest().getDistance());
            initial.setText(initialDistance);
            
        }

        // Evolve population for generations
        for (int i = 0; i < generations; i++) {
            pop = genethicAlgorithm.evolvePopulation(pop);
            registerEachGeneration("generations.txt",pop.getTheShortest(),i);
        }
        
        
        // Print final results
        result.setText(Double.toString(pop.getTheShortest().getDistance()));
        registerLogResult(fileName, index, pop.getTheShortest());

        return pop.getTheShortest();
    }
    
    public void clear() {
        travelList.clearTravelList();
    }
    
        public void registerLogResult(String fileName, int index, SingleTour result) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter (fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
            bufferedWriter.write(index+1 + ". repeat");
            bufferedWriter.newLine();
            bufferedWriter.write("Initial distance: " + initialDistance);
            bufferedWriter.newLine();
            bufferedWriter.write("Result: " + result.getDistance());
            bufferedWriter.newLine();

            bufferedWriter.close();
    } catch (IOException e) {}
        
    }

    public void registerEachGeneration(String fileName, SingleTour shortest, int index) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter (fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
            bufferedWriter.write(index+1+"");
            bufferedWriter.write("\t");
            bufferedWriter.write(shortest.getDistance()+"");
            bufferedWriter.newLine();
            bufferedWriter.close();
    } catch (IOException e) {}
        
    }
    

}
