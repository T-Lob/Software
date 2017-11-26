package events;

import human.Nurse;
import human.Patient;
import others.Database;
import others.IDGenerator;
import rooms.Room;

public class Installation extends Event{
	
	private Nurse nurse;
	private Patient patient;
	private Room room;
	
	public Installation(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Patient Installation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "Installation";
	}
	
	public Installation(String edName, Patient patient, Nurse nurse, Room room) {
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Patient Installation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.nurse = nurse;
		this.patient = patient;
		this.room = room;
		this.type= "Installation";
	}
	
	@Override
	public void execute() {
		nurse.installation(patient, room);
	}
}
