package events;

import healthServices.Consultation;
import human.Patient;
import human.Physician;

public class EndOfConsultationEvent extends Event {
	private Consultation consultation;
	private Patient patient;
	private Physician physician;
	
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
