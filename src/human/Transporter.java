package human;
import others.*;

/**
 * Transporters are part of the human resources.
 * They transport a patient from a consultation to any health service.
 *
 */
public class Transporter extends HumanResource{
	
	private Patient currentPatient;
	
	/**
	 * /**
	 * This constructs a Transporter and adds it to the TransporterList.
	 * <br>Its default state is idle.
	 * <br>With no name specified, it is constructed like so : "Transporter" followed by
	 * the Transporter ID.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Transporter.
	 */
	public Transporter(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = "Transporter" + String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	/**
	 * This constructs a Transporter and adds it to the TransporterList.
	 * <br>Its default state is idle.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Transporter.
	 * @param name The name of this Transporter.
	 * @param surname The surname of this Transporter.
	 */
	public Transporter(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	/**
	 * This constructs a Transporter and adds it to the TransporterList.
	 * <br>Its default state is idle.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Transporter.
	 * @param name The name of this Transporter.
	 */
	public Transporter(String EDname, String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getTransporterList().get(0).add(this);
	}
	
	/**
	 * 
	 * @return The patient currently assigned to this Transporter.
	 */
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	/**
	 * Sets the patient currently assigned to this Transporter.
	 * @param currentPatient The patient to assign to this Transporter.
	 */
	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	/**
	 * This is used to set the state of this Transporter, it works by moving this Transporter from one line to the other in the TransporterList Arraylist
	 * (only if it is not already in this line)
	 * @param state The state in which you wish to set this Transporter.
	 */
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
	
	/**
	 * This simulates the transportation of a patient. This transporters's state is set to transporting, the patient's state is set transported.
	 * The room in which the patient was picked up is set to empty.
	 * @param patient The patient waiting for transportation
	 */
	public void transportation (Patient patient) {
		this.setState("transporting");
		this.setCurrentPatient(patient);
		patient.setState("transported");
		patient.getDestination().setPatient(patient);;
	}
	
	/**
	 * This simulates the drop off of the patient by this transporter. The patient is added to the wainting queue corresponding 
	 * to its destination, it is set to waitinForExamination and his location is updated.
	 * The patient is unassigned to this Transporter
	 * @param patient The patient being transported
	 */
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
