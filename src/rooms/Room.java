package rooms;
import human.Patient;
import others.Resource;

/**
 * This abstract class is extended by all the rooms of an ED : Blood test rooms, box rooms, mri rooms, radio room, schock rooms and waiting rooms.
 */
public abstract class Room extends Resource {

	protected Patient patient;

	/**
	 * 
	 * @return the patient assigned to this room.
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient assigned to this room.
	 * @param patient the patient which is to assign.
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
