package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

/**
 * A MRIRoomm is used to provide MRI, patients requiring an MRI need to wait in the room waitingQueue before receiving the service.
 */
public class MRIRoom extends Room {
	private ArrayList<Patient> waitingQueue = new ArrayList<Patient>();
	
	/**
	 * Constructs a MRIRoom
	 * <br>Its default state is empty
	 * @param EDname The ED in which you wish to create the room
	 */
	public MRIRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.state="empty";
	}
	
	public MRIRoom(String eDname, String roomName) {
		this.ED=Database.getEDbyName(eDname);
		this.name=roomName;
		this.state="empty";
	}

	/**
	 * 
	 * @return The waitingQueue of this MRIRoom
	 */
	public ArrayList<Patient> getWaitingQueue() {
		return waitingQueue;
	}
	
	/**
	 * Adds a patient to the waiting queue of this MRIroom
	 * @param patient The patient to add
	 */
	public void addToWaitingQueue(Patient patient) {
		this.waitingQueue.add(patient);
	}
}
