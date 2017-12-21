package events;

import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class Verdict extends Event {

	/**
	 * Constructs a Verdict Envent
	 * <br>Occurs immediatly
	 * @param eDname The ED in which the event is created.
	 */
	public Verdict(String eDname) {
		this.ed=Database.getEDbyName(eDname);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("Verdict" + String.valueOf(id));
		this.occurenceTime=(Database.getTime());
		this.type= "Verdict";
	}
		

	/**
	 * when the physician is idling, he can give his verdict to the patient
	 */
	@Override
	public void execute() {
		for(int i=0 ; i<ed.getWaitingForVerdictPatients().size(); i++) {
			Patient patient = ed.getWaitingForVerdictPatients().get(i);
			if (patient.getPhysician().getState().equalsIgnoreCase("idle")) 
				patient.getPhysician().verdict(patient);
			this.ed.getNewEnabledEvents().remove("Verdict");
		}		
	}	
}
