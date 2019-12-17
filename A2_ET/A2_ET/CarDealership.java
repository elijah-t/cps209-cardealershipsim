import java.util.*;
import sun.util.calendar.*;
import java.text.SimpleDateFormat;

/**
* The CarDealership class. This class organizes cars into an ArrayList.
*
* @author  Elijah Tungul (500885285)
* @since   2019-04-12 
*/

public class CarDealership{

    /**
     * List of cars (type ArrayList<Car>)
     */
    private ArrayList<Car> cars;

    /**
     * A salesTeam object instance variable
     */
    private SalesTeam team;

    /**
     * An AccountingSystem object instance variable
     */
    private AccountingSystem accounting;
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
     * Last car bought (object Car)
     */
    private Car carBought;
    
    /**
     * Seller of the car bought (type String)
     */
    private String seller;

    /**
     * Month of the car bought (type int)
     */
    private int month;

    /**
     * Day of the car bought (type int)
     */
    private int day;

    /**
     * Date of the car bought (object type Calendar)
     */
    private Calendar date;

    /**
     * Random object for a randomized date (object type random)
     */
    private Random random;

    /**
     * Default constructor method for CarDealership
     */
    public CarDealership(){
        cars = new ArrayList<Car>();
        team = new SalesTeam();
        accounting = new AccountingSystem();
        random = new Random();
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
     * Does a linear search of the cars ArrayList; if the index = -1 at the end of the loop, 
     * the VIN does not exist and the method is stopped.
     * If the index is found, the car is removed with the index and a random date is generated as a buy date.
     * Returns a string of the transaction added to the accounting system.
     * 
     * @param VIN The VIN of the car being bought in ArrayList cars
     * @return a String reference to the transaction, if the VIN is valid
     */
    public String buyCar(int VIN){
        int index = 0;
    	for(int i = 0; i<cars.size(); i++){
            if(cars.get(i).getVIN() == VIN){
            	index = i;
                break;
            }
            else {
            	index = -1;
            }
        }
    	if(index == -1) {
    		return "ERROR: This VIN does not exist!";
    	}
    	carBought = cars.get(index);
    	cars.remove(index);
        seller = team.pickRandom();
        month = random.nextInt(12);
        day = random.nextInt(25) + 1;
        date = new GregorianCalendar();
        date.set(2019, month, day);
        return accounting.add(date, carBought, seller, "BUY", carBought.getPrice());
    }

    /**
     * Returns a car based on it's transaction ID in the accounting system.
     * If the transaction is a return, then print an error message.
     * If the car has a return transaction already, then it cannot be returned again.
     * If the return transaction is valid, a random date is generated and a new transaction is added to the accounting system.
     * The car is then added back to the cars ArrayList.
     * 
     * @param transaction Transaction ID
     */
    public void returnCar(int transaction){
    	Transaction returnTransaction = accounting.getTransaction(transaction);
    	ArrayList<Transaction> transactions = accounting.getTransactionList();
    	if(returnTransaction.getType() == "RETURN" || returnTransaction == null) {
    		System.out.println("ERROR: Cannot return the specified car.");
    	}
    	else if(returnTransaction.getType() == "BUY" && returnTransaction != null) {
    		for(int i = 0; i<transactions.size(); i++) {
    			if(accounting.transactions.get(i).getCar().equals(returnTransaction.getCar())){
					if(accounting.transactions.get(i).getType() == "RETURN") {
						System.out.println("ERROR: Cannot return the specified car.");
						return;
					}
    			}
    		}
    		date = returnTransaction.getDate();
            int dayReturned = date.get(Calendar.DAY_OF_MONTH) + (random.nextInt(3) + 1);
            GregorianCalendar dateReturned = new GregorianCalendar(2019, month, dayReturned);
            System.out.println(accounting.add(dateReturned, carBought, seller, "RETURN", carBought.getPrice()));
            cars.add(carBought);
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
                System.out.println(cars.get(i).display());
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
     * This method sorts the cars by VIN (lowest to highest).
     */
    public void sortByVIN(){
        Collections.sort(cars, new VINComparator());
    }

    /**
     * This method displays the transactions in the accounting system.
     */
    public void displayTransactions() {
    	accounting.displayTransactions();
    }
    
    /**
     * This method displays the transactions in the accounting system within the given month.
     */
    public void displayTransactions(int month) {
    	accounting.displayTransactions(month);
    }
    
    /**
     * Displays the sales team
     */
    public void displayTeam() {
    	team.listTeam();
    }
    
    /**
     * Displays the top salesperson
     */
    public void displayTopSales() {
    	accounting.topSales();
    }

    /**
     * Displays the accounting system syays
     */
    public void displayStats() {
    	accounting.salesStats();
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

    /**
     * A helper class that implements the Comparator interface and compares cars by VIN.
     */
    private class VINComparator implements Comparator<Car>{
        
        /**
            * This method compares two car's VIN.
            * 
            * @param a A car to compare
            * @param b Another car that is compared with a
            * @return an integer value based on both car's VIN
            */
        public int compare(Car a, Car b){
            if(a.getVIN() > b.getVIN()) return 1;
            else if(a.getVIN() < b.getVIN()) return -1;
            else return 0;
        }
    }
}