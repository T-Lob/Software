package events;

import human.Patient;
import human.Transporter;

public class EndOfTransportation extends Event {
	private Transporter transporter;
	private Patient patient;
	
	/**
	 * Constructs an EndOftrasportation Event
	 * It takes the same ID as the transportation corresponding
	 * It occurs 5 minutes after the trasnportation has been triggered
	 * @param transportation the corresponding transportations.
	 */
	public EndOfTransportation(Transportation transportation) {
		this.ed = transportation.getED();
		this.id = transportation.getID();
		this.name = "End of Transportation" + String.valueOf(this.id);
		this.occurenceTime = transportation.getOccurenceTime()+5;
		this.transporter = transportation.getTransporter();
		this.patient = transportation.getPatient();
		this.type= "endOfTransportation";
	}

	@Override
	public void execute() {
		transporter.endOfTransportation(patient);

	}

}
