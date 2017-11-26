package events;

import human.Patient;
import others.Database;
import others.IDGenerator;

public class PatientArrival extends Event {
	
	private Patient patient;
	
	public PatientArrival(String edName){
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Patient Arrival " + String.valueOf(id);
		this.occurenceTime = patient.getArrivalTime();
		this.type="PatientArrival";
	}
	
	public PatientArrival(String edName, Patient patient){
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Patient Arrival " + String.valueOf(id);
		this.patient = patient;
		this.occurenceTime = patient.getArrivalTime();
		this.type="PatientArrival";
	}
	@Override
	public void execute() {
		this.patient.setState("arrived");
	}
}
