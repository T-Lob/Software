package events;

import healthServices.XRay;
import human.Patient;

public class EndOfXrayEvent extends Event {
	private XRay xray;
	private Patient patient;

	/**
	 * This constructs a EndOfXRayEvent
	 * Occurs bloodtest.getduration() after the coresponding XRayEvent
	 * @param e the corresponding XRay
	 */
	public EndOfXrayEvent(XrayEvent e) {
		this.ed = e.getED();
		this.id = e.getID();
		this.name = "End of XRay" + String.valueOf(this.id);
		this.xray=e.getXRay();
		this.occurenceTime = e.getOccurenceTime()+xray.getDuration();
		this.patient = e.getPatient();
		this.type= "endOfXRay";
	}

	@Override
	public void execute() {
		xray.endCheck(patient);

	}

}
