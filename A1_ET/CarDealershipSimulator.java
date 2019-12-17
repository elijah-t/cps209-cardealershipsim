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
  public static void main(String[] args)
  {
	  //The CarDealership object is created, along with references to an ArrayList of cars and last car bought.
	  CarDealership carDealership = new CarDealership();
		ArrayList<Car> cars = new ArrayList<Car>();
		Car lastCarBought = null;

		//Car objects are created based on cars.txt.
		//Toyota     blue   SEDAN   GAS_ENGINE      9.5  500 2WD 25000
		Car car0 = new Car("Toyota", "blue", Car.Model.SEDAN, 500, 9.5, false, 25000);
		//Honda      red    SPORTS  GAS_ENGINE      9.2  450 2WD 30000
		Car car1 = new Car("Honda", "red", Car.Model.SPORTS, 450, 9.2, false, 30000);
		//Kia        white  MINIVAN GAS_ENGINE      9.7  550 2WD 20000
		Car car2 = new Car("Kia", "white", Car.Model.MINIVAN, 450, 9.2, false, 30000);
		//BMW        black  SEDAN   GAS_ENGINE      9.6  600 AWD 55000
		Car car3 = new Car("BMW", "black", Car.Model.SEDAN, 600, 9.6, true, 55000);
		//Tesla      red    SEDAN   ELECTRIC_MOTOR  9.1  425 AWD 85000  30
		Car car4 = new ElectricCar("Tesla", "red", Car.Model.SEDAN, 9.1, 425, true, 85000, 30);
		//Chevy      red    MINIVAN GAS_ENGINE      9.25 475 2WD 40000
		Car car5 = new Car("Chevy", "red", Car.Model.MINIVAN, 475, 9.25, false, 40000);
		//ChevyVolt  green  SEDAN   ELECTRIC_MOTOR  8.9  375 AWD 37000  45
		Car car6 = new ElectricCar("ChevyVolt", "green", Car.Model.SEDAN, 8.9, 375, true, 37000, 45);
		//Bentley    black  SEDAN   GAS_ENGINE      9.8  575 2WD 150000
		Car car7 = new Car("Bentley", "black", Car.Model.SEDAN, 575, 9.8, false, 150000);
		//NissanLeaf green  SEDAN   ELECTRIC_MOTOR  8.8  325 AWD 32000  55
		Car car8 = new ElectricCar("NissanLeaf", "green", Car.Model.SEDAN, 8.8, 325, true, 32000, 55);
		
		//The cars are then added to the ArrayList previously mentioned (though not the same ArrayList as in class CarDealership!)
		cars.add(car0);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		cars.add(car6);
		cars.add(car7);
		cars.add(car8);
		
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
							int index = commandLine.nextInt();
							lastCarBought = carDealership.buyCar(index);
							if(lastCarBought != null){
								System.out.println("Purchase successful! You have bought:");
								System.out.println(lastCarBought.display());
							}
						}
						//Catches InputMismatchException if index is not an integer 
						catch(InputMismatchException e){
							System.out.println("ERROR: Invalid buy index!");
						}
						//Catches NoSuchElementException no index is given
						catch(NoSuchElementException e){
							System.out.println("ERROR: No index was given!");
						}
						//Catches IndexOutOfBoundsException thrown by buyCar() if the index is out of bounds
						catch(IndexOutOfBoundsException e){
							System.out.println("ERROR: Index is out of bounds!");
						}
						break;
						case "RET": //Returns the last car bought
						if(lastCarBought != null){
							System.out.println("Returning last car bought...");
							carDealership.returnCar(lastCarBought);
							lastCarBought = null;
						}
						//Prints out an error message if no car was recently purchased
						else{
							System.out.println("ERROR: No car was bought!");
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
