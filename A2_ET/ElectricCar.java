/**
* The Electric class is the subclass of the Car superclass.
*
* @author  Elijah Tungul (500885285)
* @since   2019-03-20 
*/
public class ElectricCar extends Car{
    
    /**
     * Recharge time of the car (type int)
     */
    private int rechargeTime;

    /**
     * Battery type of the car (type String)
     */
    private String batteryType;

    /**
     * Default constructor method for class ElectricCar
     */
    public ElectricCar(){
        super();
        this.rechargeTime = 0;
        this.batteryType = "";
    }

    /**
     * Constructor method for class ElectricCar
     * 
     * @param mfr Sets manufacturer
     * @param clr Sets color
     * @param model Sets model
     * @param safetyRating Sets safety rating
     * @param maxRange Sets maximum range
     * @param AWD Sets AWD
     * @param price Sets price
     * @param rechargeTime Sets recharge time
     */
    public ElectricCar(String mfr, String clr, Model model, double safetyRating, int maxRange, boolean AWD, double price, int rechargeTime){
        super(mfr, clr, model, Power.ELECTRIC_MOTOR, maxRange, safetyRating, AWD, price);
        this.rechargeTime = rechargeTime;
        this.batteryType = "Lithium";
    }
    
    /**
     * Getter method for recharge time
     * 
     * @return the recharge time of an electric car
     */
    public int getRechargeTime(){
        return rechargeTime;
    }

    /**
     * Setter method for recharge time
     * 
     * @param rechargeTime Sets new recharge time
     */
    public void setRechargeTime(int rechargeTime){
        this.rechargeTime = rechargeTime;
    }

    /**
     * Getter method for battery type
     * 
     * @return the battery type of an electric car
     */
    public String getBatteryType(){
        return batteryType;
    }

    /**
     * Setter method for battery type
     * 
     * @param rechargeTime Sets new battery type
     */
    public void setBatteryType(String batteryType){
        this.batteryType = batteryType;
    }

    /**
     * Displays information about the electric car
     * 
     * @return A string value of superclass car display() + battery type, recharge time
     */
    public String display(){
        return super.display() + " ELECTRIC, Battery type: " + batteryType + " Recharge time: " + rechargeTime;
    }
}