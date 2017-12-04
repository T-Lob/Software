package events;

import healthServices.XRay;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class Verdict extends Event {

	public Verdict(String eDname) {
		this.ed=Database.getEDbyName(eDname);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("Verdict" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.type= "Verdict";
	}
		

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
