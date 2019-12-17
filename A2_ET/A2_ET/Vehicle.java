/**
* The vehicle class is the superclass of the Car subclass.
*
* @author  Elijah Tungul (500885285)
* @since   2019-03-20 
*/
public class Vehicle{
    /**
     * Manufacturer of the vehicle (type String)
     */
    private String mfr;

    /**
     * Color of the vehicle (type String)
     */
    private String color;

    /**
     * Power of the vehicle (enum type Power)
     */
    private Power power;

    /**
     * Number of wheels on the vehicle (type int)
     */
    private int numWheels;
    
    /**
     * Vehicle identification number (type int)
     */
    private int VIN;

    /**
     * Power of the vehicle (Constants)
     */
    public enum Power{
        ELECTRIC_MOTOR,
        GAS_ENGINE
    }

    /**
    * Default contructor method for class Vehicle
    *
    */
    public Vehicle(){
        this.mfr = "";
        this.color = "";
        power = Power.GAS_ENGINE;
        numWheels = 0;
        VIN = 0;
    }

    /**
    * Contructor method for class Vehicle
    *
    * @param mfr Sets the manufacturer
    * @param clr Sets the color
    * @param pwr Sets the power
    * @param numWheels Sets the number of wheels
    */
    public Vehicle(String mfr, String clr, Power pwr, int numWheels){
        this.mfr = mfr;
        this.color = clr;
        this.power = pwr;
        this.numWheels = numWheels;
        VIN = (int)(Math.random() * 499 + 100);
    }

    /**
     * Getter method for manufacturer
     * 
     * @return Returns the manufacturer name
     */
    public String getManufacturer(){
        return mfr;
    }

    /**
     * Setter method for manufacturer
     * 
     * @param mfr The newly set manufacturer name
     */
    public void setManufacturer(String mfr){
        this.mfr = mfr;
    }

    /**
     * Getter method for color
     * 
     * @return Returns the color of the vehicle
     */
    public String getColor(){
        return color;
    }

    /**
     * Setter method for color
     * 
     * @param color Sets the new color of the vehicle
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * Getter method for power
     * 
     * @return Gets the power of the vehicle
     */
    public Power getPower(){
        return power;
    }
    
    /**
     * Setter method for power
     * 
     * @param pwr Sets the power of the vehicle
     */
    public void setPower(Power pwr){
        power = pwr;
    }

    /**
     * Getter method for power
     * 
     * @return Number of wheels on the vehicle
     */
    public int getNumberOfWheels(){
        return numWheels;
    }

    /**
     * Setter method for power
     * 
     * @param numWheels Sets the number of wheels on the vehicle
     */
    public void setNumberOfWheels(int numWheels){
        this.numWheels = numWheels;
    }

    /**
     * Getter method for VIN
     * 
     * @return VIN number of a vehicle
     */
    public int getVIN(){
        return VIN;
    }


    /**
     * Tests if a vehicle is equal to another vehicle
     * For a vehicle to be equal to another vehicle, they must have the same manufacturer, power, and number of wheels.
     * 
     * @param other The vehicle being compared with
     * @return A boolean value based on if the two vehicles are equal
     */
    public boolean equals(Object other){
        Vehicle otherVehicle = (Vehicle) other;
        return this.mfr.equals(otherVehicle.mfr) && this.power == otherVehicle.power 
        && this.numWheels == otherVehicle.numWheels;
    }

    /**
     * Displays the manufacturer and color of the vehicle
     * 
     * @return A string value of manufacturer and color
     */
    public String display(){
        return VIN + " Manufacturer: " + mfr + " Color: "  + color;
    }
}