package others;
import java.util.ArrayList;

import Human.*;
import Resources.*;


public class Database {
	private static ArrayList<Patient> generatedPatients = new ArrayList<Patient>();
	private static ArrayList<Patient> arrivedPatients = new ArrayList<Patient> ();
	private static ArrayList<ArrayList<Patient>> registeredPatients = new ArrayList<ArrayList<Patient>>(5);
	private static ArrayList<Patient> waitingForTransportPatients = new ArrayList<Patient> ();
	private static ArrayList<Patient> waitingForVerdictPatients = new ArrayList<Patient>();
	private static ArrayList<ArrayList<BoxRoom>> boxRoomList = new ArrayList<ArrayList<BoxRoom>>(3);
	private static ArrayList<ArrayList<ShockRoom>> shockRoomList = new ArrayList<ArrayList<ShockRoom>>(3);
	private static ArrayList<Patient> releasedPatients = new ArrayList<Patient> ();
	private static ArrayList<ArrayList<Nurse>> nurseList = new ArrayList<ArrayList<Nurse>>(2);
	private static ArrayList<ArrayList<Physician>> physicianList = new ArrayList<ArrayList<Physician>>(2);
	private static ArrayList<ArrayList<Transporter>> transporterList = new ArrayList<ArrayList<Transporter>>(2);
	private static int time;
	private static String EDname;
	public static final RadioRoom radioRoom = new RadioRoom();
	public static final MRIRoom mriRoom = new MRIRoom();
	public static final BloodTestRoom bloodTestRoom = new BloodTestRoom();
	
	
	public static void createED(String EDname) {
		Database.EDname=EDname;
		for (int i=0 ; i<3; i++) {
			Database.physicianList.add(new ArrayList<Physician>());
			Database.nurseList.add(new ArrayList<Nurse>());
			Database.transporterList.add(new ArrayList<Transporter>());
			Database.shockRoomList.add(new ArrayList<ShockRoom>());
			Database.boxRoomList.add(new ArrayList<BoxRoom> ());
			Database.registeredPatients.add(generatedPatients);
		}
		Database.registeredPatients.add(generatedPatients);
		Database.registeredPatients.add(generatedPatients);
	}
	
	public static String getEDname() {
		return EDname;
	}

	public static int getTime() {
		return time;
	}
	public static void setTime(int time) {
		Database.time = time;
	}
	public static ArrayList<Patient> getGeneratedPatients() {
		return generatedPatients;
	}
	public static void addToGeneratedPatients(Patient patient) {
		Database.generatedPatients.add(patient);
	}
	public static ArrayList<Patient> getArrivedPatients() {
		return arrivedPatients;
	}
	public static void addToArrivedPatients(Patient patient) {
		Database.arrivedPatients.add(patient);
	}
	public static ArrayList<ArrayList<Patient>> getRegisteredPatients() {
		return registeredPatients;
	}
	public static void addToRegisteredPatients(Patient patient) {
		Database.registeredPatients.get(patient.getLevel()-1).add(patient);
	}
	public static ArrayList<Patient> getWaitingForTransportPatients() {
		return waitingForTransportPatients;
	}
	public static ArrayList<Patient> getWaitingForVerdictPatients() {
		return waitingForVerdictPatients;
	}
	public static ArrayList<ArrayList<BoxRoom>> getBoxRoomList() {
		return boxRoomList;
	}
	public static ArrayList<ArrayList<ShockRoom>> getShockRoomList() {
		return shockRoomList;
	}
	public static ArrayList<Patient> getReleasedPatients() {
		return releasedPatients;
	}
	public static ArrayList<ArrayList<Nurse>> getNurseList() {
		return nurseList;
	}
	public static ArrayList<ArrayList<Physician>> getPhysicianList() {
		return physicianList;
	}
	public static ArrayList<ArrayList<Transporter>> getTransporterList() {
		return transporterList;
	}
	
	public static void addToWaitingForTransportPatients(Patient patient) {
		Database.waitingForTransportPatients.add(patient);
	}
	
	public static void addToWaitingForVerdictPatients(Patient patient) {
		Database.waitingForVerdictPatients.add(patient);
	}
	
	public static void addToReleasedPatients(Patient patient) {
		Database.releasedPatients.add(patient);
	}
	public static void addToBoxRoomList(BoxRoom room) {
		Database.boxRoomList.get(0).add(room);
	}
	public static void addToShockRoomList(ShockRoom room) {
		Database.shockRoomList.get(0).add(room);
	}
	public static void addToNurseList(Nurse nurse) {
		Database.nurseList.get(0).add(nurse);
	}
	public static void addToPhysicianList(Physician phys) {
		Database.physicianList.get(0).add(phys);
	}
	public static void addToTransporterList(Transporter transporter) {
		Database.transporterList.get(0).add(transporter);
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