package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

public class BloodTestRoom extends Room {
	private ArrayList<Patient> waitingQueue = new ArrayList<Patient>();
	
	public BloodTestRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.state="empty";
	}
	
	public ArrayList<Patient> getWaitingQueue() {
		return waitingQueue;
	}
	
	public void addToWaitingQueue(Patient patient) {
		this.waitingQueue.add(patient);
	}

}
