package others;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import human.*;
import maths.ProbabilityDistribution;
import rooms.*;
import events.*;



public class ED {
	public  ArrayList<String[]> toBeGeneratedPatients = new ArrayList<String[]>();
	private  ArrayList<Patient> generatedPatients = new ArrayList<Patient>();
	private  ArrayList<Patient> historyOfPatients = new ArrayList<Patient>();
	private  ArrayList<Patient> arrivedPatients = new ArrayList<Patient> ();
	private  ArrayList<ArrayList<Patient>> registeredPatients = new ArrayList<ArrayList<Patient>>();
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
	private int instanciedBoxRooms;
	private int instanciedShockRooms;
	private  String EDname;
	private PatientGeneration L1 = new PatientGeneration(this.EDname,"L1");
	private PatientGeneration L2 = new PatientGeneration(this.EDname,"L2");
	private PatientGeneration L3 = new PatientGeneration(this.EDname,"L3");
	private PatientGeneration L4 = new PatientGeneration(this.EDname,"L4");
	private PatientGeneration L5 = new PatientGeneration(this.EDname,"L5");
	public  final WaitingRoom waitingRoom = new WaitingRoom(this.EDname);
	public  final RadioRoom radioRoom = new RadioRoom(this.EDname);
	public  final MRIRoom mriRoom = new MRIRoom(this.EDname);
	public  final BloodTestRoom bloodTestRoom = new BloodTestRoom(this.EDname);
	
	
	@SuppressWarnings("unchecked")
	public ED (String EDname) {
		ArrayList<Patient> L = new ArrayList<Patient>();
		this.EDname=EDname;
		for (int i=0 ; i<3; i++) {
			ArrayList<Patient> L1 = new ArrayList<Patient>();
			this.physicianList.add(new ArrayList<Physician>());
			this.nurseList.add(new ArrayList<Nurse>());
			this.transporterList.add(new ArrayList<Transporter>());
			this.shockRoomList.add(new ArrayList<ShockRoom>());
			this.boxRoomList.add(new ArrayList<BoxRoom> ());
			this.registeredPatients.add((ArrayList<Patient>) L1.clone());
		}
		this.registeredPatients.add((ArrayList<Patient>) L.clone());
		this.registeredPatients.add((ArrayList<Patient>) L.clone());
		this.newEnabledEvents.add("PatientArrival");
		this.oldEnabledEvents.add("PatientArrival");
		Database.addToGeneratedEDs(this);
		L1.addGeneration(this);
		L2.addGeneration(this);
		L3.addGeneration(this);
		L4.addGeneration(this);
		L5.addGeneration(this);
		sortTBGP();
		
	}
	
	public String getEDname() {
		return EDname;
	}


	public int getInstanciedBoxRooms() {
		return instanciedBoxRooms;
	}

	public void upInstanciedBoxRooms() {
		this.instanciedBoxRooms +=1;
	}

	
	
	public int getInstanciedShockRooms() {
		return instanciedShockRooms;
	}

	public void upInstanciedShockRooms() {
		this.instanciedShockRooms += 1;
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
			return this.generatedPatients.get(0);
		}
		return null;
	}
	public Patient getNextRegisteredPatient() {
		int i = 0;
		while (i <= 4)  {
			if (this.registeredPatients.get(i).size()==0) {
				i+=1;
			}
			else  {
				return this.registeredPatients.get(i).get(0);
			}
		}
			return null;
		}
		
	public Patient getNextWaitingForTransportPatient() {
		if (this.waitingForTransportPatients.size() >0) {
			return this.waitingForTransportPatients.get(0);
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
	    AbortConsultation e;
	    ArrayList<Event> fakeEQ = new ArrayList<Event>(eQ);
	    for (Event event: fakeEQ) {
    			if(event.getOccurenceTime()>Database.getTime())
	    				break;
	    		if(event.getType()=="AbortConsultation") {
	    			e=(AbortConsultation) event;
	    			int index = eQ.indexOf(e);
		    		eQ.remove(index);
		    		eQ.add(0,e);
		    		break;
	    			}
	    }
	}
	public void sortEventQueue() {
		Collections.sort(this.eventQueue, new Comparator<Event>() {
	        @Override
	        public int compare(Event event2, Event event1)
	        {

	            return  new Double(event2.getOccurenceTime()).compareTo(event1.getOccurenceTime());
	        }
	       
	    });
		AbortConsultation e;
	    ArrayList<Event> fakeEQ = new ArrayList<Event>(eventQueue);
	    for (Event event: fakeEQ) {
    			if(event.getOccurenceTime()>Database.getTime())
	    				break;
	    		if(event.getType()=="AbortConsultation") {
	    			e=(AbortConsultation) event;
	    			int index = eventQueue	.indexOf(e);
		    		eventQueue.remove(index);
		    		eventQueue.add(0,e);
		    		break;
	    			}
	    }
		
	}
	
	public void sortTBGP() {
		Collections.sort(this.toBeGeneratedPatients, new Comparator<String[]>() {
	        @Override
	        public int compare(String[] list2, String[] list1)
	        {

	            return  new Double(Double.parseDouble(list2 [1])).compareTo(Double.parseDouble(list1[1]));
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
		state.put("fullBoxrooms", this.boxRoomList.get(2).size());
		state.put("emptyShockrooms", this.shockRoomList.get(0).size());
		state.put("onlyPatientShockrooms", this.shockRoomList.get(1).size());
		state.put("fullShockrooms", this.shockRoomList.get(2).size());
		state.put("Nurse",this.nurseList.get(0).size());
		state.put("Physician",this.physicianList.get(0).size());
		state.put("Transporter", this.transporterList.get(0).size());
		state.put("BloodTest", this.bloodTestRoom.getWaitingQueue().size());
		state.put("MRI", this.mriRoom.getWaitingQueue().size());
		state.put("XRay", this.radioRoom.getWaitingQueue().size());
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

	public ArrayList<String[]> getToBeGeneratedPatients() {
		return toBeGeneratedPatients;
	}

	public void addToBeGeneratedPatients(String[] arrayList) {
		this.toBeGeneratedPatients.add(arrayList);
	}

	public PatientGeneration getL1() {
		return L1;
	}

	public void setL1(PatientGeneration l1) {
		L1 = l1;
	}

	public PatientGeneration getL2() {
		return L2;
	}

	public void setL2(PatientGeneration l2) {
		L2 = l2;
	}

	public PatientGeneration getL3() {
		return L3;
	}

	public void setL3(PatientGeneration l3) {
		L3 = l3;
	}

	public PatientGeneration getL4() {
		return L4;
	}

	public void setL4(PatientGeneration l4) {
		L4 = l4;
	}

	public PatientGeneration getL5() {
		return L5;
	}

	public void setL5(PatientGeneration l5) {
		L5 = l5;
	}
	
	public Patient getPatientbyId(int ID) {
		for (Patient patient:generatedPatients) {
			if (patient.getID()==ID) {
				return patient;
			}
		}
		return null;
	}
	
	public void display() {
		System.out.println("------------------------------");
		System.out.println("State at time: " + Database.getTime() + ":");
		System.out.println("Generated Patients");
		for (Patient p:historyOfPatients) {
			System.out.println(p.getName() +" " + p.getSeverityLevel() + " Arrival Time: " +p.getArrivalTime() + " "+p.getLastHistoryItem());
			//System.out.println(p.getName() +" LOS: " + p.LOS() + " -- DTDT: " +p.DTDT());
		}
		
		System.out.print("\nIdle Nurses: ");
		for (Nurse nurse:nurseList.get(0)) {
			System.out.print(nurse.getName() + "; ");
		}
		System.out.print("\nBusy Nurses: ");
		for (Nurse nurse:nurseList.get(1)) {
			System.out.print(nurse.getName() + "; ");
		}
		System.out.print("\nOffduty Nurses: ");
		for (Nurse nurse:nurseList.get(2)) {
			System.out.print(nurse.getName() + "; ");
		}
		System.out.print("\nIdle Transporter: ");
		for (Physician physician:physicianList.get(0)) {
			System.out.print(physician.getName() + "; ");
		}
		System.out.print("\nBusy Physicians: ");
		for (Physician physician:physicianList.get(1)) {
			System.out.print(physician.getName() + "; ");
		}
		System.out.print("\nOffduty Physicians: ");
		for (Physician physician:physicianList.get(2)) {
			System.out.print(physician.getName() + "; ");
		}
		System.out.print("\nIdle Transporters: ");
		for (Transporter transporter:transporterList.get(0)) {
			System.out.print(transporter.getName() + "; ");
		}
		System.out.print("\nBusy Transporters: ");
		for (Transporter transporter:transporterList.get(1)) {
			System.out.print(transporter.getName() + "; ");
		}
		System.out.print("\nOffduty Transporters: ");
		for (Transporter transporter:transporterList.get(2)) {
			System.out.print(transporter.getName() + "; ");
		}
		
		System.out.println("\n------------------------------");
	}

	public ArrayList<Patient> getHistoryOfPatients() {
		return historyOfPatients;
	}

	public void setHistoryOfPatients(ArrayList<Patient> historyOfPatients) {
		this.historyOfPatients = historyOfPatients;
	}
}
