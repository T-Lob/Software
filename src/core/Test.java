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
		System.out.println(ed.getGeneratedPatients());
		Physician physician = (Physician) HRFactory.createHR("ED", "Physician");
		Nurse nurse = (Nurse) HRFactory.createHR("ED", "Nurse");
		Transporter transporter = (Transporter) HRFactory.createHR("ED", "Transporter");
		ed.updateState();
		ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getOldEnabledEvents(), ed));
		// System.out.println(ed.getOldEnabledEvents());
		// System.out.println(ed.getNewEnabledEvents());
		ed.setEventQueue(Database.updateEventQueue(ed));
		while (ed.getTime() <1000) {
			Event e1=ed.getEventQueue().get(0);
			System.out.println(e1);
			ed=Database.execute(e1, ed);
			System.out.println("-------------"+ed.getTime()+"--------------");
			ed.setTime(e1.getOccurenceTime());
			ed.getOldEnabledEvents().remove(0);
			ed.addToOldEnabledEvents("PatientArrival");
			ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getOldEnabledEvents(), ed));
			ed.setEventQueue(Database.updateEventQueue(ed));
			ed.display();
		 }
		
		
	
	
	}

}
