import java.text.*;
import java.util.*;


/**
 * The AccountingSystem class. This class keeps track of all transactions.
 *
 * @author  Elijah Tungul (500885285)
 * @since   2019-04-12 
 */

class AccountingSystem{

	/**
     * List of transactions (type ArrayList<Transaction>)
     */
    ArrayList<Transaction> transactions;
	
	/**
     * Default constructor method for AccountingSystem
     */
    public AccountingSystem(){
        transactions = new ArrayList<Transaction>();
    }
	
	/**
	 * This method adds a new transaction to the ArrayList transactions, and returns a string value of the transaction.
	 * 
	 * @param date Date record
	 * @param car Car record
	 * @param person Salesperson responsible for transaction
	 * @param type Transaction type
	 * @param price Sale price
	 * @return The display method for the newly added transaction
	 */
    String add(Calendar date, Car car, String person, String type, double price){
        int ID = (int)(Math.random() * 99 + 1);
        Transaction temp = new Transaction(ID, date, car, person, type, price);
        transactions.add(temp);
        return temp.display();
	}
	
	/**
	 * This method gets a chosen transaction from the ArrayList transactions and returns a reference to it.
	 * 
	 * @param ID Transaction ID
	 * @return If a transaction matches with an ID, return the transaction. Otherwise, return null.
	 */
    public Transaction getTransaction(int ID){
        for(int i = 0; i<transactions.size(); i++){
            if(transactions.get(i).getID() == ID){
                return transactions.get(i);
            }
        }
        return null;
    }
    
    /**
	 * This method returns a reference to the ArrayList transactions.
	 * 
	 * @return ArrayList transactions
	 */
    public ArrayList<Transaction> getTransactionList(){
    	return transactions;
	}
	
	/**
	 * Sets a new ArrayList transactions based on the given parameters
	 * 
	 * @param newList
	 */
	public void setTransactionList(ArrayList<Transaction> newList){
		transactions = newList;
	}

	/**
	 * Displays each transaction in ArrayList transactions using the display method in the transaction class
	 */
    public void displayTransactions() {
    	for(int i = 0; i<transactions.size(); i++) {
    		System.out.println(transactions.get(i).display());
    	}
    }
	
	/**
	 * Displays each transaction in ArrayList transactions using the display method in the transaction class based on the month parameter
	 */
    public void displayTransactions(int month) {
    	for(int i = 0; i<transactions.size(); i++) {
    		if(transactions.get(i).getDate().get(Calendar.MONTH) == month - 1) {
    			System.out.println(transactions.get(i).display());
    		}
    	}
    }
	
	/**
	 * This method checks sales for each salesperson.
	 * 
	 * @return A map of sales for each salesperson.
	 */
    public Map<String, Integer> checkSales() {
    	Map<String, Integer> sales = new TreeMap<String, Integer>();
    	for(int i = 0; i<transactions.size(); i++) {
    		String salesPerson = transactions.get(i).getName();
    		Integer count = sales.get(salesPerson);
    		if(count == null) count = 1;
    		else if(count != null) count++;
    		sales.put(salesPerson, count);
    	}
    	return sales;
    }
	
	/**
	 * This method prints out the top selling salesperson.
	 */
    public void topSales() {
    	Map<String, Integer> sales = checkSales();
    	int topSalesNum = 0;
    	String topName = "";
    	String tie = "";
    	for(String name: sales.keySet()) {
    		if(sales.get(name) > topSalesNum) {
    			topSalesNum = sales.get(name);
    			topName = name;
    			tie = "";
    		}
    		else if(sales.get(name) == topSalesNum) {
    			if(tie.length() == 0) {
    				tie += topName;
    			}
    			tie += " and " + name;
    		}
    	}
    	if(tie.length() > 0) {
    		System.out.println("Top sales: " + tie + " with " + topSalesNum + ".");
    	}
    	else {
    		System.out.println("Top sales: " + topName + " with " + topSalesNum + ".");
    	}
    	
    }
	
	/**
	 * This method checks the monthly sales.
	 * 
	 * @return A map of the monthly sales.
	 */
    public Map<Integer, Integer> checkMonth() {
    	Map<Integer, Integer> monthlySales = new TreeMap<Integer, Integer>();
    	for(int i = 0; i<transactions.size(); i++) {
    		int month = transactions.get(i).getDate().get(Calendar.MONTH);
    		Integer count = monthlySales.get(month);
    		if(count == null) count = 1;
    		else if(count != null) count++;
    		monthlySales.put(month, count);
    	}
    	return monthlySales;
    }
	
	/**
	 * This method prints out the sales stats.
	 * The method prints out profit, total cars sold, total cars returned, sales average, and top month sold.
	 */
    public void salesStats() {
    	double profit = 0;
    	for(int i = 0; i< transactions.size(); i++) {
    		if(transactions.get(i).getType() == "BUY") {
    			profit += transactions.get(i).getPrice();
    		}
    	}
    	int totalSold = 0;
    	for(int i = 0; i<transactions.size(); i++) {
    		if(transactions.get(i).getType() == "BUY") {
    			totalSold++;
    		}
    	}
    	int totalReturned = 0;
    	for(int i = 0; i<transactions.size(); i++) {
    		if(transactions.get(i).getType() == "RETURN") {
    			totalReturned++;
    		}	
    	}
    	Map<Integer, Integer> monthlySold = checkMonth();
    	int topMonthlySold = 0;
    	int topMonth = 0;
    	for(int i = 0; i<transactions.size(); i++) {
    		int month = transactions.get(i).getDate().get(Calendar.MONTH);
    		if(monthlySold.get(month) > topMonthlySold) {
    			topMonthlySold = monthlySold.get(month);
    			topMonth = month;
    		}
    	}
    	String topMonthString = new DateFormatSymbols().getMonths()[topMonth];
    	System.out.println("Total sales: " + profit + " Total cars sold: " + totalSold + " Sales average: " + profit/totalSold + " Best month: " + topMonthString + " with " + topMonthlySold + " sold. Total cars returned: " + totalReturned);
    }
}