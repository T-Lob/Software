package events;

import java.util.ArrayList;

import others.ED;

public abstract class Event {
	protected String name;
	protected ED ed;
	protected int occurenceTime;
	protected int id;
	protected String type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOccurenceTime() {
		return occurenceTime;
	}
	
	public void setOccurenceTime(int occurenceTime) {
		this.occurenceTime = occurenceTime;
	}
	
	public ED getED() {
		return ed;
	}
	
	public int getID() {
		return id;
	}
	
	
	public String getType() {
		return type;
	}

	public abstract void execute();
		

	public static ArrayList <String> updateEnabledEvents (ArrayList <String> enabledEvents, ED ed) {
		ed.setOldEnabledEvents(enabledEvents);
		if (ed.getState().get("arrivedPatients") > 0) {
			ed.addToNewEnabledEvents("Registration");
		}
		if (ed.getState().get("Nurse") >0 & ed.getNextPatient() != null) {
			if (ed.getNextPatient().getLevel() >= 4 & (ed.getState().get("emptyBoxrooms") > 0 || ed.getState().get("emptyShockrooms") > 0)) {
				ed.addToNewEnabledEvents("Installation");
				}
			else if (ed.getState().get("emptyBoxrooms") > 0) {
				ed.addToNewEnabledEvents("Installation");
			}
		}
		if (ed.getState().get("Physician") > 0 & (ed.getState().get("onlyPatienBoxrooms") > 0 || ed.getState().get("onlyPatientShockrooms") > 0)) {
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
	
}
	
			
