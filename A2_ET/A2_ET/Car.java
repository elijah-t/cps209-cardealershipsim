/**
* The Car class is the subclass of the Vehicle superclass.
* The class also implements the Comparable interface to use the CompareTo method.
*
* @author  Elijah Tungul (500885285)
* @since   2019-03-20 
*/

public class Car extends Vehicle implements Comparable<Car>{
    
    /**
     * Model of the car (Constant enum Model)
     */
    private Model model;

    /**
     * Maximum range of the car (type int)
     */
    private int maxRange;

    /**
     * Safety rating of the car (type double)
     */
    private double safetyRating;

    /**
     * Determines whether the car is 2WD or AWD (type boolean; false: 2WD, true: AWD)
     */
    private boolean AWD;

    /**
     * The price of the car (type double)
     */
    private double price;


    /**
     * Constants for Model
     */
    public enum Model {
        SEDAN,
        SUV,
        SPORTS,
        MINIVAN,
        RACING
    }

    //Toyota     blue   SEDAN   GAS_ENGINE      9.5  500 2WD 25000


    /**
     * Default constructor class for class Car
     */
    public Car(){
        super();
        this.model = Model.SEDAN;
        this.safetyRating = 0;
        this.AWD = false;
        this.price = 0;
    }

    /**
     * Constructor class for class Car 
     * (regular cars usually have a gas engine, and almost always have 4 wheels)
     * 
     * @param mfr Sets manufacturer
     * @param color Sets color
     * @param model Sets model
     * @param maxRange Sets maximum range
     * @param safetyRating Sets safety rating
     * @param AWD Sets 2WD or AWD
     * @param price Sets price
     */
    public Car(String mfr, String color, Model model, int maxRange, double safetyRating, boolean AWD, double price){
        super(mfr, color, Power.GAS_ENGINE, 4);
        this.model = model;
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }

    /**
     * Constructor class for class Car 
     * (Used if car has other power system and cars almost always have 4 wheels)
     * 
     * @param mfr Sets manufacturer
     * @param color Sets color
     * @param model Sets model
     * @param power Sets power
     * @param maxRange Sets maximum range
     * @param safetyRating Sets safety rating
     * @param AWD Sets 2WD or AWD
     * @param price Sets price
     */
    public Car(String mfr, String color, Model model, Power power,  int maxRange, double safetyRating, boolean AWD, double price){
        super(mfr, color, power, 4);
        this.model = model;
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }

    /**
     * Getter method for safety rating 
     * 
     * @return Returns the safety rating
     */
    public double getSafetyRating(){
        return safetyRating;
    }

    /**
     * Getter method for maximum range
     * 
     * @return Returns the maximum range
     */
    public int getMaxRange(){
        return maxRange;
    }

    /**
     * Getter method for price
     * 
     * @return Returns the price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Getter method for AWD
     * 
     * @return Returns if the car is AWD
     */
    public boolean getAWD(){
        return AWD;
    }

    /**
     * Getter method for model
     * 
     * @return Returns the model
     */
    public Model getModel(){
        return model;
    }

    /**
     * Setter method for safety rating
     * 
     * @param rating Sets new safety rating
     */
    public void setSafetyRating(double rating){
        safetyRating = rating;
    }

    /**
     * Setter method for maximum range
     * 
     * @param range Sets new maximum range
     */
    public void setMaxRange(int range){
        maxRange = range;
    }

    /**
     * Setter method for price
     * 
     * @param price Sets new price
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Setter method for AWD
     * 
     * @param AWD Sets new AWD
     */
    public void setAWD(boolean AWD){
        this.AWD = AWD;
    }

    /**
     * Setter method for model
     * 
     * @param model Sets new model
     */
    public void setModel(Model model){
        this.model = model;
    }

    /**
     * Displays information about the car
     * 
     * @return A string value of superclass vehicle display() + model, price, safety rating, maximum range
     */
    public String display(){
        return super.display() + " Model: " + model + " Price: $" + price + " Safety rating: " + safetyRating + " Maximum range: "+ maxRange ;
    }

    /**
     * Tests if a car is equal to another car
     * For two cars to be equal, they must be the same vehicle (manufacturer, power, number of wheels) 
     * and have the same model, and both must be either 2WD or AWD.
     * 
     * @param other The car being compared with
     * @return A boolean value based on if the two cars are equal
     */
    public boolean equals(Object other){
        Car otherCar = (Car) other;    
        boolean vehicleEqual = super.equals(otherCar);
        if(vehicleEqual) return this.model == otherCar.model && this.AWD == otherCar.AWD;
        return false;
    }
    
    /**
     * This method is taken from Comparable<Car>, where the method is used for sorting by price
     * 
     * @param other The car being compared with
     * @return An integer value depending on the price of both cars 
     */
    public int compareTo(Car other){
        if(this.price > other.price){
            return 1;
        }
        else if(this.price < other.price){
            return -1;
        }
        else{
            return 0;
        }
    }    
}

