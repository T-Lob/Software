

import others.IDGenerator;

public class Nurse extends HumanResource {
	private Patient currentPatient;
	
	public Nurse() {
		this.ID = IDGenerator.getInstance().getNextID();
	}
	public Nurse(String name, String surname, String username) {
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}

}
