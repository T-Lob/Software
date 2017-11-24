package events;


import human.Patient;
import others.Database;
import others.IDGenerator;

public class RegistrationTriage extends Event {
	private int ID;

	
	public RegistrationTriage(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID=IDGenerator.getInstance().getNextID();
		this.name=("RegistrationTriage" + String.valueOf(ID));
		this.occurenceTime=(this.ED.getTime());
	}
	
	public int getID() {
		return ID;
	}
	
	public void execute () {
		for (Patient patient:this.ED.getGeneratedPatients()) {
			this.ED.getNurseList().get(0).get(0).registration(patient);
			this.ED.getNurseList().get(0).get(0).triage(patient);
			}
		
	}
	
	

}
