package others;

import java.util.ArrayList;
import java.util.Arrays;

import events.ConsultationEvent;
import events.Event;
import events.EventFactory;
import human.Patient;
import rooms.Room;


public class Database {
	private static  ArrayList<ED> generatedEDs = new ArrayList<ED>();
	private static  ArrayList<ConsultationEvent> generatedConsultations = new ArrayList<ConsultationEvent>();
	public static Patient remplacedPatient = null;
	private static  ArrayList <Object> generatedResources = new ArrayList <Object> ();
	private  ArrayList<Patient> toBeGeneratedPatients = new ArrayList<Patient>();
	private static double time;
	

	public static ArrayList<ED> getGeneratedEDs() {
		return generatedEDs;
	}
	
	public static ArrayList<ConsultationEvent> getGeneratedConsultations() {
		return generatedConsultations;
	}

	public static void addToGeneratedEDs(ED ed) {
		Database.generatedEDs.add(ed);
	}
	
	public static void addToGeneratedResources(Object o) {
		Database.generatedResources.add(o);
	}
	
	public static void addToGeneratedConsultations(ConsultationEvent c) {
		Database.generatedConsultations.add(c);
	}

	public static ED getEDbyName(String edName) {
		for (ED ed:generatedEDs) {
			if (ed.getEDname().equalsIgnoreCase(edName)) {
				return ed;
			}
		}
		return null;
	}
	public static ConsultationEvent getConsultationbyPatient(Patient patient) {
		for (ConsultationEvent c:generatedConsultations) {
			if (c.getPatient()==remplacedPatient) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Object> getGeneratedResources() {
		return generatedResources;
	}

	public static ArrayList <String> updateEnabledEvents (ArrayList <String> enabledEvents, ED ed) {
		ed.setOldEnabledEvents(new ArrayList<String>(ed.getNewEnabledEvents()));
		ed.addToNewEnabledEvents("PatientArrival");
		ed.addToNewEnabledEvents("endOfInstallation");
		ed.addToNewEnabledEvents("endOfConsultation");
		ed.addToNewEnabledEvents("endOfTransportation");
		boolean verdict =false;
		boolean emergency = false;
		remplacedPatient=null;
		if ((ed.getRegisteredPatients().get(0).size() >0 || ed.getRegisteredPatients().get(1).size() >0) & ed.getState().get("fullBoxrooms") == ed.getInstanciedBoxRooms() & ed.getState().get("fullShockrooms") == ed.getInstanciedShockRooms()) { 
		}
		for(Patient patient : ed.getWaitingForVerdictPatients()) {
			if (patient.getPhysician().getState().equalsIgnoreCase("idle")) {
				verdict = true;
				break;
			}
		}
		if (ed.getState().get("Nurse") > 0 & ed.getState().get("arrivedPatients") > 0) {
			ed.addToNewEnabledEvents("Registration");
		}
		if (emergency) {
			{	for (Room room: ed.getBoxRoomList().get(2)) { // si pas possible checker les busy rooms et si possible jarter un patient pas trop malade (on recup la room)
					if (remplacedPatient==null) {
					remplacedPatient = room.getPatient();
					}
					if (room.getPatient().getLevel() > remplacedPatient.getLevel()) {
					remplacedPatient=room.getPatient();	
					}		
				}
				for (Room room: ed.getShockRoomList().get(2)) {
					if (remplacedPatient==null) {
					remplacedPatient = room.getPatient();
					}
					if (room.getPatient().getLevel() > remplacedPatient.getLevel()) {
					remplacedPatient=room.getPatient();	
					}		
				}
				ed.addToNewEnabledEvents("AbortConsultation");
			}
		}
		if (ed.getState().get("Nurse") > 0 & ed.getNextRegisteredPatient() != null) {
			if (ed.getNextRegisteredPatient().getLevel() <= 2 & (ed.getState().get("emptyBoxrooms") > 0 || ed.getState().get("emptyShockrooms") > 0)) {
				ed.addToNewEnabledEvents("Installation");
				}
			else if (ed.getState().get("emptyBoxrooms") > 0) {
				ed.addToNewEnabledEvents("Installation");
			}
		}
		if (ed.getState().get("Physician") > 0 & ed.getState().get("onlyPatientShockrooms") > 0) {
			ed.addToNewEnabledEvents("Consultation");
		} else if (ed.getState().get("Physician") > 0 & ed.getState().get("onlyPatientBoxrooms") > 0) {
			ed.addToNewEnabledEvents("Consultation");
		}
		if (ed.getState().get("Transporter") >0 & ed.getState().get("waitingForTransportPatients") >0) {
			ed.addToNewEnabledEvents("Transportation");
		}
		if (ed.mriRoom.getState()=="empty" & ed.getState().get("MRI")>0) {
			ed.addToNewEnabledEvents("MRI");
		}
		if (ed.bloodTestRoom.getState()=="empty" & ed.getState().get("BloodTest")>0) {
			ed.addToNewEnabledEvents("BloodTest");
		}
		if (ed.radioRoom.getState()=="empty" & ed.getState().get("XRay")>0) {
			ed.addToNewEnabledEvents("Xray");
		}

		if (verdict) {
			ed.addToNewEnabledEvents("Verdict");
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
		if (Database.getTime()>Double.parseDouble(ed.toBeGeneratedPatients.get(0)[1])) {
			for (int i=0;i<5;i++) {
			ed.toBeGeneratedPatients.get(i)[1]=String.valueOf(Database.time+Double.parseDouble(ed.toBeGeneratedPatients.get(i)[3]));
		}
		}
			
		return ed;
		
	}

	public static double getTime() {
		return time;
	}

	public static void setTime(double time) {
		Database.time = time;
	}

	public ArrayList<Patient> getToBeGeneratedPatients() {
		return toBeGeneratedPatients;
	}
	
	public static void addGeneration(String severityLevel, String EDname) {
		
		
		if (severityLevel.equalsIgnoreCase("L1")){
			getEDbyName(EDname).getL1().addGeneration(getEDbyName(EDname));
		} else if (severityLevel.equalsIgnoreCase("L2")){
			getEDbyName(EDname).getL2().addGeneration(getEDbyName(EDname));
		} else if (severityLevel.equalsIgnoreCase("L3")){
			getEDbyName(EDname).getL3().addGeneration(getEDbyName(EDname));
		} else if (severityLevel.equalsIgnoreCase("L4")){
			getEDbyName(EDname).getL4().addGeneration(getEDbyName(EDname));
		} else if (severityLevel.equalsIgnoreCase("L5")){
			getEDbyName(EDname).getL5().addGeneration(getEDbyName(EDname));
		}
		getEDbyName(EDname).sortTBGP();
		System.out.println(Arrays.toString(getEDbyName(EDname).getToBeGeneratedPatients().get(0)));
		System.out.println(Arrays.toString(getEDbyName(EDname).getToBeGeneratedPatients().get(1)));
		System.out.println(Arrays.toString(getEDbyName(EDname).getToBeGeneratedPatients().get(2)));
		System.out.println(Arrays.toString(getEDbyName(EDname).getToBeGeneratedPatients().get(3)));
		System.out.println(Arrays.toString(getEDbyName(EDname).getToBeGeneratedPatients().get(4)));
	}
		

	
}