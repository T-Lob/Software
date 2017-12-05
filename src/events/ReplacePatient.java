package events;

import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class ReplacePatient extends Event {
	private Patient patient;
	public ReplacePatient(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name = "Replace Patient" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "ReplacePatient";
		this.patient=Database.remplacedPatient;
	}
	

	@Override
	public void execute() {
		 patient.setState("registered");
		 ed.getRegisteredPatients().get(patient.getLevel()-1).remove(patient);
		 ed.getRegisteredPatients().get(patient.getLevel()-1).add(0,patient);
		 
		 patient.getLocation().setState("empty");
		 patient.setLocation(ed.waitingRoom);
		 this.ed.getNewEnabledEvents().remove("ReplacePatient");
	}

}
