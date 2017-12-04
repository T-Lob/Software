package human;

import java.util.ArrayList;
import healthServices.Consultation;
import others.*;


public class Physician extends HumanResource implements Observer{
	
	private String username;
	private ArrayList<Patient> currentPatients = new ArrayList<Patient>();
	private ArrayList<Patient> historyPatients = new ArrayList<Patient>();
	private ArrayList<String> messageBox = new ArrayList<String>();
	
	
	
	public Physician(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.name = "Physician" + String.valueOf(this.ID);
		this.surname = "Physician" + String.valueOf(this.ID);
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
		
	}
	public Physician(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = "Physician" + String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
	}

	public Physician(String EDname,String name, String surname, String username) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
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

	public ArrayList<Patient> getHistoryPatients() {
		return historyPatients;
	}

	public ArrayList<String> getMessageBox() {
		return messageBox;
	}
	
	public String getLastMessage() {
		return messageBox.get(messageBox.size()-1);
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting") & !(this.ED.getPhysicianList().get(1).contains(this))){
			this.ED.getPhysicianList().get(1).add(this);
			this.ED.getPhysicianList().get(0).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle") & !(this.ED.getPhysicianList().get(0).contains(this))) {
			this.ED.getPhysicianList().get(0).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("offduty") & !(this.ED.getPhysicianList().get(2).contains(this))){
			this.ED.getPhysicianList().get(2).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(0).remove(this);
		}
	}
	
	public void consultation (Patient patient, Consultation consultation) {
			this.setState("visiting");
			patient.getLocation().setState("full");
			this.getHistoryPatients().add(patient);
			this.getCurrentPatients().add(patient);
			patient.setPhysician(this);
			patient.setState("visited");
			
	}
	
	public void endOfConsultation (Patient patient, Consultation consultation) {
			consultation.result(patient);
			this.setState("idle");
	}
	
	public void verdict (Patient patient) {
		patient.setState("released");
		patient.setLocation(this.ED.waitingRoom);
		this.setState("idle");
		this.currentPatients.remove(patient);
	}
	
	@Override
	public void update(String outcome) {
		this.messageBox.add(outcome);
	}
}
