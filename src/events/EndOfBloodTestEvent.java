package events;

import healthServices.BloodTest;
import human.Patient;

public class EndOfBloodTestEvent extends Event {
	private BloodTest bloodTest;
	private Patient patient;

	/**
	 * This constructs a EndOfBloodTestEvent
	 * Occurs bloodtest.getduration() after the ceoresponding BloodTestEvent
	 * @param e the corresponding BloodTest
	 */
	public EndOfBloodTestEvent(BloodTestEvent e) {
		this.ed = e.getED();
		this.id = e.getID();
		this.name = "End of BloodTest" + String.valueOf(this.id);
		this.bloodTest=e.getBloodTest();
		this.occurenceTime = e.getOccurenceTime()+bloodTest.getDuration();
		this.patient = e.getPatient();
		this.type= "endOfBloodTest";
	}

	@Override
	public void execute() {
		bloodTest.endCheck(patient);

	}

}
