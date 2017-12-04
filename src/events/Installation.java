package events;

import human.Nurse;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;
import rooms.Room;

public class Installation extends Event{
	
	private Nurse nurse;
	private Patient patient;
	private Room room;
	
	public Installation(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name = "Patient Installation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "Installation";
	}
	
	public Installation(String edName, Patient patient, Nurse nurse, Room room) {
		this.ed = Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name = "Patient Installation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.nurse = nurse;
		this.patient = patient;
		this.room = room;
		this.type= "Installation";
	}
	
	public Nurse getNurse() {
		return nurse;
	}

	public Patient getPatient() {
		return patient;
	}

	public Room getRoom() {
		return room;
	}

	@Override
	public void execute() {
		this.nurse=this.ed.getNextNurse();
		this.patient=this.ed.getNextRegisteredPatient();
		if (this.patient.getLevel()<=2 & this.ed.getNextEmptyShockRoom() != null) {
			this.room=this.ed.getNextEmptyShockRoom();
		} else {
			this.room=this.ed.getNextEmptyBoxRoom();
		}
		nurse.installation (patient,room);
		this.ed.addToEventQueue(new EndOfInstallation(this));
		this.ed.getNewEnabledEvents().remove("Installation");
	}
}
