package others;
import java.util.ArrayList;
import java.util.HashMap;

import human.*;
import rooms.*;
import events.*;



public class ED {
	private  ArrayList<Patient> generatedPatients = new ArrayList<Patient>();
	private  ArrayList<Patient> arrivedPatients = new ArrayList<Patient> ();
	private  ArrayList<ArrayList<Patient>> registeredPatients = new ArrayList<ArrayList<Patient>>(5);
	private  ArrayList<Patient> waitingForTransportPatients = new ArrayList<Patient> ();
	private  ArrayList<Patient> waitingForVerdictPatients = new ArrayList<Patient>();
	private  ArrayList<ArrayList<BoxRoom>> boxRoomList = new ArrayList<ArrayList<BoxRoom>>(3);
	private  ArrayList<ArrayList<ShockRoom>> shockRoomList = new ArrayList<ArrayList<ShockRoom>>(3);
	private  ArrayList<Patient> releasedPatients = new ArrayList<Patient> ();
	private  ArrayList<ArrayList<Nurse>> nurseList = new ArrayList<ArrayList<Nurse>>(2);
	private  ArrayList<ArrayList<Physician>> physicianList = new ArrayList<ArrayList<Physician>>(2);
	private  ArrayList<ArrayList<Transporter>> transporterList = new ArrayList<ArrayList<Transporter>>(2);
	private ArrayList<Event> eventQueue = new ArrayList <Event> ();
	private ArrayList<Event> newEnabledEvents = new ArrayList <Event> ();
	private ArrayList<Event> oldEnabledEvents = new ArrayList <Event> ();
	private HashMap<Resource, Integer> state = new  HashMap<Resource, Integer> ();
	private  int time=1000;
	private  String EDname;
	public  final WaitingRoom waitingRoom = new WaitingRoom(this.EDname);
	public  final RadioRoom radioRoom = new RadioRoom(this.EDname);
	public  final MRIRoom mriRoom = new MRIRoom(this.EDname);
	public  final BloodTestRoom bloodTestRoom = new BloodTestRoom(this.EDname);
	
	
	public ED (String EDname) {
		this.EDname=EDname;
		for (int i=0 ; i<3; i++) {
			this.physicianList.add(new ArrayList<Physician>());
			this.nurseList.add(new ArrayList<Nurse>());
			this.transporterList.add(new ArrayList<Transporter>());
			this.shockRoomList.add(new ArrayList<ShockRoom>());
			this.boxRoomList.add(new ArrayList<BoxRoom> ());
			this.registeredPatients.add(generatedPatients);
		}
		this.registeredPatients.add(generatedPatients);
		this.registeredPatients.add(generatedPatients);
		Database.addToGeneratedEDs(this);
	}
	
	public String getEDname() {
		return EDname;
	}

	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public ArrayList<Patient> getGeneratedPatients() {
		return generatedPatients;
	}
	
	public void addToGeneratedPatients(Patient patient) {
		this.generatedPatients.add(patient);
	}
	
	public void removeFromGeneratedPatients(Patient patient) {
		this.generatedPatients.remove(patient);
	}
	
	public ArrayList<Patient> getArrivedPatients() {
		return arrivedPatients;
	}
	
	public void addToArrivedPatients(Patient patient) {
		this.arrivedPatients.add(patient);
	}
	
	public void removeFromArrivedPatients(Patient patient) {
		this.arrivedPatients.remove(patient);
	}
	
	public ArrayList<ArrayList<Patient>> getRegisteredPatients() {
		return registeredPatients;
	}
	
	public void addToRegisteredPatients(Patient patient) {
		this.registeredPatients.get(patient.getLevel()-1).add(patient);
	}
	
	public void removeFromRegisteredPatients(Patient patient) {
		this.registeredPatients.get(patient.getLevel()-1).remove(patient);
	}
	
	public ArrayList<Patient> getWaitingForTransportPatients() {
		return waitingForTransportPatients;
	}
	
	public ArrayList<Patient> getWaitingForVerdictPatients() {
		return waitingForVerdictPatients;
	}
	
	public ArrayList<ArrayList<BoxRoom>> getBoxRoomList() {
		return boxRoomList;
	}
	
	public ArrayList<ArrayList<ShockRoom>> getShockRoomList() {
		return shockRoomList;
	}
	
	public ArrayList<Patient> getReleasedPatients() {
		return releasedPatients;
	}
	
	public ArrayList<ArrayList<Nurse>> getNurseList() {
		return nurseList;
	}
	
	public ArrayList<ArrayList<Physician>> getPhysicianList() {
		return physicianList;
	}
	
	public ArrayList<ArrayList<Transporter>> getTransporterList() {
		return transporterList;
	}
	
	public void addToWaitingForTransportPatients(Patient patient) {
		this.waitingForTransportPatients.add(patient);
	}
	
	public void removeFromWaitingForTransportPatients(Patient patient) {
		this.waitingForTransportPatients.remove(patient);
	}
	
	public void addToWaitingForVerdictPatients(Patient patient) {
		this.waitingForVerdictPatients.add(patient);
	}
	
	public void removeFromWaitingForVerdictPatients(Patient patient) {
		this.waitingForVerdictPatients.remove(patient);
	}
	
	public void addToReleasedPatients(Patient patient) {
		this.releasedPatients.add(patient);
	}
	
	public void removeFromReleasedPatients(Patient patient) {
		this.releasedPatients.remove(patient);
	}
	
	public void addToBoxRoomList(BoxRoom room) {
		this.boxRoomList.get(0).add(room);
	}
	
	public void addToShockRoomList(ShockRoom room) {
		this.shockRoomList.get(0).add(room);
	}
	
	public void addToNurseList(Nurse nurse) {
		this.nurseList.get(0).add(nurse);
	}
	
	public void addToPhysicianList(Physician phys) {
		this.physicianList.get(0).add(phys);
	}
	
	public void addToTransporterList(Transporter transporter) {
		this.transporterList.get(0).add(transporter);
	}
	
	public ArrayList<Event> getEventQueue() {
		return eventQueue;
	}
	
	public void addToEventQueue(Event e) {
		this.eventQueue.add(e);
	}
	
	public void removeFromEventQueue(Event e) {
		this.eventQueue.remove(e);
	}
	
	
	public ArrayList<Event> getNewEnabledEvents() {
		return newEnabledEvents;
	}

	public ArrayList<Event> getOldEnabledEvents() {
		return oldEnabledEvents;
	}
	
	public void addToNewEnabledEvents(Event e) {
		this.newEnabledEvents.add(e);
	}
	
	public void removeNewEnabledEvents(Event e) {
		this.newEnabledEvents.remove(e);
	}
	
	public void addToOldEnabledEvents(Event e) {
		this.oldEnabledEvents.add(e);
	}
	
	public void removeOldEnabledEvents(Event e) {
		this.oldEnabledEvents.remove(e);
	}
	

	public BloodTestRoom getBloodTestRoom() {
		return bloodTestRoom;
	}

	public void sortBySelection(ArrayList<Room> rooms) {

	    int i, min, j;
		Room x;
	    for(i = 0 ; i < rooms.size() - 1 ; i++)
	    {
	    	min = i;
	        for(j = i+1 ; j < rooms.size(); j++){
	        	if(rooms.get(j).getPatient().getLevel() < rooms.get(min).getPatient().getLevel())
	        		min = j;
	        	if(min != i)
	        	{
	        		x = rooms.get(i);
	        		rooms.set(i,rooms.get(min));
	        		rooms.set(min, x);
	        	}
	        }
	    } 
	}
}
