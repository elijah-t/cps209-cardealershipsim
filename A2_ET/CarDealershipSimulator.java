import java.io.*;
import java.util.*;

/**
* The CarDealershipSimulator program. 
* This class contains the main method that will be run.
*
* @author  Elijah Tungul (500885285)
* @since   2019-03-20 
*/

public class CarDealershipSimulator 
{
  public static void main(String[] args) throws IOException
  {
	  //The CarDealership object is created, along with references to an ArrayList of cars and last transaction.
	  CarDealership carDealership = new CarDealership();
		ArrayList<Car> cars = new ArrayList<Car>();
		String transaction = null;
		//File I/O with cars.txt
		try {
			Scanner fileIn = new Scanner(new File("cars.txt"));
			Car tempCar;
			ElectricCar tempElectric;
			while(fileIn.hasNext()) {
				String mfr = fileIn.next();
				String color = fileIn.next();
				Car.Model model = Car.Model.valueOf(fileIn.next());
				Vehicle.Power pwr = Vehicle.Power.valueOf(fileIn.next());
				double safetyRating = Double.parseDouble(fileIn.next());
				int maxRange = Integer.parseInt(fileIn.next());
				boolean AWD = (fileIn.next().equals("AWD")) ? true : false;
				double price = Double.parseDouble(fileIn.next());
				if(pwr == Vehicle.Power.GAS_ENGINE) {
					tempCar = new Car(mfr, color, model, maxRange, safetyRating, AWD, price);
					cars.add(tempCar);
				}
				else if(pwr == Vehicle.Power.ELECTRIC_MOTOR) {
					int recharge = Integer.parseInt(fileIn.next());
					tempElectric = new ElectricCar(mfr, color, model, safetyRating, maxRange, AWD, price, recharge);
					cars.add(tempElectric);
				}
			}
		}
		//Print the exception thrown at it
		catch(Exception e) {
			System.out.println(e);
		}
		//Introduction
		System.out.println("Welcome to Elijah's Car Dealership Simulator! Please enter a command.");
		//Scanner that reads user input
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			//Reads the whole line
			String command = in.nextLine();
			Scanner commandLine = new Scanner(command);
			try{
				//Reads the first word
				String word = commandLine.next();
				switch(word){
					case "L":	//Display inventory
						System.out.println("Inventory:");
						carDealership.displayInventory();
						break;
					case "Q": //Quits program
						System.out.println("Qutting program...");
						return;
					case "BUY": //Buys a car from the selected index
						try {
							int VIN = commandLine.nextInt();
							transaction = carDealership.buyCar(VIN);
							if(transaction.equals("ERROR: This VIN does not exist!")) {
								System.out.println(transaction);
							}
							else if(transaction != null){
								System.out.println("Purchase successful! Reciept:");
								System.out.println(transaction);
							}
						}
						//Catches InputMismatchException if index is not an integer 
						catch(InputMismatchException e){
							System.out.println("ERROR: Invalid VIN!");
						}
						//Catches NoSuchElementException no index is given
						catch(NoSuchElementException e){
							System.out.println("ERROR: No VIN was given!");
						}
						catch(IndexOutOfBoundsException e) {
							System.out.println("ERROR: No cars to buy!");
						}
						break;
					case "RET": //Returns the last car bought
						try {
							if(transaction != null){
								int transactionID = commandLine.nextInt();
								carDealership.returnCar(transactionID);
							}	
							//Prints out an error message if no car was recently purchased
							else{
								System.out.println("ERROR: No car was bought!");
							}
						}
						catch(NullPointerException e) {
							System.out.println("ERROR: The ID given does not exist!");
						}
						break;
					case "ADD": //Adds cars from the CarDealershipSimulator ArrayList
						System.out.println("Adding cars...");
						carDealership.addCars(cars);
						break;
					case "SPR": //Sorts cars by price using the Comparable interface from class Car
							System.out.println("Sorting cars by price...");
							carDealership.sortByPrice();
							break;
					case "SSR": //Sorts cars by safety rating using the Comparator interface from class CarDealership
							System.out.println("Sorting cars by safety rating...");
							carDealership.sortBySafetyRating();
							break;
					case "SMR": //Sorts cars by maximum range using the Comparator interface from class CarDealership
							System.out.println("Sorting cars by maximum range...");
							carDealership.sortByMaxRange();
							break;
					case "SVIN":
							System.out.println("Sorting cars by VIN...");
							carDealership.sortByVIN();
							break;
					case "FPR": //Filters price from a minimum to a maximum
						try{
							double min = commandLine.nextDouble();
							double max = commandLine.nextDouble();
							carDealership.filterByPrice(min, max);
						}
						//Catches InputMismatchException if min and max are not doubles
						catch(InputMismatchException e){
							System.out.println("ERROR: Invalid price range!");
						}
						//Catches NoSuchElementException when min and max are not given, or if min is given, but max is not
						catch(NoSuchElementException e){
							System.out.println("ERROR: No range was given!");
						}
						//Catches IllegalArgumentException thrown by filterByPrice() when the minimum is greater than the maximum
						catch(IllegalArgumentException e){
							System.out.println("ERROR: Invalid price range!");
						}
						break;
					case "FEL": //Filters electric cars
						System.out.println("Filtering cars by electric...");
						carDealership.filterByElectric();
						break;
					case "FAW": //Filters cars with AWD
						System.out.println("Filtering cars by AWD...");
						carDealership.filterByAWD();
						break;
					case "FCL": //Clears filters
						System.out.println("Clearing filters...");
						carDealership.filtersClear();
						break;
					case "SALES": //Sales command used for displaying transactions, team members, top salesperson, statistics, or sales of the month
						if(commandLine.hasNext()) {
							String salesCommand = commandLine.next();
							if(salesCommand.equals("TEAM")) { //Display sales team
								carDealership.displayTeam();
							}
							else if(salesCommand.equals("TOPSP")) { //Display top sales person
								carDealership.displayTopSales();
							}
							else if(salesCommand.equals("STATS")) { //Display accounting system stats
								carDealership.displayStats();
							}
							else {
								try {
									int month = Integer.parseInt(salesCommand);
									if(month > 1 && month < 12) {
										carDealership.displayTransactions(month); //Displays transactions by month
									}
								}
								catch(Exception e){
									System.out.println("ERROR: Not a valid month or command.");
								}
							}
						}
						else {
							carDealership.displayTransactions(); //Displays all transactions
						}
						break;
					default: //If word does not match any case, the user is told that the word is not a command
						System.out.println("ERROR: " + "\"" + word + "\"" + " is not a valid command.");
					}
				}
				//Catches NoSuchElementException when no command is given
				catch(NoSuchElementException e){
					System.out.println("ERROR: No command input!");
				}
		}
	}
}	
