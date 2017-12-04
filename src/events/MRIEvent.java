package events;


import healthServices.MRIScan;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class MRIEvent extends Event {
	private MRIScan mri;
	private Patient patient;
	
	public MRIEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("MRI" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.mri=new MRIScan(this.ed.getEDname());
		this.type= "MRI";

}

	@Override
	public void execute() {
		this.patient=this.ed.mriRoom.getWaitingQueue().get(0);
		this.mri.check(patient);
		this.ed.addToEventQueue(new EndOfMRIEvent(this));
		this.ed.getNewEnabledEvents().remove("MRI");
		
		
	}

	public MRIScan getMRIScan() {
		return this.mri;
	}

	public Patient getPatient() {
		return this.patient;
	}

}
