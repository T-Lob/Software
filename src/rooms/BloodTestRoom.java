package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

/**
 * A blood test room is used to provide blood tests, patients requiring a blood test need to wait in the room waitingQueue before receiving the service.
 */
public class BloodTestRoom extends Room {
	private ArrayList<Patient> waitingQueue = new ArrayList<Patient>();
	
	/**
	 * Constructs a BloodTestRoom
	 * <br>Its default state is empty
	 * @param EDname The ED in which you wish to create the room
	 */
	public BloodTestRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.state="empty";
	}
	
	/**
	 * 
	 * @return The waitingQueue of this BloodTestRoom
	 */
	public ArrayList<Patient> getWaitingQueue() {
		return waitingQueue;
	}
	
	/**
	 * Adds a patient to the waiting queue of this BloodTestRoom
	 * @param patient The patient to add
	 */
	public void addToWaitingQueue(Patient patient) {
		this.waitingQueue.add(patient);
	}

}
