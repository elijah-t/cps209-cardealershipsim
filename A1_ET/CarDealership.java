import java.util.*;

/**
* The CarDealership class. This class organizes cars into an ArrayList.
*
* @author  Elijah Tungul (500885285)
* @since   2019-03-20 
*/

public class CarDealership{

    /**
     * List of cars (type ArrayList<Car>)
     */
    private ArrayList<Car> cars;

    /**
     * Determines the electric filter (type boolean; false: off, true: on)
     */
    private boolean filterElectric;

    /**
     * Determines the AWD filter (type boolean; false: off, true: on)
     */
    private boolean filterAWD;

    /**
     * Determines the price filter (type boolean; false: off, true: on)
     */
    private boolean filterPrice;

    /**
     * Minimum price of the price filter (type double)
     */
    private double minimumPrice;

    /**
     * Maximum price of the price filter (type double)
     */
    private double maximumPrice;

    /**
     * Default constructor method for CarDealership
     */
    public CarDealership(){
        cars = new ArrayList<Car>();
    }
    
    /**
     * Adds an ArrayList of new cars into the instance variable cars
     * 
     * @param newCars The list of cars being added
     */
    public void addCars(ArrayList<Car> newCars){
        cars.addAll(newCars);
    }

    /**
     * Removes a car from the ArrayList cars and returns a reference to it.
     * If the index is invalid, IndexOutOfBoundsException is thrown.
     * 
     * @param index The index of the car being bought in ArrayList cars
     * @return a reference to the car being bought, if the index is valid
     */
    public Car buyCar(int index) throws IndexOutOfBoundsException{
        if((0 <= index) && (index < cars.size()) ){
            Car carBought = cars.get(index);
            cars.remove(carBought);
            return carBought;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Adds a car to the ArrayList cars
     * (In CarDealershipSimulator, returns lastCarBought.)
     * 
     * @param car Car being returned
     */
    public void returnCar(Car car){
        if(car != null){
            cars.add(car);
        }
    }

    /**
     * Prints the inventory of cars based on what filters are off or on.
     * The filterElectric variable keeps track of the electric filter, then checks if the power is an electric motor.
     * The filterAWD variable keeps track of the AWD filter, then checks if the car is AWD.
     * The filterPrice variable keeps track of the price filter, then checks if the cars falls in between
     * minimumPrice and maximumPrice.
     * 
     * Note: The filters are ANDed.
     */
    public void displayInventory(){
        if(cars.size() == 0){
            System.out.println("No cars in stock!");
        }
        else{
            for(int i = 0; i<cars.size(); i++){
                if(filterElectric){
                    if(cars.get(i).getPower() != Vehicle.Power.ELECTRIC_MOTOR){
                        continue;
                    }
                }
                if(filterAWD){
                    if(cars.get(i).getAWD() != true){
                        continue;
                    }
                }
                if(filterPrice){
                    if(!(minimumPrice <= cars.get(i).getPrice() && cars.get(i).getPrice() <= maximumPrice)){
                        continue;
                    }
                }
                System.out.println(i + " " + cars.get(i).display());
            }
        }
    }

    /**
     * This method sets the boolean variable filterElectric to true.
     */
    public void filterByElectric(){
        filterElectric = true;
    }

    /**
     * This method sets the boolean variable filterAWD to true.
     */
    public void filterByAWD(){
        filterAWD = true;
    }

    /**
     * This method sets the boolean variable filterPrice to true, and sets a price range for cars.
     * The method throws an IllegalArgumentException if the difference between maxRange and minRange is negative.
     * 
     * @param minPrice the minimum price of the car filter
     * @param maxPrice the maximum price of the car filter
     */
    public void filterByPrice(double minPrice, double maxPrice) throws IllegalArgumentException{
        if(maxPrice - minPrice >= 0){
            filterPrice = true;
            minimumPrice = minPrice;
            maximumPrice = maxPrice;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method sets all of the filter variables to false; effectively clearing the filters.
     */
    public void filtersClear(){
        filterElectric = false;
        filterAWD = false;
        filterPrice = false;
    }

    /**
     * This method sorts the cars by price (lowest to highest).
     */
    public void sortByPrice(){
        Collections.sort(cars);
    }

    /**
     * This method sorts the cars by safety rating (highest to lowest).
     */
    public void sortBySafetyRating(){
        Collections.sort(cars, new SafetyRatingComparator());
    }

    /**
     * This method sorts the cars by maximum range (highest to lowest).
     */
    public void sortByMaxRange(){
        Collections.sort(cars, new MaxRangeComparator());
    }

    /**
     * A helper class that implements the Comparator interface and compares cars by safety rating.
     */
    private class SafetyRatingComparator implements Comparator<Car>{
            
        /**
        * This method compares two car's safety ratings.
        * 
        * @param a A car to compare
        * @param b Another car that is compared with a
        * @return an integer value based on both car's safety rating
        */
            public int compare(Car a, Car b){
            if(a.getSafetyRating() > b.getSafetyRating()) return -1;
            else if(a.getSafetyRating() < b.getSafetyRating()) return 1;
            else return 0;
        }
    }

    /**
     * A helper class that implements the Comparator interface and compares cars by maximum range.
     */
    private class MaxRangeComparator implements Comparator<Car>{
           
            /**
            * This method compares two car's maximum range.
            * 
            * @param a A car to compare
            * @param b Another car that is compared with a
            * @return an integer value based on both car's maximum range
            */
            public int compare(Car a, Car b){
            if(a.getMaxRange() > b.getMaxRange()) return -1;
            else if(a.getMaxRange() < b.getMaxRange()) return 1;
            else return 0;
        }
    }
}