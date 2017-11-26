package events;

import human.Nurse;
import human.Patient;

public class EndOfInstallation extends Event {
	private Nurse nurse;
	private Patient patient;

	public EndOfInstallation(Installation installation) {
		this.ed = installation.getED();
		this.id = installation.getID();
		this.name = "End of Installation" + String.valueOf(this.id);
		this.occurenceTime = installation.getOccurenceTime()+2;
		this.nurse = installation.getNurse();
		this.patient = installation.getPatient();
		this.type= "endOfInstallation";
	}

	public Nurse getNurse() {
		return nurse;
	}

	public Patient getPatient() {
		return patient;
	}

	@Override
	public void execute() {
		nurse.endOfInstallation(patient);
		
	}
	

}
