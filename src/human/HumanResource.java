package human;

import others.Resource;

/**
 * This abstract class is extended by all the human ressources of an ED : Th physician, the nurses, the patients and the transporters.
 */
public abstract class HumanResource extends Resource {
	protected String surname;
	
	/**
	 * 
	 * @return The surname of this human resource
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Sets the Surname of this human resource.
	 * @param surname the surname to give to this human resource.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
