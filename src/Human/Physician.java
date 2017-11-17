package Human;

import java.util.ArrayList;
import healthServices.Consultation;
import others.Database;
import others.IDGenerator;


public class Physician extends HumanResource {
	
	private String username;
	private ArrayList<Patient> currentPatients = new ArrayList<Patient>();
	private ArrayList<Patient> historyPatients = new ArrayList<Patient>();
	private ArrayList<String> messageBox = new ArrayList<String>();
	
	
	
	public Physician() {
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToPhysicianList(this);
		
	}
	public Physician(String name) {
		super();
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToPhysicianList(this);
	}

	public Physician(String name, String surname, String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
		Database.addToPhysicianList(this);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<Patient> getCurrentPatients() {
		return currentPatients;
	}
	
	public void addToCurrentPatients(Patient patient) {
		this.currentPatients.add(patient);
	}
	public void removeFromCurrentPatients(Patient patient) {
		this.currentPatients.remove(patient);
	}

	public ArrayList<Patient> getHistoryPatients() {
		return historyPatients;
	}
	public void addToHistoryPatients(Patient patient) {
		this.historyPatients.add(patient);
	}

	public ArrayList<String> getMessageBox() {
		return messageBox;
	}
	public String getLastMessage() {
		return messageBox.get(messageBox.size()-1);
	}
	public void  addToMessageBox(String message) {
		this.messageBox.add(message);
	}
	
	public void consultation (Patient patient) {
		if (this.timeOfAvailability <= Database.getTime()) { // The physician can be idle for several minutes if no patient shows up
			this.setTimeOfAvailability(Database.getTime()); // We set the time to the time of the beginning of the consultation
			this.setState("visiting");
			Consultation consultation = new Consultation(); 
			this.addToHistoryPatients(patient);
			this.addToCurrentPatients(patient);
			patient.setPhysician(this);
			patient.setState("visited");
			this.timeOfAvailability += consultation.getDuration();
		}
	
	
		else {
			/* The patient is being visited by the physician, nothing to do
			unless a High severity patient comes and takes his place => To implement */
			
		}
	}
	
	public void endConsultation (Patient patient) {
		if (this.timeOfAvailability == Database.getTime()) {
			Consultation.result(patient);
			this.setState("idle");
			
			}
		}
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting")){
			Database.getPhysicianList().get(1).add(this);
			Database.getPhysicianList().get(0).remove(this);
			Database.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle")) {
			Database.getPhysicianList().get(0).add(this);
			Database.getPhysicianList().get(1).remove(this);
			Database.getPhysicianList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			Database.getPhysicianList().get(2).add(this);
			Database.getPhysicianList().get(1).remove(this);
			Database.getPhysicianList().get(0).remove(this);
			}
		
}
	}
