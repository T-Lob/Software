package Human;

public class HRFactory {
	public HumanResource createHR(String HRType) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician();
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse();
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter();
		}
		return null;
		
	}
	public HumanResource createHR(String HRType, String name) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(name);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(name);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(name);
		}
		return null;
		
	}
}

