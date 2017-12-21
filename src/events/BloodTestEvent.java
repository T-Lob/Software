package events;

import healthServices.BloodTest;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class BloodTestEvent extends Event {
	private BloodTest bloodTest;
	private Patient patient;
	
	/**
	 * ThE BloodtestEvent constructor creates the corresponding BloodTest and chose the patient from the waiting queue.
	 * @param edName The ED in which the bloodtest occurs.
	 */
	public BloodTestEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("BloodTest" + String.valueOf(id));
		this.occurenceTime=(Database.getTime());
		this.bloodTest=new BloodTest(this.ed.getEDname());
		this.patient=this.ed.bloodTestRoom.getWaitingQueue().get(0);
		this.type= "BloodTest";

	}
	
	/**
	 * This simply triggers the BloodTestEvent and theEndOfBloodTestEvent
	 */
	@Override
	public void execute() {
		this.bloodTest.check(patient);
		this.ed.addToEventQueue(new EndOfBloodTestEvent(this));
		this.ed.getNewEnabledEvents().remove("BloodTest");
		
		
	}

	public BloodTest getBloodTest() {
		return this.bloodTest;
	}

	public Patient getPatient() {
		return this.patient;
	}

}
