package events;

import others.Database;
import others.IDGenerator;
import healthServices.*;
import human.Patient;
import human.Physician;

public class ConsultationEvent extends Event {
	private Consultation consultation;
	private Patient patient;
	private Physician physician;
	
	
	public ConsultationEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGenerator.getInstance().getNextID();
		this.name=("Consultation" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.consultation=new Consultation(this.ed.getEDname());
		this.type= "Consultation";
		
	}

	@Override
	public void execute() {
		this.physician=this.ed.getNextPhysician();
		if (this.ed.getNextOnlyPatientShockRoom() != null) {
			this.patient=this.ed.getNextOnlyPatientShockRoom().getPatient();
		}
		else {
			this.patient=this.ed.getNextOnlyPatientBoxRoom().getPatient();
		}
		physician.consultation(patient, consultation);
		this.ed.addToEventQueue(new EndOfConsultationEvent(this));
		
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public Patient getPatient() {
		return patient;
	}

	public Physician getPhysician() {
		return physician;
	}



}
