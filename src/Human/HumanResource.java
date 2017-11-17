package Human;

import Resources.Resource;

public abstract class HumanResource extends Resource {
	protected String surname;
	protected String state="idle";
	protected int timeOfAvailability;
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getTimeOfAvailability() {
		return timeOfAvailability;
	}
	public void setTimeOfAvailability(int t) {
		this.timeOfAvailability = t;
	}
	
}
