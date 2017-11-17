package Resources;
import Human.Patient;

public abstract class Room extends Resource {

	protected Patient patient;
	protected String state="empty";

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}