package Human;
import others.Database;
import others.IDGenerator;

public class Transporter extends HumanResource{
private Patient currentPatient;
	
	public Transporter() {
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToTransporterList(this);
	}
	public Transporter(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToTransporterList(this);
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}


}
