package Human;
import java.util.ArrayList;

import Resources.Room;
import others.Database;
import others.IDGenerator;


public class Patient {
	
	private String name = "";
	private String surname = "";
	private int ID;
	private String healthInsurance="None";
	private String severityLevel;
	private String state = "arrived";
	private Room location = null;
	private ArrayList<String[]> history = new ArrayList<String[]> () ;
	private int arrivalTime;
	private Physician physician = null;
	private int bill;
	private Room destination;
	
	
	

	public Patient(String name,String surname,String severityLevel,int arrivalTime) {
		this.name=name;
		this.surname=surname;
		this.ID=IDGenerator.getInstance().getNextID();
		this.severityLevel = severityLevel;
		this.arrivalTime=arrivalTime;
		Database.addToGeneratedPatients(this);
		
	}	
	public Patient() {
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getID() {
		return ID;
	}

	public String getHealthIsurance() {
		return healthInsurance;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}
	public String getState() {
		return state;
	}

	public Room getLocation() {
		return location;
	}
	public void setLocation(Room location) {
		this.location.setState("empty");
		this.location=location;
	}
	public ArrayList<String[]> getHistory() {
		return history;
	}
	public void addEventToHistory(String[] event) {
		this.history.add(event);
	}
	public int getArrivalTime() {
		return arrivalTime;
	}	

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	
	public int getBill() {
		return bill;
	}

	public void addToBill(int cost) {
		this.bill= this.bill+cost;
	}
	public Room getDestination() {
		return destination;
	}

	public void setDestination(Room destination) {
		this.destination = destination;
	}
	public Integer getLevel() {
		return Integer.parseInt(this.severityLevel.substring(severityLevel.length()-1));
	}

	public double charges() {
		int S= this.getBill();
		switch(this.healthInsurance) {
		case "Gold":
			return S/2;
		case "Silver":
			return 4*S/5;
		case "":
			return S;
		}
		return 0;
	}
	
public void setState(String state) {
		this.state=state;
		String [] event= {state,String.valueOf(Database.getTime())};
		this.addEventToHistory(event);
		
		if (state.equalsIgnoreCase("registered")){
			Database.addToRegisteredPatients(this);
			
		} else if (state.equalsIgnoreCase("waitingForTransport")){
			Database.addToWaitingForTransportPatients(this);
			
		} else if (state.equalsIgnoreCase("waitingForVerdict")) {
			Database.addToWaitingForVerdictPatients(this);
			
		} else if (state.equalsIgnoreCase("released")){
			Database.addToReleasedPatients(this);}
	
	
}
}
