package Human;
import others.*;

public class HRFactory {
	public HumanResource createHR(ED ed,String HRType) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(ed);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(ed);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(ed);
		}
		return null;
		
	}
	public HumanResource createHR(ED ed,String HRType, String name) {
		if (HRType == null) {
			return null;
		}
		
		if (HRType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(ed,name);
		} else if (HRType.equalsIgnoreCase("NURSE")){
			return new Nurse(ed,name);
		} else if (HRType.equalsIgnoreCase("TRANSPORTER")) {
			return new Transporter(ed,name);
		}
		return null;
		
	}
}

