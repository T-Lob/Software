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
		 ed.getRegisteredPatients().get(patient.getLevel()-1).add(0,patient);
		 patient.setState("registered");
		 int index = ed.getRegisteredPatients().get(patient.getLevel()-1).indexOf(patient);
		 ed.getRegisteredPatients().get(patient.getLevel()-1).remove(index);
		 ed.getRegisteredPatients().get(patient.getLevel()-1).add(0,patient);
		 patient.setLocation(ed.waitingRoom);
		 this.ed.getNewEnabledEvents().remove("ReplacePatient");
	}

}
