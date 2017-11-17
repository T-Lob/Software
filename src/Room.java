
public abstract class Room extends Resource {

	protected Patient patient;
	protected String state="empty";

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	


}
