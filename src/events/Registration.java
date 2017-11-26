package events;

import human.Patient;
import others.Database;
import others.IDGenerator;

public class Registration extends Event {
	
	public Registration(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGenerator.getInstance().getNextID();
		this.name=("Registration" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.type= "Registration";
	}
	@Override
	public void execute () {
		for (Patient patient:this.ed.getArrivedPatients()) {
			this.ed.getNextNurse().registration(patient);
		}	
	}
}
