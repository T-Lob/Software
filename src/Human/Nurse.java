package Human;


import Resources.Room;
import others.*;


public class Nurse extends HumanResource {
	private Patient currentPatient;
	
	public Nurse(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToNurseList(this);
	}
	public Nurse(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToNurseList(this);
	}
	public Nurse(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToNurseList(this);
		
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("busy")){
			this.ED.getNurseList().get(1).add(this);
			this.ED.getNurseList().get(0).remove(this);
			this.ED.getNurseList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle")) {
			this.ED.getNurseList().get(0).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			this.ED.getNurseList().get(2).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(0).remove(this);
			}
		
}
	
	public void registration (Patient patient) {
		patient.setState("registrated");
		
	}
	public void installation (Patient patient, Room room) {
		if (this.timeOfAvailability <= this.ED.getTime()) {
			this.setTimeOfAvailability(this.ED.getTime());
			this.setState("busy");
			patient.setState("transported");
			patient.setLocation(room);
			room.setState("onlypatient");
			room.setPatient(patient);
			this.setCurrentPatient(patient);
			this.timeOfAvailability += 2;
		}
	}
	public void endOfInstallation (Patient patient) {
		if (this.timeOfAvailability == this.ED.getTime()) {
			this.setState("idle");
			this.setCurrentPatient(null);
			patient.setState("waiting");
		}
	
}
}



