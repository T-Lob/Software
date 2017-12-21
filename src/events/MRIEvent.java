package events;


import healthServices.MRIScan;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class MRIEvent extends Event {
	private MRIScan mri;
	private Patient patient;
	
	/**
	 * ThE MRIEvent constructor creates the corresponding MRIScan and chose the patient from the waiting queue.
	 * @param edName The ED in which the MRI occurs.
	 */
	public MRIEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("MRI" + String.valueOf(id));
		this.occurenceTime=(Database.getTime());
		this.mri=new MRIScan(this.ed.getEDname());
		this.type= "MRI";
		this.patient=this.ed.mriRoom.getWaitingQueue().get(0);

	}

	/**
	 * This triggers the check and the EndOfMRIEvent.
	 */
	@Override
	public void execute() {
		
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
