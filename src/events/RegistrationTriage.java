package events;


import human.Patient;
import others.Database;
import others.IDGenerator;

public class RegistrationTriage extends Event {
	private int ID;

	
	public RegistrationTriage(String EDname) {
		this.ed=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name=("RegistrationTriage" + String.valueOf(ID));
		this.occurenceTime=(this.ed.getTime());
	}
	
	public int getID() {
		return ID;
	}
	
	public void execute () {
		for (Patient patient:this.ed.getGeneratedPatients()) {
			this.ed.getNurseList().get(0).get(0).registration(patient);
			this.ed.getNurseList().get(0).get(0).triage(patient);
		}	
	}
}
