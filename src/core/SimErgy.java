package core;


import java.util.ArrayList;

import events.Event;
import human.*;
import rooms.*;
import others.Database;
import others.ED;

public class SimErgy {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		
		System.out.println(ed.getRegisteredPatients().get(4));
		Patient patient  = new Patient ("ED");
		Patient patient2  = new Patient ("ED");
		Patient patient10  = new Patient ("ED");
		Patient patient21  = new Patient ("ED");
		Patient patient111  = new Patient ("ED");
		Patient patient212 = new Patient ("ED");
		Patient patient5  = new Patient ("ED");
		Patient patient6  = new Patient ("ED");
		ArrayList<Patient> L = new ArrayList<Patient>(ed.getGeneratedPatients());
		System.out.println(ed.getGeneratedPatients());
		Physician physician = (Physician) HRFactory.createHR("ED", "Physician");
		Nurse nurse = (Nurse) HRFactory.createHR("ED", "Nurse");
		Transporter transporter = (Transporter) HRFactory.createHR("ED", "Transporter");
		BoxRoom boxroom = new BoxRoom("ED");
		ShockRoom shockroom = new ShockRoom("ED");
		ShockRoom shockroom2 = new ShockRoom("ED");
		Physician physician2 = (Physician) HRFactory.createHR("ED", "Physician");
		Nurse nurse2 = (Nurse) HRFactory.createHR("ED", "Nurse");
		Transporter transporter2 = (Transporter) HRFactory.createHR("ED", "Transporter");
		BoxRoom boxroom2 = new BoxRoom("ED");
		ed.updateState();
		ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getOldEnabledEvents(), ed));
		ed.setEventQueue(Database.updateEventQueue(ed));
		while (ed.getTime()<1000) {
			Event e1=ed.getEventQueue().get(0);
			ed.setTime(e1.getOccurenceTime());
			ed=Database.execute(e1, ed);
			System.out.println(e1.getType()+ " executed at time: " +ed.getTime());
			ed.getNewEnabledEvents().remove(e1.getType());
			ed.addToNewEnabledEvents("PatientArrival");
			ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
			ed.setEventQueue(Database.updateEventQueue(ed));
			if(ed.getEventQueue().size()==0) {
				System.out.println("Final State:");
				for (Patient p: L) {
					System.out.println(p.getName() +" " + p.getSeverityLevel() + " arrived at: " +p.getArrivalTime() + " "+p.getState()+" at " + p.getReleaseTime());
					System.out.println(p.getName() +" LOS: " + p.LOS() + " -- DTDT: " +p.DTDT());
				}
				System.out.println("End of simulation, all the patients should be released.");
				System.out.println("----------------");
				break;
			}
			System.out.println("----------------");
			
		 }
		if(ed.getTime()>=1000) {
			System.out.println("State at time: " + ed.getTime() + ":");
			for (Patient p: L) {
				System.out.println(p.getName() +" "+p.getState()+" ");
			}
			System.out.println("Time out ! Some patients may not have been released yet.");
		}
		
		
	
	
	}

}
