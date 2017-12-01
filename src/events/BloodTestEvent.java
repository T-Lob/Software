package events;

import healthServices.Consultation;
import human.Patient;
import others.Database;
import others.IDGenerator;
import others.IDGeneratorEvent;

public class BloodTestEvent extends Event {
	private Consultation consultation;
	private Patient patient;
	
	public BloodTestEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("BloodTest" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.consultation=new Consultation(this.ed.getEDname());
		this.type= "Consultation";

}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
