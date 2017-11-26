package human;

public class HRFactory {
	
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
	
	public HumanResource createHR(String EDname,String HRType, String name) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(EDname,name);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(EDname,name);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(EDname,name);
		}
		return null;
	}
}

