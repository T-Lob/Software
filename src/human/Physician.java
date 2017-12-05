package human;

import java.util.ArrayList;
import healthServices.Consultation;
import others.*;

/**
 * Physicians are part of the human resources. They have ah username and hold a list of their current and past patients.
 *  they can also receive some messages.
 *
 */
public class Physician extends HumanResource implements Observer{
	
	private String username;
	private ArrayList<Patient> currentPatients = new ArrayList<Patient>();
	private ArrayList<Patient> historyPatients = new ArrayList<Patient>();
	private ArrayList<String> messageBox = new ArrayList<String>();
	
	
	/**
	 * This constructs a Physician and adds it to the PhysicianList.
	 * <br>Its default state is idle.
	 * <br>With no name or surname specified, its name and surname are the same and constructed like so : "Physician" followed by
	 * the Physician ID.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Physician.
	 */
	public Physician(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.name = "Physician" + String.valueOf(this.ID);
		this.surname = "Physician" + String.valueOf(this.ID);
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
		
	}
	
	/**
	 * This constructs a Physician and adds it to the PhysicianList.
	 * <br>Its default state is idle.
	 * <br>With no surname specified, it is constructed like so : "Physician" followed by
	 * the Physician ID.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Physician.
	 * @param name The name of this physician.
	 */
	public Physician(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = "Physician" + String.valueOf(this.ID);
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
	}

	/**
	 * This constructs a Physician and adds it to the PhysicianList.
	 * <br>Its default state is idle.
	 * <br>His ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a Physician.
	 * @param name The name of this physician.
	 * @param surname The surname of this physician.
	 * @param username The username of this physician.
	 */
	public Physician(String EDname,String name, String surname, String username) {
		this.ED=Database.getEDbyName(EDname);
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
		this.state = "idle";
		this.ED.getPhysicianList().get(0).add(this);
	}
	
	/**
	 * 
	 * @return The username of this physician.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * This sets the username of this physician.
	 * @param username The username which is to set for this physician.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return The patient currently assigned to this physician.
	 */
	public ArrayList<Patient> getCurrentPatients() {
		return currentPatients;
	}

	/**
	 * 
	 * @return The patient history of this physician.
	 */
	public ArrayList<Patient> getHistoryPatients() {
		return historyPatients;
	}

	/**
	 * 
	 * @return The message box of this physician.
	 */
	public ArrayList<String> getMessageBox() {
		return messageBox;
	}
	
	/**
	 * 
	 * @return The element most recently added to the message box of this physician.
	 */
	public String getLastMessage() {
		return messageBox.get(messageBox.size()-1);
	}
	
	/**
	 * This is used to set the state of this Physician, it works by moving this Physician from one line to the other in the PhysicianList Arraylist
	 * (only if it is not already in this line)
	 * @param state The state in which you wish to set this Physician.
	 */
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting") & !(this.ED.getPhysicianList().get(1).contains(this))){
			this.ED.getPhysicianList().get(1).add(this);
			this.ED.getPhysicianList().get(0).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle") & !(this.ED.getPhysicianList().get(0).contains(this))) {
			this.ED.getPhysicianList().get(0).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("offduty") & !(this.ED.getPhysicianList().get(2).contains(this))){
			this.ED.getPhysicianList().get(2).add(this);
			this.ED.getPhysicianList().get(1).remove(this);
			this.ED.getPhysicianList().get(0).remove(this);
		}
	}
	
	/**
	 * This simulates a consultation by updating the Physician state to visiting, the room state to full, and the patient state to visited.
	 * It also implements the HystoryPatients and CurrentPatients lists.
	 * @param patient The patient being consulted by this physician.
	 * @param consultation The consultation corresponding to the patient.
	 */
	public void consultation (Patient patient, Consultation consultation) {
			this.setState("visiting");
			patient.getLocation().setState("full");
			this.getHistoryPatients().add(patient);
			this.getCurrentPatients().add(patient);
			patient.setPhysician(this);
			patient.setState("visited");
			
	}
	
	/**
	 * This simulates the end of a consultation by resetting the physician state to idle, and calculating the result of the consultation.
	 * @param patient The patient which was consulted.
	 * @param consultation The consultation he was gven.
	 */
	public void endOfConsultation (Patient patient, Consultation consultation)
		{	patient.setConsultationTime(ED.getTime()-consultation.getDuration());
			consultation.result(patient);
			this.setState("idle");
	}
	
	/**
	 * This simulates the end of the patient's journey in the ED, the patient is set to released (by the physician) and relocated 
	 * to the waitingRoom.
	 * <br> The patient is removed form this physician's CurrentPatients list. and the physician becomes idle.
	 * @param patient The patient which is waiting for verdict
	 */
	public void verdict (Patient patient) {
		patient.setReleaseTime(ED.getTime());
		patient.setState("released");
		this.setState("idle");
		this.currentPatients.remove(patient);
	}
	
	/**
	 * This adds an outcomein this physician messagebox
	 * @param outcome The outcom to add to the message box.
	 */
	@Override
	public void update(String outcome) {
		this.messageBox.add(outcome);
	}
}
