package events;

import human.Patient;
import human.Transporter;
import others.Database;
import others.IDGeneratorEvent;

public class Transportation extends Event{
	private Patient patient;
	private Transporter transporter;
	
	/**
	 * Constructs a transportation event
	 * <br>Occurs immediatly.
	 * @param edName The ED in which the event is created.
	 */
	public Transportation(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id=IDGeneratorEvent.getInstance().getNextID();
		this.name = "Transportation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "Transportation";
		this.patient=this.ed.getNextWaitingForTransportPatient();
	}

	/**
	 * a transporter is selected, then the transportation is triggered, followed by the endoftransportation.
	 */
	public void execute() {
		this.transporter=this.ed.getNextTransporter();
		transporter.transportation (patient);
		this.ed.addToEventQueue(new EndOfTransportation(this));
		this.ed.getNewEnabledEvents().remove("Transportation");
		
	}

	public Patient getPatient() {
		return patient;
	}


	public Transporter getTransporter() {
		return transporter;
	}


}
