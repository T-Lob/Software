package events;

import human.Patient;
import human.Transporter;
import others.Database;
import others.IDGenerator;

public class Transportation extends Event{
	private Patient patient;
	private Transporter transporter;
	
	public Transportation(String edName) {
		this.ed = Database.getEDbyName(edName);
		this.id = IDGenerator.getInstance().getNextID();
		this.name = "Transportation" + String.valueOf(this.id);
		this.occurenceTime = ed.getTime();
		this.type= "Transportation";
	}

	public void execute() {
		this.transporter=this.ed.getNextTransporter();
		this.patient=this.ed.getNextWaitingForTransportPatient();
		transporter.transportation (patient);
		this.ed.addToEventQueue(new EndOfTransportation(this));
		
	}

	public Patient getPatient() {
		return patient;
	}


	public Transporter getTransporter() {
		return transporter;
	}


}
