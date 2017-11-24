package events;

import human.Patient;
import others.Database;
import others.IDGenerator;

public class PatientArrival extends Event {
	
	private Patient patient;
	
	public PatientArrival(String edName, Patient patient){
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Patient Arrival " + String.valueOf(id);
		this.patient = patient;
		this.occurenceTime = patient.getArrivalTime();
	}
	
	public void execute() {
		this.ed.removeFromGeneratedPatients(this.patient);
		this.patient.setState("arrived");
	}
}
