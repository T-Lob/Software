import java.util.ArrayList;

import others.IDGenerator;


public class Patient {
	
	private String name = "";
	private String surname = "";
	private int ID;
	private String healthInsurance="None";
	private String severityLevel;
	private String state = null;
	private Room location = null;
	private ArrayList<ArrayList<String>> history = new ArrayList<ArrayList<String>> () ;
	private int arrivalTime;
	private Physician physician = null;
	private int bill;
	private Room destination;
	
	
	

	public Patient(String name, String surname, String healthInsurance, String severityLevel, String state,
			Room location, ArrayList<ArrayList<String>> history, int arrivalTime, Physician physician) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.healthInsurance = healthInsurance;
		this.severityLevel = severityLevel;
		this.state = state;
		this.location = location;
		this.history = history;
		this.arrivalTime = arrivalTime;
		this.physician = physician;
		Database.addToGeneratedPatients(this);
	}

	public Patient(String name,String surname,String severityLevel,int arrivalTime) {
		this.name=name;
		this.surname=surname;
		this.ID=IDGenerator.getInstance().getNextID();
		this.severityLevel = severityLevel;
		this.state = "arrived";
		// this.location = WaitingRoom;  peut-etre un singleton waiting room ?
		this.physician=null;
		Database.addToGeneratedPatients(this);
		
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

	public void setState(String state) {
		this.state = state;
	}
	public Room getLocation() {
		return location;
	}
	public void setLocation(Room location) {
		this.location = location;
	}
	public ArrayList<ArrayList<String>> getHistory() {
		return history;
	}
	public void addEventToHistory(ArrayList<String> event) {
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
	
	
}
