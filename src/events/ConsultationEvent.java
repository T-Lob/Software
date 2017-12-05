package events;

import others.Database;
import others.IDGeneratorEvent;
import healthServices.*;
import human.Patient;
import human.Physician;

public class ConsultationEvent extends Event {
	private Consultation consultation;
	private Patient patient;
	private Physician physician;
	private EndOfConsultationEvent endOfConsultationEvent;
	
	
	/**
	 * Constructs a consultation event.
	 * <br>The ID is generated by the getNextID Method.
	 * The event is then added to the database.
	 * @param edName The ED in which the event is created
	 * @param patient the patient undergoing the consultation
	 */
	public ConsultationEvent(String edName, Patient patient) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("Consultation" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.consultation=new Consultation(this.ed.getEDname());
		this.patient = patient;
		this.type= "Consultation";
		Database.addToGeneratedConsultations(this);
		
	}

	
	
	/**
	 * This chooses a physician to do the consultation and adds the end of the consultation to the event queue
	 */
	@Override
	public void execute() {
		this.physician=this.ed.getNextPhysician();
		physician.consultation(patient, consultation);
		this.endOfConsultationEvent=new EndOfConsultationEvent(this);
		this.ed.addToEventQueue(endOfConsultationEvent);
		this.ed.getNewEnabledEvents().remove("Consultation");
		
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
	public EndOfConsultationEvent getEndOfConsultationEvent() {
		return endOfConsultationEvent;
	}


}
