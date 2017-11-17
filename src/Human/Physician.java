package Human;

import java.util.ArrayList;
import healthServices.Consultation;
import others.*;


public class Physician extends HumanResource {
	
	private String username;
	private ArrayList<Patient> currentPatients = new ArrayList<Patient>();
	private ArrayList<Patient> historyPatients = new ArrayList<Patient>();
	private ArrayList<String> messageBox = new ArrayList<String>();
	
	
	
	public Physician(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToPhysicianList(this);
		
	}
	public Physician(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToPhysicianList(this);
	}

	public Physician(String EDname,String name, String surname, String username) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
		this.ED.addToPhysicianList(this);
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
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting")){
			this.ED.getPhysicianList().get(1).add(this);
			this.ED.getPhysicianList().get(0).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle")) {
			this.ED.getPhysicianList().get(0).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			this.ED.getPhysicianList().get(2).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(0).remove(this);
			}
		
}
	
	public void consultation (Patient patient) {
		if (this.timeOfAvailability <= this.ED.getTime()) { // The physician can be idle for several minutes if no patient shows up
			this.setTimeOfAvailability(this.ED.getTime()); // We set the time to the time of the beginning of the consultation
			this.setState("visiting");
			Consultation consultation = new Consultation(this.ED.getEDname()); 
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
		if (this.timeOfAvailability == this.ED.getTime()) {
			new Consultation(this.ED.getEDname()).result(patient);
			this.setState("idle");
			
			}
		}
	public void verdict (Patient patient) {
		patient.setState("released");
		this.currentPatients.remove(patient);
	}
	
	}
