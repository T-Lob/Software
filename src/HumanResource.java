
public class HumanResource extends Resource {
	protected String surname;
	protected String state="idle";
	public int timeOfAvailability;
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getTimeOFAvailability() {
		return timeOfAvailability;
	}
	public void setTimeOfAvailability(int timestamp) {
		this.timeOfAvailability = timestamp;
	}

	

}