package events;

import human.Patient;
import others.Database;
import others.IDGenerator;

public class Registration extends Event {
	
	public Registration(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGenerator.getInstance().getNextID();
		this.name=("RegistrationTriage" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
	}
	
	public void execute () {
		for (Patient patient:this.ed.getGeneratedPatients()) {
			this.ed.getNurseList().get(0).get(0).registration(patient);
		}	
	}
}
