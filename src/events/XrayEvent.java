package events;


import healthServices.XRay;
import human.Patient;
import others.Database;
import others.IDGeneratorEvent;

public class XrayEvent extends Event {
	private XRay xray;
	private Patient patient;
	
	/**
	 * ThE XRayEvent constructor creates the corresponding XRay and chose the patient from the waiting queue.
	 * @param edName The ED in which the XRay occurs.
	 */
	public XrayEvent(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name=("XRay" + String.valueOf(id));
		this.occurenceTime=(Database.getTime());
		this.xray=new XRay(this.ed.getEDname());
		this.type= "XRay";
		this.patient=this.ed.radioRoom.getWaitingQueue().get(0);

	}

	/**
	 * This triggers the check and the EndOfXRayEvent.
	 */
	@Override
	public void execute() {
		this.xray.check(patient);
		this.ed.addToEventQueue(new EndOfXrayEvent(this));
		this.ed.getNewEnabledEvents().remove("Xray");
		
		
	}

	public XRay getXRay() {
		return this.xray;
	}

	public Patient getPatient() {
		return this.patient;
	}

}
