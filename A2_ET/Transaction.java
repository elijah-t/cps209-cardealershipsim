import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The Transaction class. This class holds information about the transaction.
 *
 * @author  Elijah Tungul (500885285)
 * @since   2019-04-12 
 */

class Transaction{

    /**
     * Transaction ID (type int)
     */
    private int transactionID;

    /**
     * Date of transaction (object type Calendar)
     */
    private Calendar dateOfTransaction;

    /**
     * Car involved in the transaction (Object type Car)
     */
    private Car carBought;

    /**
     * Salesperson involved in the transaction (type String)
     */
    private String salesPerson;

    /**
     * Transaction type (type String)
     */
    private String transactionType;

    /**
     * Sale price (type double)
     */
    private double salePrice;

    /**
     * Used to format the date into (day of the week, month, day, year; object type SimpleDateFormat)
     */
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
    
    /**
     * Constructor method for Transaction
     * 
     * @param ID transaction ID
     * @param date date of transaction
     * @param car car being bought or returned
     * @param person salesperson doing the transaction
     * @param type transaction type
     * @param price price of the car
     */
    public Transaction(int ID, Calendar date, Car car, String person, String type, double price){
        transactionID = ID;
        dateOfTransaction = date;
        carBought = car;
        salesPerson = person;
        transactionType = type;
        salePrice = price;
    }

    /**
     * Shows the details of the transaction
     * 
     * @return A string of Transaction
     */
    public String display(){
        return "ID: " + transactionID + " Date: " + sdf.format(dateOfTransaction.getTime()) + " Sales rep: " +  
        salesPerson + " Type: " + transactionType + " Car: " + carBought.display();
    }

    /**
     * Getter method for ID
     * 
     * @return Returns the transaction ID
     */
    public int getID(){
        return transactionID;
    }

    /**
     * Getter method for date
     * 
     * @return Returns the transaction date
     */
    public Calendar getDate(){
        return dateOfTransaction;
    }
    
    /**
     * Getter method for name
     * 
     * @return Returns the sales person involved with the transaction
     */
    public String getName() {
    	return salesPerson;
    }
    
    /**
     * Getter method for type
     * 
     * @return Returns the transaction type
     */
    public String getType() {
    	return transactionType;
    }
    
    /**
     * Getter method for price
     * 
     * @return Returns the transaction price
     */
    public double getPrice() {
    	return salePrice;
    }
    
    /**
     * Getter method for car
     * 
     * @return Returns the car involved in the transaction
     */
    public Car getCar() {
    	return carBought;
    }

    /**
     * Setter method for transactionID
     * 
     * @param ID New transaction ID
     */
    public void setID(int ID){
        transactionID = ID;
    }

    /**
     * Setter method for dateOfTransaction
     * 
     * @param date New transaction date
     */
    public void setDate(Calendar date){
        dateOfTransaction = date;
    }

    /**
     * Setter method for carBought
     * 
     * @param ID New car
     */
    public void setCar(Car car){
        carBought = car;
    }

    /**
     * Setter method for salesPerson
     *  
     * @param name New name
     */
    public void setName(String name){
        salesPerson = name;
    }
    
    /**
     * Setter method for transactionType
     * 
     * @param type New type
     */
    public void setType(String type){
        transactionType = type;
    }
    /**
     * Setter method for salePrice
     * 
     * @param price New price
     */
    public void setPrice(double price){
        salePrice = price;
    }
}