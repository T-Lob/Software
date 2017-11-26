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
		} else if (eventType.equalsIgnoreCase("CONSULTATION")) {
			return new ConsultationEvent(EDname);
		} else if (eventType.equalsIgnoreCase("TRANSPORTATION")) {
			return new Transportation(EDname);
		}
		
		return null;	
	}

}
