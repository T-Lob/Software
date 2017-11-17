package Human;


import Resources.Room;
import others.Database;
import others.IDGenerator;

public class Nurse extends HumanResource {
	private Patient currentPatient;
	
	public Nurse() {
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToNurseList(this);
	}
	public Nurse(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToNurseList(this);
	}
	public Nurse(String name) {
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToNurseList(this);
		
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting")){
			Database.getNurseList().get(1).add(this);
			Database.getNurseList().get(0).remove(this);
			Database.getNurseList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("busy")) {
			Database.getNurseList().get(0).add(this);
			Database.getNurseList().get(1).remove(this);
			Database.getNurseList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			Database.getNurseList().get(2).add(this);
			Database.getNurseList().get(1).remove(this);
			Database.getNurseList().get(0).remove(this);
			}
		
}
	
	public void registration (Patient patient) {
		patient.setState("registrated");
		
	}
	public void installation (Patient patient, Room room) {
		if (this.timeOfAvailability <= Database.getTime()) {
			this.setTimeOfAvailability(Database.getTime());
			this.setState("busy");
			patient.setState("transported");
			room.setState("onlypatient");
			this.timeOfAvailability += 2;
		}
	}
	public void endOfInstallation (Patient patient) {
		if (this.timeOfAvailability == Database.getTime()) {
			this.setState("idle");
			patient.setState("waiting");
		}
	
}
}



