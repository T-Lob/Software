package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

public class BloodTestRoom extends Room {
	private ArrayList<Patient> WaitingQueue = new ArrayList<Patient>();
	
	public BloodTestRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}
	
	public ArrayList<Patient> getWaitingQueue() {
		return WaitingQueue;
	}
	
	public void addToWaitingQueue(Patient patient) {
		this.WaitingQueue.add(patient);
	}

}
