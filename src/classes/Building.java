package classes;

import java.util.ArrayList;

/**
 * The Building class stores the number of floors and and the number of customers
 * that will be used in the Elevator program.
 * The class constructor Building() takes two parameters, 
 * numberOfFloors and numberOfCustomers
 * @author Mustapha Benbaziz
 * @version 1.0
 * 
 * 
 */
public class Building {
	
	private int numberOfFloors;
	private ArrayList<Customer> customerList; // list of customers in the Building 
	private int numberOfCustomers; 
	private Elevator elevator;
	int o = 1; // to indicate number of operations for easy output reading
	int[][] table = {{15,5},{15,10},{14,4},{11,14},{4,0},{15,10},{5,0}};
	
	/**
	 * Constructs instances of Building
	 * @param f represents the number of floors, to be decided by the user
	 * @param c represents the number of customers, to
	 */
	public Building(int f, int c) {
		
		this.numberOfFloors = f; // Number of floors in the building
		numberOfCustomers = c;   // Number of customers in the building
		
		ArrayList<Customer> myList = new ArrayList<>(); // Create ArrayList of type Customer
		
		for(int j = 1; j <= c; j++) {
			Customer d = new Customer(j, numberOfFloors);
			//d.setCurrentFloor(table[j-1][0]);
			//d.setDestinationFloor(table[j-1][1]);
			myList.add(d);
			//myList.add(new Customer(j, numberOfFloors)); // Add instances of Customer to ArrayList
			
		}

		this.customerList = myList; // Store instances of Customer in myList
		
		this.setElevator();
	}
	
public static void main(String[] args) {
		Building theHyde = new Building(15, 7);
		System.out.println(theHyde.getCustomerList());
		System.out.println("===============================================================");
		System.out.println("Floors: " + theHyde.getNumberOfFloors());
		System.out.println("Customers: " + theHyde.getNumberOfCustomers());
		System.out.println("===============================================================");
		theHyde.defautlStrategy();
	}
	
	public int getNumberOfFloors() {
		return numberOfFloors;
	}
	
	public int getNumberOfCustomers() {
		return numberOfCustomers;
	}
	
	public void setElevator() {
		elevator = new Elevator(this.getNumberOfFloors());
	}
	
	public Elevator getElevator() {
		return elevator;	
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	/**
	 * moves the elevator from the bottom to the top of the building.
	 * Checks (it will eventually) on each floor for customers willing to enter the lift and also checks if
	 * any of the customers wants to leave the elevator
	 */
	public void defautlStrategy() {
		elevator.setDirection(1);
		for(int i = 0; i <= elevator.getNumOfFloors(); i++) { // sign = causes the floor error
			if(i == 13) {
				continue;
			}
			this.checkFloor(i);
			if(i<elevator.getNumOfFloors())elevator.move();
		}
		System.out.println("---------------------------------------------");
		elevator.setDirection(-1);
		for(int i = elevator.getNumOfFloors(); i >= 0; i--) {// sign = causes the floor error
			if(i == 13) {
				continue;
			}
			this.checkFloor(i);
			if(i>0)elevator.move();	
		}
		// just some tests...
		System.out.println("======================================================");
		System.out.println("elevator register list size: " + elevator.registerList.size());
		System.out.println(elevator.registerList);
		System.out.println("building customer list size: " + this.customerList.size());
		System.out.println(this.customerList);
	}
	public void checkFloor(int f){
		//int o,  to indicate number of operations for easier output reading
		//System.out.println("this is \"f\" inside checkFloor: "+f);
		for(int i = 0; i < this.customerList.size(); i++){
			Customer c = this.customerList.get(i);
			if(c.getCurrentFloor() == f){
				//System.out.println(i);
				System.out.print(o+". "); 
				o++;
				System.out.print("customer " + c.getId() + " enters on the floor nr: "+ f);
				//if(this.customerList.remove(c)) System.out.println(c);
				//elevator.registerList.add(c);
				customerJoinsElevator(c);
				this.customerList.remove(c);
				System.out.println("    b_list: "+this.customerList.size()+"  e_list: "+elevator.registerList.size());
				i--;
			}
		}
		for(int i = 0; i < elevator.registerList.size(); i++){
			Customer c = elevator.registerList.get(i);
			//elevator.registerList.
			if(c.getDestinationFloor() == f){
				System.out.println(i);
				System.out.print(o+". "); 
				o++;
				System.out.println("customer " + c.getId() + " exits on floor nr: " + f);
				//if(elevator.registerList.remove(c)) System.out.println(c);
				customerLeavesElevator(c);
				//elevator.registerList.remove(i);
				i--;
			}
		}
	}
	/**
	 * The method to add a Customer to the Elevator's registeList
	 * @param c a Customer to be added to the registerList
	 */
	public void customerJoinsElevator(Customer c){
		if(elevator.registerList.contains(c)){
			System.out.println("Error. It apears that the customer already is in the Elevator...");
			return;
		}else{
			elevator.registerList.add(c);
		}
	}
	/**
	 * The method to remove a Customer from the Elevator's registerList
	 * @param c a Customer to be removed from the registerList
	 */
	public void customerLeavesElevator(Customer c){
		if(elevator.registerList.contains(c)) {
			elevator.registerList.remove(c);
		}else{
			System.out.println("Error. The customer is not anymore in the Elevator...");
		}
	}	
}
