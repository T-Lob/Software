package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

/**
 * A Radio is used to provide Radigraphys, patients requiring a radiography need to wait in the room waitingQueue before receiving the service.
 */
public class RadioRoom extends Room {
	private ArrayList<Patient> waitingQueue = new ArrayList<Patient>();
	
	/**
	 * Constructs a RadioRoom
	 * <br>Its default state is empty
	 * @param EDname The ED in which you wish to create the room
	 */
	public RadioRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.state="empty";
	}
	
	public RadioRoom(String eDname, String roomName) {
		this.ED=Database.getEDbyName(eDname);
		this.name=roomName;
		this.state="empty";
		
	}

	/**
	 * 
	 * @return The waitingQueue of this RadioRoom
	 */
	public ArrayList<Patient> getWaitingQueue() {
		return waitingQueue;
	}
	
	/**
	 * Adds a patient to the waiting queue of this RadioRoom
	 * @param patient The patient to add
	 */
	public void addToWaitingQueue(Patient patient) {
		this.waitingQueue.add(patient);
	}
}
