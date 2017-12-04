package events;

import java.util.ArrayList;

import human.Nurse;
import human.Patient;
import others.Database;
import others.IDGenerator;
import others.IDGeneratorEvent;

public class Registration extends Event {
	private Nurse nurse;
	
	public Registration(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("Registration" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.type= "Registration";
	}
	@Override
	public void execute () {
		this.nurse=ed.getNextNurse();
		for (Patient patient:new ArrayList<Patient>(this.ed.getArrivedPatients())) {
			nurse.registration(patient);
			this.ed.getNewEnabledEvents().remove("Registration");
		}	
	}
}
