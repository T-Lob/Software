import java.util.ArrayList;

public class Database {
	private static ArrayList<Patient> generatedPatients = new ArrayList<Patient>();
	private static ArrayList<Patient> arrivedPatients = new ArrayList<Patient> ();
	private static ArrayList<ArrayList<Patient>> registeredPatients = new ArrayList<ArrayList<Patient>>();
	private static ArrayList<Patient> waitingForTransportPatients = new ArrayList<Patient> ();
	private static ArrayList<Patient> waitingForVerdictPatients = new ArrayList<Patient>();
	private static ArrayList<ArrayList<Room>> boxRoomList = new ArrayList<ArrayList<Room>>();
	private static ArrayList<ArrayList<Room>> shockRoomList = new ArrayList<ArrayList<Room>>();
	private static ArrayList<Patient> releasedPatients = new ArrayList<Patient> ();
	private static ArrayList<ArrayList<Nurse>> nurseList = new ArrayList<ArrayList<Nurse>>();
	private static ArrayList<ArrayList<Physician>> physicianList = new ArrayList<ArrayList<Physician>>();
	private static ArrayList<ArrayList<Transporter>> transporterList = new ArrayList<ArrayList<Transporter>>();
	
	
	
	
	
	
	
	
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
	public static void addToRegisteredPatients(ArrayList<Patient> categories) {
		Database.registeredPatients.add(categories);
	}
	public static ArrayList<Patient> getWaitingForTransportPatients() {
		return waitingForTransportPatients;
	}
	public static ArrayList<Patient> getPatientsWaitingForVerdictPatients() {
		return getPatientsWaitingForVerdictPatients();
	}
	public static ArrayList<ArrayList<Room>> getBoxRoomList() {
		return boxRoomList;
	}
	public static ArrayList<ArrayList<Room>> getShockRoomList() {
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
	
	public static void addToWaitingForTranportPatients(Patient patient) {
		Database.waitingForTransportPatients.add(patient);
	}
	
	public static void addToWaitingForVerdictPatients(Patient patient) {
		Database.waitingForVerdictPatients.add(patient);
	}
	
	public static void addToReleasedPatients(Patient patient) {
		Database.releasedPatients.add(patient);
	}
	public static void addToBoxRoomList(ArrayList<Room> categories) {
		Database.boxRoomList.add(categories);
	}
	public static void addToShockRoomList(ArrayList<Room> categories) {
		Database.shockRoomList.add(categories);
	}
	public static void addToNurseList(ArrayList<Nurse> categories) {
		Database.nurseList.add(categories);
	}
	public static void addToPhysicianList(ArrayList<Physician> categories) {
		Database.physicianList.add(categories);
	}
	public static void addToTransporterList(ArrayList<Transporter> categories) {
		Database.transporterList.add(categories);
	}
	
	public void sortBySelection(ArrayList<Room> rooms) {

	     int i, min, j;
		Room x;
	     for(i = 0 ; i < rooms.size() - 1 ; i++)
	     {
	         min = i;
	         for(j = i+1 ; j < rooms.size(); j++)
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
