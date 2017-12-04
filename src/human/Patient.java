package human;
import java.util.ArrayList;

import events.PatientArrival;
import maths.Uniform;
import others.Database;
import others.ED;
import others.IDGenerator;
import rooms.Room;


public class Patient {
	
	private String name = "";
	private String surname = "";
	private int ID;
	private String healthInsurance="None";
	private String severityLevel;
	private String state = "generated";
	private Room location = null;
	private ArrayList<String[]> history = new ArrayList<String[]> () ;
	private double arrivalTime;
	private Physician physician = null;
	private int bill = 0;
	private Room destination;
	private ED ED;
	
	
	

	public Patient(String EDname,String name,String surname,String healthInsurance, String severityLevel,int arrivalTime) {
		this.ED=Database.getEDbyName(EDname);
		this.name=name;
		this.surname=surname;
		this.ID=IDGenerator.getInstance().getNextID();
		this.healthInsurance = healthInsurance;
		this.severityLevel = severityLevel;
		this.arrivalTime=arrivalTime;
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	
	public Patient(String EDname, String severityLevel,int arrivalTime) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name = "Patient" + String.valueOf(this.ID);
		this.surname = "Patient" + String.valueOf(this.ID);
		this.severityLevel = severityLevel;
		this.arrivalTime=arrivalTime;
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	
	public Patient(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name = "Patient" + String.valueOf(this.ID);
		this.surname = "Patient" + String.valueOf(this.ID);
		this.severityLevel = "L"+String.valueOf((int) Math.floor(new Uniform(1,5).getSample()));
		this.arrivalTime=new Uniform(700,710).getSample();
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
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

	public String getHealthInsurance() {
		return healthInsurance;
	}
	
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
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
		if (this.location != null) {
				if (this.location.getState()!="onlyPatient") {
					this.location.setState("empty");
			}
		}
		this.location=location;
	}
	
	public void getHistory() {
		for (String[] L: this.history) {
			System.out.println("State: " + L[0] + " - Time: "  + L[1]);	
		}
	}
	
	public void addEventToHistory(String[] event) {
		this.history.add(event);
	}
	
	public double getArrivalTime() {
		return arrivalTime;
	}	

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
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
		case "None":
			return S;
		}
		return 0;
	}
	
	public void setState(String state) {
		this.state=state;
		this.ED.getGeneratedPatients().remove(this);
		this.ED.getRegisteredPatients().get(this.getLevel()-1).remove(this);
		this.ED.getArrivedPatients().remove(this);
		this.ED.getWaitingForTransportPatients().remove(this);
		this.ED.getWaitingForVerdictPatients().remove(this);
		String [] event= {state,String.valueOf(this.ED.getTime())};
		this.addEventToHistory(event);
		
		if (state.equalsIgnoreCase("registered")){
			this.ED.getRegisteredPatients().get(this.getLevel()-1).add(this);
			
		} else if (state.equalsIgnoreCase("arrived")) {
			this.ED.getArrivedPatients().add(this);
		
		} else if (state.equalsIgnoreCase("waitingForTransport")){
			this.ED.getWaitingForTransportPatients().add(this);
			
		} else if (state.equalsIgnoreCase("waitingForVerdict")) {
			this.ED.getWaitingForVerdictPatients().add(this);
			
		} else if (state.equalsIgnoreCase("released")){
			this.ED.getReleasedPatients().add(this);}
	
	
	}
}
