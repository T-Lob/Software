package human;
import others.*;

public class Transporter extends HumanResource{
	
	private Patient currentPatient;
	
	public Transporter(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = "Transporter" + String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	public Transporter(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	public Transporter(String EDname, String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("transporting") & !(this.ED.getTransporterList().get(1).contains(this))){
			this.ED.getTransporterList().get(1).add(this);
			this.ED.getTransporterList().get(0).remove(this);
			this.ED.getTransporterList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle") & !(this.ED.getTransporterList().get(0).contains(this))) {
			this.ED.getTransporterList().get(0).add(this);
			this.ED.getTransporterList().get(1).remove(this);
			this.ED.getTransporterList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty") & !(this.ED.getTransporterList().get(2).contains(this))){
			this.ED.getTransporterList().get(2).add(this);
			this.ED.getTransporterList().get(1).remove(this);
			this.ED.getTransporterList().get(0).remove(this);
		}
	}
	
	public void transportation (Patient patient) {
		this.setState("transporting");
		this.setCurrentPatient(patient);
		patient.setState("transported");
		patient.getDestination().setPatient(patient);;
	}
	
	public void endOfTransportation (Patient patient) {
		if (this.currentPatient.getDestination().equals(this.ED.bloodTestRoom)) {
			this.ED.bloodTestRoom.addToWaitingQueue(patient);
		} else if (this.currentPatient.getDestination().equals(this.ED.mriRoom)) {
			this.ED.mriRoom.addToWaitingQueue(patient);
		} else if (this.currentPatient.getDestination().equals(this.ED.radioRoom)) {
			this.ED.radioRoom.addToWaitingQueue(patient);
		}
		this.setState("idle");
		patient.setState("waitingForExamination");
		patient.setLocation(patient.getDestination());
		this.setCurrentPatient(null);
	}
	
}
