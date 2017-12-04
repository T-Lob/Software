package events;

public class EventFactory {
	public static Event createEvent(String EDname,String eventType) {
		if (eventType == null) {
			return null;
		}
		
		if (eventType.equalsIgnoreCase("PATIENTARRIVAL")){
			return new PatientArrival(EDname);
		} else if (eventType.equalsIgnoreCase("INSTALLATION")){
			return new Installation(EDname);
		} else if (eventType.equalsIgnoreCase("REGISTRATION")){
			return new Registration(EDname);
		} else if (eventType.equalsIgnoreCase("REPLACEPATIENT")) {
			return new ReplacePatient(EDname);
		} else if (eventType.equalsIgnoreCase("ABORTCONSULTATION")) {
			return new AbortConsultation(EDname);
		} else if (eventType.equalsIgnoreCase("CONSULTATION")) {
			return new ConsultationEvent(EDname);
		} else if (eventType.equalsIgnoreCase("TRANSPORTATION")) {
			return new Transportation(EDname);
		} else if (eventType.equalsIgnoreCase("BLOODTEST")) {
			return new BloodTestEvent(EDname);	
		} else if (eventType.equalsIgnoreCase("XRAY")) {
			return new XrayEvent(EDname);	
		} else if (eventType.equalsIgnoreCase("MRI")) {
			return new MRIEvent(EDname);	
		} else if (eventType.equalsIgnoreCase("VERDICT")) {
			return new Verdict(EDname);	
		}
		return null;	

	}
}
