package pl.polsl.biai.domain;

/**
 * City model class.
 * Holds information about location's coordinates.
 * Counts distances from other cities.
 * 
 * @author Marta Miler
 * @author Michał Jakóbczyk
 */
public class City {
    
    //Coordinates
    private final int x;
    private final int y;
    
    
    public City() {
        this.x = (int) (Math.random()*101);
        this.y = (int) (Math.random()*101);
    }
    
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public double measureDistance(City city){
        int distanceX = Math.abs(this.x - city.getX());
        int distanceY = Math.abs(this.y - city.getY());
        double distance = Math.sqrt((distanceX*distanceX)+(distanceY*distanceY));
        return distance;
    }
    
    @Override
    public String toString(){
        return "City [" + getX() + ";"+ "]" + getY();
    }
    
}
