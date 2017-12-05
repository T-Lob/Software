package events;

import healthServices.MRIScan;
import human.Patient;

public class EndOfMRIEvent extends Event {
	private MRIScan mri;
	private Patient patient;

	/**
	 * This constructs a EndOfMRItEvent
	 * Occurs MRIScan.getduration() after the ceoresponding MRIEvent
	 * @param e the corresponding MRIEvent
	 */
	public EndOfMRIEvent(MRIEvent e) {
		this.ed = e.getED();
		this.id = e.getID();
		this.name = "End of MRIScan" + String.valueOf(this.id);
		this.mri=e.getMRIScan();
		this.occurenceTime = e.getOccurenceTime()+mri.getDuration();
		this.patient = e.getPatient();
		this.type= "endOfMRIScan";
	}

	@Override
	public void execute() {
		mri.endCheck(patient);

	}

}
