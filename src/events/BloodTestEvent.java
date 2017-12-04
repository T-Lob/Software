package events;

import healthServices.BloodTest;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class BloodTestEvent extends Event {
	private BloodTest bloodTest;
	private Patient patient;
	
	public BloodTestEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("BloodTest" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.bloodTest=new BloodTest(this.ed.getEDname());
		this.type= "BloodTest";

}

	@Override
	public void execute() {
		this.patient=this.ed.bloodTestRoom.getWaitingQueue().get(0);
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
