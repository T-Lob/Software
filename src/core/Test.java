package core;


import events.Event;
import human.*;
import rooms.*;
import others.Database;
import others.ED;

public class Test {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		
		System.out.println(ed.getRegisteredPatients().get(4));
		Patient patient  = new Patient ("ED");
		Patient patient2  = new Patient ("ED");
		Patient patient3  = new Patient ("ED");
		Patient patient4 = new Patient ("ED");
		Patient patient5  = new Patient ("ED");
		Patient patient6  = new Patient ("ED");
		Patient patient7  = new Patient ("ED");
		Patient patient8 = new Patient ("ED");
		Patient patient9  = new Patient ("ED");
		Patient patient10 = new Patient ("ED");
		Patient patient11  = new Patient ("ED");
		Patient patient12 = new Patient ("ED");
		System.out.println(ed.getGeneratedPatients());
		Physician physician = (Physician) HRFactory.createHR("ED", "Physician");
		Nurse nurse = (Nurse) HRFactory.createHR("ED", "Nurse");
		Transporter transporter = (Transporter) HRFactory.createHR("ED", "Transporter");
		BoxRoom boxroom = new BoxRoom("ED");
		ShockRoom shockroom = new ShockRoom("ED");
		ed.updateState();
		ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getOldEnabledEvents(), ed));
		ed.setEventQueue(Database.updateEventQueue(ed));
		while (ed.getTime()<1000) {
			Event e1=ed.getEventQueue().get(0);
			System.out.println(e1.getType());
			ed=Database.execute(e1, ed);
			ed.setTime(e1.getOccurenceTime());
			System.out.println(ed.getTime());
			ed.getNewEnabledEvents().remove(e1.getType());
			ed.addToNewEnabledEvents("PatientArrival");
			ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
			ed.setEventQueue(Database.updateEventQueue(ed));
			patient.getHistory();
			System.out.println("Event Queue: " + ed.getEventQueue());
			System.out.println("----------------");
			//ed.display();
		 }
		
		
	
	
	}

}
