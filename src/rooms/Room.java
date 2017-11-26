package rooms;
import human.Patient;
import others.Resource;

public abstract class Room extends Resource {

	protected Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
