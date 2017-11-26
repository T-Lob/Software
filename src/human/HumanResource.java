package human;

import others.Resource;

public abstract class HumanResource extends Resource {
	protected String surname;
	protected String state="idle";
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
