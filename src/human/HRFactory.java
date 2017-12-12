package human;

public class HRFactory {
	
	/**
	 * Creates a human resource between nurse, physician and transporter.
	 * @param EDname The ED in which you wish to create a human resource
	 * @param HRType the type of resource to create
	 * @return the human resource created.
	 */
	public static HumanResource createHR(String EDname,String HRType) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(EDname);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(EDname);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(EDname);
		}
		return null;	
	}
	
	/**
	 * Creates a human resource between nurse, physician and transporter.
	 * @param EDname The ED in which you wish to create a human resource
	 * @param HRType the type of resource to create.
	 * @param name the name you wish to give to this HR.
	 * @return the human resource created.
	 */
	public static HumanResource createHR(String EDname,String HRType, String name, String surname) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(EDname,name,surname);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(EDname,name,surname);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(EDname,name,surname);
		}
		return null;
	}
}

