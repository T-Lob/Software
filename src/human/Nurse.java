package human;


import others.*;
import rooms.Room;

/**
 * Nurses are part of the human resources
 * A nurse has a patient assigned when it is not idle : currentPatient
 *
 */
public class Nurse extends HumanResource {
	private Patient currentPatient;
	
	/**
	 * This constructs a Nurse and adds it to the NurseList.
	 * <p>Its default state is idle.
	 * <P>With no name or surname specified, its name and surname are the same and constructed like so : "Nurse" followed by
	 * the Nurse ID.
	 * <p>Her ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Nurse
	 */
	public Nurse(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.name = "Nurse"+String.valueOf(this.ID);
		this.surname = "Nurse"+String.valueOf(this.ID);
		this.state = "idle";
		Database.getEDbyName(EDname).getNurseList().get(0).add(this);
	}
	
	/**
	 * This constructs a Nurse and adds it to the NurseList.
	 * <p>Its default state is idle
	 * <p>Her ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Nurse
	 * @param name The name you wish to give to the Nurse.
	 * @param surname The surname you wish to give to the Nurse.
	 */
	public Nurse(String EDname, String name, String surname) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getNurseList().get(0).add(this);
	}
	
	/**
	 * This constructs a Nurse and adds it to the NurseList.
	 * <p>Its default state is idle.
	 * <P>With no surname specified, it is constructed like so : "Nurse" followed by the Nurse ID.
	 * <p>Her ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Nurse
	 * @param name The name you wish to give to the Nurse.
	 */
	public Nurse(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = "Nurse"+String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getNurseList().get(0).add(this);
		
	}

	/**
	 * This is used to access a patient by knowing its Nurse.
	 * @return The Patient instance currently assigned to a Nurse.
	 */
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	/**
	 * This is used to assign a patient to a Nurse
	 * @param currentPatient the patient you wish to assign to this Nurse
	 */
	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	/**
	 * This is used to set the state of this Nurse, it works by moving this Nurse from on line to the other in the NurseList Arraylist
	 * (only if it is not already in this line)
	 * @param state The state in which you wish to set this Nurse.
	 */
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("busy") & !(this.ED.getNurseList().get(1).contains(this))){
			this.ED.getNurseList().get(1).add(this);
			this.ED.getNurseList().get(0).remove(this);
			this.ED.getNurseList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("idle") & !(this.ED.getNurseList().get(0).contains(this))) {
			this.ED.getNurseList().get(0).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty") & !(this.ED.getNurseList().get(2).contains(this))){
			this.ED.getNurseList().get(2).add(this);
			this.ED.getNurseList().get(1).remove(this);
			this.ED.getNurseList().get(0).remove(this);
		}
	}
	
	/**
	 * This simulates the registration of a patient, by changing its state to registered using the setState method.
	 * The patient is then moved to the waitingRoom using the setLocation method.
	 * @param patient The patient this Nurse is resgistering.
	 */
	public void registration (Patient patient) {
		patient.setState("registered");
		patient.setLocation(ED.waitingRoom);
	}
	
	/**
	 * This simulates the installation of a given patient by this Nurse in a given room.
	 * <p>The nurse is busy during this operation and the patient is assigned to her, when the patient is installed, 
	 * the room becomes full and the patient is assigned to it.
	 * @param patient The patient which is going to be installed.
	 * @param room The room in which the patient is going to be installed.
	 */
	public void installation (Patient patient, Room room) {
		this.setState("busy");
		this.setCurrentPatient(patient);	
		patient.setState("installed");
		patient.setDestination(room);
		room.setState("reserved");
		room.setPatient(patient);
	}
	
	/**
	 * This is used to finalize the installation of the patient and to reset the state of the Nurse.
	 * The location of the patient is set and the state of the room is updated to onlyPatient as the nurse as leaved.
	 * Finally, the destination of the patient is set ton null and its state to waiting.
	 * @param patient the patient whiwh is to end its installation in a room.
	 */
	public void endOfInstallation (Patient patient) {
		this.setState("idle");
		this.setCurrentPatient(null);
		patient.setLocation(patient.getDestination());
		patient.getLocation().setState("onlyPatient");
		patient.setDestination(null);
		patient.setState("waiting");
	}
}



