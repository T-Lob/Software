package human;
import java.util.ArrayList;

import events.PatientArrival;
import maths.Uniform;
import others.Database;
import others.ED;
import others.IDGenerator;
import rooms.Room;

/**
 * The patient is defined by different variables:
 * <br>name
 * <br>surname
 * <br>ID
 * <br>health insurance
 * <br>security level
 * <br>state (generated by default)
 * <br>location (null by default)
 * <br>history (empty arraylist by default)
 * <br>arrival time
 * <br>assigned physician (null by default)
 * <br>bill
 * <br>destination
 * <br>ED
 *
 */
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
	private double ConsultationTime;
	private double releaseTime;
	private Physician physician = null;
	private int bill = 0;
	private Room destination;
	private ED ED;
	
	
	/**
	 * This constructs a patient with given name and surname and trigger the PatientArrival event before adding it 
	 * to the GeneratedPatients list
	 * @param EDname The ED in which you wish to construct this patient
	 * @param name the name of this patient
	 * @param surname the surname of this patient
	 * @param healthInsurance the kind of health insurrance the patient has
	 * @param severityLevel The security level of this patient
	 * @param arrivalTime The time at which this patient arrived to the ED
	 */
	public Patient(String EDname,String name,String surname,String healthInsurance, String severityLevel,double arrivalTime) {
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
	
	public Patient(String EDname,String name,String surname,String healthInsurance) {
		this.ED=Database.getEDbyName(EDname);
		this.name=name;
		this.surname=surname;
		this.ID=IDGenerator.getInstance().getNextID();
		this.healthInsurance = healthInsurance;
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	
	/**
	 * This constructs a patient with generic name and surname and trigger the PatientArrival event before adding it 
	 * to the GeneratedPatients list
	 * @param EDname The ED in which you wish to construct this patient
	 * @param severityLevel The security level of this patient
	 * @param arrivalTime The time at which this patient arrived to the ED
	 */
	public Patient(String EDname, String severityLevel,double arrivalTime) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name = "Patient" + String.valueOf(this.ID);
		this.surname = "Patient" + String.valueOf(this.ID);
		this.severityLevel = severityLevel;
		this.arrivalTime=arrivalTime;
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	
	/**
	 * This constructs a patient with generic name and surname and trigger the PatientArrival event before adding it 
	 * to the GeneratedPatients list
	 * The patient arrival time and security level are ramdomly selected.
	 * @param EDname The ED in which you wish to construct this patient
	 */
	public Patient(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name = "Patient" + String.valueOf(this.ID);
		this.surname = "Patient" + String.valueOf(this.ID);
		this.severityLevel = "L"+String.valueOf((int) Math.floor(new Uniform(1,5).getSample()));
		this.arrivalTime=new Uniform(495,500).getSample();
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	public Patient(String EDname, String severityLevel) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name = "Patient" + String.valueOf(this.ID);
		this.surname = "Patient" + String.valueOf(this.ID);
		this.severityLevel = severityLevel;
		this.arrivalTime=new Uniform(495,500).getSample();
		this.ED.addToEventQueue(new PatientArrival(EDname, this));
		this.ED.getGeneratedPatients().add(this);
		
	}
	
	/**
	 * 
	 * @return The name of this patient
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this patient
	 * @param name The name which you wish to give to this patient
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return The suname of this patient
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Sets the surname of this patient
	 * @param surname The surname which you wish to give to this patient
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * 
	 * @return The ID of this patient
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @return The healthinsurance of this patient
	 */
	public String getHealthInsurance() {
		return healthInsurance;
	}
	
	/**
	 * Sets the health insurance type of this patient.
	 * @param healthInsurance The health insurance which you wish to give to this patient.
	 */
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	/**
	 * 
	 * @return The securityLevel of this patient
	 */
	public String getSeverityLevel() {
		return severityLevel;
	}
	
	/**
	 * 
	 * @return The state of this patient
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @return The room in which this patient is located
	 */
	public Room getLocation() {
		return location;
	}
	
	/**
	 * set location of this patient and the state of the room he is leaving.
	 * @param location null or room in which th patient is located.
	 */
	public void setLocation(Room location) {
		if (this.location != null) {
				if (this.location.getState()!="onlyPatient") {
					this.location.setState("empty");
			}
		}
		this.location=location;
	}
	
	/**
	 * Prints the history of this patient, line by line.
	 */
	public void getHistory() {
		for (String[] L: this.history) {
			System.out.println("State: " + L[0] + " - Time: "  + L[1]);	
		}
	}
	
	/**
	 * adds an event to the history oh this patient
	 * @param event the event which is to add to the history
	 */
	public void addEventToHistory(String[] event) {
		this.history.add(event);
	}
	
	/**
	 * 
	 * @return The arrival time of this patient
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}	

	/**
	 * Sets the arrival time of this patient
	 * @param arrivalTime arrival time of this patient to the ED
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * 
	 * @return The physician assigned to this patient.
	 */
	public Physician getPhysician() {
		return physician;
	}

	/**
	 * This is used to set the physician assigned to this patient
	 * @param physician the physician which is to be assigned
	 */
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	/**
	 * 
	 * @return The amount of this patient's bill.
	 */
	public int getBill() {
		return bill;
	}

	/**
	 * this increments the bill of this patient 
	 * @param cost cost which is to add to this patient's bill.
	 */
	public void addToBill(int cost) {
		this.bill= this.bill+cost;
	}
	
	/**
	 * 
	 * @return The destination of this patient
	 */
	public Room getDestination() {
		return destination;
	}

	/**
	 * This is used to set the destination of this patient.
	 * @param destination The destination of this patient.
	 */
	public void setDestination(Room destination) {
		this.destination = destination;
	}
	
	/**
	 * 
	 * @return The security level of this patient
	 */
	public Integer getLevel() {
		return Integer.parseInt(this.severityLevel.substring(severityLevel.length()-1));
	}

	/**
	 * This is used to calculate the price which is to be payed by the patient, according to its type of health insurrance.
	 * @return the final price of the bill
	 */
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
	
	/**
	 * This is used to set the state of the patient. It works by removing the patien from all lists before adding it to the one requested
	 * @param state the state in which you wish to set this patient
	 */
	public void setState(String state) {
		this.state=state;
		this.ED.getGeneratedPatients().remove(this);
		this.ED.getRegisteredPatients().get(this.getLevel()-1).remove(this);
		this.ED.getArrivedPatients().remove(this);
		this.ED.getWaitingForTransportPatients().remove(this);
		this.ED.getWaitingForVerdictPatients().remove(this);
		String [] event= {state,String.valueOf(Database.getTime())};
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

	public double getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(double releaseTime) {
		this.releaseTime = releaseTime;
	}

	public double getConsultationTime() {
		return ConsultationTime;
	}

	public void setConsultationTime(double ConsultationTime) {
		this.ConsultationTime = ConsultationTime;
	}
	
	public double LOS() {
		return releaseTime-arrivalTime;
	}
	public double DTDT() {
		return ConsultationTime-arrivalTime;
	}
}
