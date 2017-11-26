package rooms;

import java.util.ArrayList;

import human.Patient;
import others.Database;

public class RadioRoom extends Room {
	private ArrayList<Patient> WaitingQueue = new ArrayList<Patient>();
	public ArrayList<Patient> getWaitingQueue() {
		return WaitingQueue;
	}
	
	public void addToWaitingQueue(Patient patient) {
		this.WaitingQueue.add(patient);
	}

	public RadioRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
