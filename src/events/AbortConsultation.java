package events;

import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class AbortConsultation extends Event {
	private ConsultationEvent consultationEvent;
	private Patient patient;
	
	public AbortConsultation(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name = "AbortConsultation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "AbortConsultation";
		this.patient=Database.remplacedPatient;
		this.consultationEvent=Database.getConsultationbyPatient(patient);
	}
	

	@Override
	public void execute() {
		ed.getRegisteredPatients().get(patient.getLevel()-1).add(0,patient);
		patient.setState("registered");
		int index = ed.getRegisteredPatients().get(patient.getLevel()-1).indexOf(patient);
		ed.getRegisteredPatients().get(patient.getLevel()-1).remove(index);
		ed.getRegisteredPatients().get(patient.getLevel()-1).add(0,patient);
		patient.setLocation(ed.waitingRoom);
		consultationEvent.getPhysician().setState("idle");
		consultationEvent.getPhysician().getHistoryPatients().remove(patient);
		consultationEvent.getPhysician().getCurrentPatients().remove(patient);
		patient.setPhysician(null);
		ed.getEventQueue().remove(consultationEvent.getEndOfConsultationEvent());
		this.ed.getNewEnabledEvents().remove("AbortConsultation");
	}

}
