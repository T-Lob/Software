package human;
import others.*;

public class Transporter extends HumanResource{
	
	private Patient currentPatient;
	
	public Transporter(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToTransporterList(this);
	}
	
	public Transporter(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToTransporterList(this);
	}
	
	public Transporter(String EDname, String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToTransporterList(this);
	}
	
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("transporting")){
			this.ED.getTransporterList().get(1).add(this);
			this.ED.getTransporterList().get(0).remove(this);
			this.ED.getTransporterList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle")) {
			this.ED.getTransporterList().get(0).add(this);
			this.ED.getTransporterList().get(1).remove(this);
			this.ED.getTransporterList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			this.ED.getTransporterList().get(2).add(this);
			this.ED.getTransporterList().get(1).remove(this);
			this.ED.getTransporterList().get(0).remove(this);
		}
		
	}
	
	public void transportation (Patient patient) {
		if (this.timeOfAvailability <= this.ED.getTime()) {
			this.setTimeOfAvailability(this.ED.getTime());
			this.setState("transporting");
			this.setCurrentPatient(patient);
			patient.setState("transported");
			patient.setLocation(patient.getDestination());
			patient.getDestination().setState("full");
			patient.getDestination().setPatient(patient);;
			this.timeOfAvailability += 5;
		}
	}
	
	public void endOfTransportation (Patient patient) {
		if (this.timeOfAvailability == this.ED.getTime()) {
			this.setState("idle");
			this.setCurrentPatient(null);
			patient.setState("waitingForExamination");
		}
	}
}
