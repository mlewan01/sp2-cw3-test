package classes;

import java.util.Random; //generate

public class Customer {
	
	private int id;
	private int currentFloor;
	private int destinationFloor;
	private boolean inElevator;
	
	public Customer(int id, int f) {

		this.id = id;
		Random ran = new Random();
		
		
		int i = ran.nextInt(f+1);
		int j = ran.nextInt(f+1);
		
		
		if(i == 13) {
			currentFloor = i + 1;
		}else {
		
			currentFloor = i;
		}
		
		if(j == 13) {
			destinationFloor = j + 1;
		} else {
			destinationFloor = j;
		}
		if(i==j){
			System.out.println(this.id + " current and destination were the same !!");
			destinationFloor = (i+j+3)%f;
		}

	}
	
	public int getId() {
		return id;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(int i){
		this.currentFloor = i;
	}
	
	public int getDestinationFloor() {
		return destinationFloor;
	}
	public void setDestinationFloor(int i){
		this.destinationFloor = i;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentFloor;
		result = prime * result + destinationFloor;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other && other != null) { return true; }
		if (getClass() != other.getClass()) { return false; }
		Customer obj = (Customer) other;
		if (currentFloor != obj.currentFloor || 
			destinationFloor != obj.destinationFloor|| id != obj.id) { return false; }
		return true;
	}

	@Override
	public String toString() {
		return "\nCustomer id: " + id + "\tCurrent floor: " + currentFloor + 
				"\tDestination floor: " + destinationFloor;
	}
	
}
