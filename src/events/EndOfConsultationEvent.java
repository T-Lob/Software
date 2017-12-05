package events;

import healthServices.Consultation;
import human.Patient;
import human.Physician;

public class EndOfConsultationEvent extends Event {
	private Consultation consultation;
	private Patient patient;
	private Physician physician;
	
	/**
	 * This constructs the EndOfConsultationEvent
	 * Its name is "End of Consultation" followed by the corresponding consultationEvent ID
	 * The occurence time is calculated using th ocurence time of the calculation events and the duration of the corresponding consultation
	 * @param e the ConsultationEvent corresponding.
	 */
	public EndOfConsultationEvent(ConsultationEvent e) {
		this.ed = e.getED();
		this.id = e.getID();
		this.name = "End of Consultation" + String.valueOf(this.id);
		this.consultation=e.getConsultation();
		this.occurenceTime = e.getOccurenceTime()+consultation.getDuration();
		this.patient = e.getPatient();
		this.physician = e.getPhysician();
		this.type= "endOfConsultation";
	}

	@Override
	public void execute() {
		physician.endOfConsultation(patient, consultation);

	}

}
