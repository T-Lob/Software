package human;


import others.*;
import rooms.Room;


public class Nurse extends HumanResource {
	private Patient currentPatient;
	
	public Nurse(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.name = "Nurse"+String.valueOf(this.ID);
		this.surname = "Nurse"+String.valueOf(this.ID);
		this.state = "idle";
		this.ED.getNurseList().get(0).add(this);
	}
	
	public Nurse(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getNurseList().get(0).add(this);
	}
	
	public Nurse(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = "Nurse"+String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getNurseList().get(0).add(this);
		
	}

	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("busy")  & !(this.ED.getNurseList().get(1).contains(this))){
			this.ED.getNurseList().get(1).add(this);
			this.ED.getNurseList().get(0).remove(this);
			this.ED.getNurseList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("idle") & !(this.ED.getNurseList().get(0).contains(this))) {
			this.ED.getNurseList().get(0).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty") & !(this.ED.getNurseList().get(2).contains(this))){
			this.ED.getNurseList().get(2).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(0).remove(this);
		}
	}
	
	public void registration (Patient patient) {
		patient.setState("registered");
		patient.setLocation(ED.waitingRoom);
	}
	
	public void installation (Patient patient, Room room) {
		this.setState("busy");
		this.setCurrentPatient(patient);	
		patient.setState("transported");
		patient.setDestination(room);
		room.setState("onlyPatient");
		room.setPatient(patient);
	}
	
	public void endOfInstallation (Patient patient) {
		this.setState("idle");
		this.setCurrentPatient(null);
		patient.setLocation(patient.getDestination());
		patient.setDestination(null);
		patient.setState("waiting");
	}
}



