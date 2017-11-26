package others;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private ArrayList<String> newEnabledEvents = new ArrayList <String> ();
	private ArrayList<String> oldEnabledEvents = new ArrayList <String> ();
	private HashMap<String, Integer> state = new  HashMap<String, Integer> ();
	private  int time;
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
			System.out.println("la bite");
		}
		this.registeredPatients.add(generatedPatients);
		this.registeredPatients.add(generatedPatients);
		this.newEnabledEvents.add("PatientArrival");
		this.oldEnabledEvents.add("PatientArrival");
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
	
	public ArrayList<Patient> getArrivedPatients() {
		return arrivedPatients;
	}
	
	public ArrayList<ArrayList<Patient>> getRegisteredPatients() {
		return registeredPatients;
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
	
	public ArrayList<Event> getEventQueue() {
		return eventQueue;
	}
	public void setEventQueue(ArrayList<Event> eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	public void addToEventQueue(Event e) {
		if (e !=null) {
		this.eventQueue.add(e);}
	}
	
	public void removeFromEventQueue(String type) {
		ArrayList<Event> list = new ArrayList<Event>();
		for (Event e:this.eventQueue) {
			if (e.getType()==type) {
				list.add(e);
			}
		}
		for (Event e:list) {
			this.eventQueue.remove(e);
		}
	}
	
	public ArrayList<String> getNewEnabledEvents() {
		return newEnabledEvents;
	}

	public ArrayList<String> getOldEnabledEvents() {
		return oldEnabledEvents;
	}
	
	public void addToNewEnabledEvents(String s) {
		if (!newEnabledEvents.contains(s))
		this.newEnabledEvents.add(s);
	}
	
	public void addToOldEnabledEvents(String s) {
		if (!oldEnabledEvents.contains(s))
		this.oldEnabledEvents.add(s);
	}

	public void setNewEnabledEvents(ArrayList<String> newEnabledEvents) {
		this.newEnabledEvents = newEnabledEvents;
	}

	public void setOldEnabledEvents(ArrayList<String> enabledEvents) {
		this.oldEnabledEvents = enabledEvents;
	}
	public Patient getNextGeneratedPatient() {
		if (this.generatedPatients.size() >0) {
			return this.generatedPatients.get(9);
		}
		return null;
	}
	public Patient getNextRegisteredPatient() {
		int i = 4;
		while (i >= 0 & this.registeredPatients.get(i).size()==0) {
			i-=1;	
		}
		if (i==-1) {
			return null;
		}
		return this.registeredPatients.get(i).get(0);
	}
	public Patient getNextWaitingForTransportPatient() {
		if (this.waitingForTransportPatients.size() >0) {
			return this.waitingForTransportPatients.get(9);
		}
		return null;
	}
	public Nurse getNextNurse() {
		if (this.nurseList.get(0).size() >0) {
			return this.nurseList.get(0).get(0);
		}
		return null;
	}
	public Physician getNextPhysician() {
		if (this.physicianList.get(0).size() >0) {
			return this.physicianList.get(0).get(0);
		}
		return null;
	}
	public Transporter getNextTransporter() {
		if (this.transporterList.get(0).size() >0) {
			return this.transporterList.get(0).get(0);
		}
		return null;
	}
	public BoxRoom getNextEmptyBoxRoom() {
		if (this.boxRoomList.get(0).size() >0) {
			return this.boxRoomList.get(0).get(0);
		}
		return null;
	}
	public ShockRoom getNextEmptyShockRoom() {
		if (this.shockRoomList.get(0).size() >0) {
			return this.shockRoomList.get(0).get(0);
		}
		return null;
	}
	public BoxRoom getNextOnlyPatientBoxRoom() {
		if (this.boxRoomList.get(1).size() >0) {
			return this.boxRoomList.get(1).get(0);
		}
		return null;
	}
	public ShockRoom getNextOnlyPatientShockRoom() {
		if (this.shockRoomList.get(1).size() >0) {
			return this.shockRoomList.get(1).get(0);
		}
		return null;
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
	public void sort(ArrayList<Event> eQ) {

	    int i, min, j;
		Event x;
	    for(i = 0 ; i < eQ.size() - 1 ; i++)
	    {
	    	min = i;
	        for(j = i+1 ; j < eQ.size(); j++){
	        	if(eQ.get(j).getOccurenceTime() < eQ.get(min).getOccurenceTime())
	        		min = j;
	        	if(min != i)
	        	{
	        		x = eQ.get(i);
	        		eQ.set(i,eQ.get(min));
	        		eQ.set(min, x);
	        	}
	        }
	    } 
	}
	public void sortEventQueue() {
		Collections.sort(this.eventQueue, new Comparator<Event>() {
	        @Override
	        public int compare(Event event2, Event event1)
	        {

	            return  new Integer(event2.getOccurenceTime()).compareTo(event1.getOccurenceTime());
	        }
	    });
		
	}
	

	public HashMap<String, Integer> getState() {
		return state;
	}

	public void updateState() {
		state.put("arrivedPatients",this.arrivedPatients.size());
		state.put("waitingForTransportPatients",this.waitingForTransportPatients.size());
		state.put("emptyBoxrooms", this.boxRoomList.get(0).size());
		state.put("onlyPatientBoxrooms", this.boxRoomList.get(1).size());
		state.put("emptyShockrooms", this.shockRoomList.get(0).size());
		state.put("onlyPatientShockrooms", this.boxRoomList.get(1).size());
		state.put("Nurse",this.nurseList.get(0).size());
		state.put("Physician",this.physicianList.get(0).size());
		state.put("Transporter", this.transporterList.get(0).size());
		state.put("BloodTest", -this.bloodTestRoom.getWaitingQueue().size() +1);
		state.put("MRI", -this.mriRoom.getWaitingQueue().size() +1);
		state.put("Radio", -this.radioRoom.getWaitingQueue().size() +1);
	}
	public ArrayList<String> getNewlyEnabledEvents() {
		ArrayList<String> newlyEnabledEvents = new ArrayList<String>(this.newEnabledEvents);
		for (String s: oldEnabledEvents) {
			newlyEnabledEvents.remove(s);
		}
		return newlyEnabledEvents;
		
	}
	public ArrayList<String> getNewlyDisabledEvents() {
		ArrayList<String> newlyDisabledEvents = new ArrayList<String>(this.oldEnabledEvents);
		for (String s: newEnabledEvents) {
			newlyDisabledEvents.remove(s);
		}
		return newlyDisabledEvents;
		
	}
	
	public void display() {
		for (Patient patient:generatedPatients) {
			System.out.println("Patient " + patient +" "+ patient.getName() + " "+ patient.getSeverityLevel() + " "+ patient.getArrivalTime() + " "+ patient.getState());
		}
		for (Patient patient:arrivedPatients) {
			System.out.println("Patient " + patient +" "+ patient.getName() + " "+ patient.getSeverityLevel() + " "+ patient.getArrivalTime() + " "+ patient.getState());
		}
		System.out.println(registeredPatients);
		System.out.println(eventQueue);
	}

	
}
