package others;

import java.util.ArrayList;

import events.Event;
import events.EventFactory;
import events.Registration;


public class Database {
	private static  ArrayList<ED> generatedEDs = new ArrayList<ED>();

	public static ArrayList<ED> getGeneratedEDs() {
		return generatedEDs;
	}
	
	public static void addToGeneratedEDs(ED ed) {
		Database.generatedEDs.add(ed);
	}

	public static ED getEDbyName(String edName) {
		for (ED ed:generatedEDs) {
			if (ed.getEDname().equalsIgnoreCase(edName)) {
				return ed;
			}
		}
		return null;
	}
	public static ArrayList <String> updateEnabledEvents (ArrayList <String> enabledEvents, ED ed) {
		ed.setOldEnabledEvents(new ArrayList<String>(ed.getNewEnabledEvents()));
		ed.addToNewEnabledEvents("PatientArrival");
		ed.addToNewEnabledEvents("endOfInstallation");
		ed.addToNewEnabledEvents("endOfConsultation");
		ed.addToNewEnabledEvents("endOfTransportation");
		if (ed.getState().get("Nurse") > 0 & ed.getState().get("arrivedPatients") > 0) {
			ed.addToNewEnabledEvents("Registration");
			ed.addToEventQueue(new Registration(ed.getEDname()));
		}
		if (ed.getState().get("Nurse") > 0 & ed.getNextRegisteredPatient() != null) {
			if (ed.getNextRegisteredPatient().getLevel() >= 4 & (ed.getState().get("emptyBoxrooms") > 0 || ed.getState().get("emptyShockrooms") > 0)) {
				ed.addToNewEnabledEvents("Installation");
				}
			else if (ed.getState().get("emptyBoxrooms") > 0) {
				ed.addToNewEnabledEvents("Installation");
			}
		}
		if (ed.getState().get("Physician") > 0 & (ed.getState().get("onlyPatientBoxrooms") > 0 || ed.getState().get("onlyPatientShockrooms") > 0)) {
			ed.addToNewEnabledEvents("Consultation");
		}
		if (ed.getState().get("Transporter") >0 & ed.getState().get("waitingForTransportPatients") >0) {
			ed.addToNewEnabledEvents("Transportation");
		}
		if (ed.mriRoom.getState()=="empty") {
			ed.addToNewEnabledEvents("mriExamination");
		}
		if (ed.bloodTestRoom.getState()=="empty") {
			ed.addToNewEnabledEvents("bloodTestExamination");
		}
		if (ed.radioRoom.getState()=="empty") {
			ed.addToNewEnabledEvents("radioExamination");
		}
		
		return ed.getNewEnabledEvents();
	}
	public static ArrayList <Event> updateEventQueue (ED ed) {
		ArrayList<String> newlyEnabledEvents = ed.getNewlyEnabledEvents();
		ArrayList<String> newlyDisabledEvents = ed.getNewlyDisabledEvents();
		for (String s: newlyDisabledEvents) {
			ed.removeFromEventQueue(s);
		}
		for (String s: newlyEnabledEvents) {
			Event e=EventFactory.createEvent(ed.getEDname(), s);
			ed.addToEventQueue(e);	
		}
		ed.sortEventQueue();
		return ed.getEventQueue();
	}
	public static ED execute (Event e, ED ed) {
		e.execute();
		ed.getEventQueue().remove(e);
		ed.updateState();
		return ed;
		
	}
}