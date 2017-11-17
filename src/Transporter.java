import others.IDGenerator;

public class Transporter extends HumanResource{
private Patient currentPatient;
	
	public Transporter() {
		this.ID = IDGenerator.getInstance().getNextID();
	}
	public Transporter(String name, String surname, String username) {
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
