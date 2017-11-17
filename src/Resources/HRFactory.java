package Resources;
import Human.Nurse;
import Human.Physician;
import Human.Transporter;

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
}

