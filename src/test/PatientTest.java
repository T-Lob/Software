package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Patient;
import human.Physician;
import others.Database;
import others.ED;
import rooms.BoxRoom;
import rooms.Room;

public class PatientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}
	
	@Before
	public void setUp() throws Exception {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		ed.getGeneratedPatients().clear();
		ed.getArrivedPatients().clear();
		for (int i =0; i<3; i++) {
			ed.getNurseList().get(i).clear();
			ed.getRegisteredPatients().get(i).clear();
			ed.getPhysicianList().get(i).clear();
			ed.getTransporterList().get(i).clear();
		}
		ed.getRegisteredPatients().get(3).clear();
		ed.getRegisteredPatients().get(4).clear();
		ed.getWaitingForTransportPatients().clear();
		ed.getWaitingForVerdictPatients().clear();
		ed.getReleasedPatients().clear();
		for (int i = 0; i<2; i++) {
			ed.getBoxRoomList().get(i).clear();
			ed.getShockRoomList().get(i).clear();
		}
	}

	@Test
	public void testGetName() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getName().equalsIgnoreCase("smo"))
			fail("The patient has a wrong name");
	}

	@Test
	public void testSetName() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setName("Pierre");
		if(!patient.getName().equalsIgnoreCase("Pierre"))
			fail("The patient name has not been changed");
	}

	@Test
	public void testGetSurname() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getSurname().equalsIgnoreCase("koco"))
			fail("The patient has a wrong surname");
	}

	@Test
	public void testSetSurname() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setSurname("Blondin");
		if(!patient.getSurname().equalsIgnoreCase("blondin"))
			fail("The patient surname has not been changed");
	}

	@Test
	public void testGetID() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getID() != 6){
			fail("The patient has no ID");
		}
	}

	@Test
	public void testGetHealthIsurance() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getHealthInsurance().equalsIgnoreCase("Silver"))
			fail("The patient has a wrong health insurance");
	}

	@Test
	public void testGetSeverityLevel() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getSeverityLevel().equalsIgnoreCase("l3"))
			fail("The patient has a wrong severity level");
	}

	@Test
	public void testGetState() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getState().equalsIgnoreCase("generated"))
			fail("The patient has not generated as a state");
	}

	@Test
	public void testSetLocation() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Room location = new BoxRoom("Saint-Denis");
		
		patient.setLocation(location);
		if (!patient.getLocation().equals(location))
			fail("The patient's location hasn't been changed");
	}

	@Test
	public void testAddEventToHistory() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		String[] event = {"registration","10h"};
		patient.addEventToHistory(event);
		patient.getHistory();
	}

	@Test
	public void testGetArrivalTime() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getArrivalTime() != 10)
			fail("The patient has a wrong arrival time");
	}

	@Test
	public void testSetArrivalTime() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setArrivalTime(15);
		if (patient.getArrivalTime() != 15)
			fail("The patient's arrival time hasn't been changed");
	}

	@Test
	public void testSetPhysician() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Physician phys = new Physician("Saint-Denis");
		
		patient.setPhysician(phys);
		if (!patient.getPhysician().equals(phys))
			fail("The patient's has the wrong physician");
	}

	@Test
	public void testGetBill() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getBill() != 0)
			fail("The patient has a wrong bill");
	}

	@Test
	public void testAddToBill() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.addToBill(5);
		if (patient.getBill() != 5)
			fail("The patient's bill has not been added");
	}


	@Test
	public void testSetDestination() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Room destination = new BoxRoom("Saint-Denis");
		
		patient.setDestination(destination);
		if (!patient.getDestination().equals(destination))
			fail("The patient has a wrong destination");
	}

	@Test
	public void testGetLevel() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getLevel() != 3)
			fail("getLevel() doesn't work");
	}

	@Test
	public void testCharges() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.addToBill(20);
		if (patient.charges() != 16)
			fail("The charges are wrong for silver patients");
		patient.setHealthInsurance("Gold");
		if (patient.charges() != 10)
			fail("the charges are wrong for gold patients");
		patient.setHealthInsurance("None");
		if (patient.charges() != 20)
			fail("the charges are wrong for not insured patients");
	}

	@Test
	public void testSetState() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setState("arrived");
		if (!patient.getState().equalsIgnoreCase("arrived"))
			fail("Patient state hasn't been changed");
		
		if (ed.getGeneratedPatients().contains(patient) || ed.getGeneratedPatients().size() != 0)
			fail("Generated Patients List contains arrived patient");
		if (!(ed.getArrivedPatients().contains(patient)) || ed.getArrivedPatients().size() != 1)
			fail("Arrived Patients List doesn't contain arrived patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient) || ed.getRegisteredPatients().get(patient.getLevel()-1).size() != 0)
			fail("Registered Patients List contains arrived patient");
		if (ed.getWaitingForTransportPatients().contains(patient) || ed.getWaitingForTransportPatients().size() != 0)
			fail("Waiting for Transport Patients List contains arrived patient");
		if (ed.getWaitingForVerdictPatients().contains(patient) || ed.getWaitingForVerdictPatients().size() != 0)
			fail("Waiting for Verdict Patients List contains arrived patient");
		if (ed.getReleasedPatients().contains(patient) || ed.getReleasedPatients().size() != 0)
			fail("Released Patients List contains arrived patient");
		
		patient.setState("registered");
		if (ed.getGeneratedPatients().contains(patient) || ed.getGeneratedPatients().size() != 0)
			fail("Generated Patients List contains registered patient");
		if (ed.getArrivedPatients().contains(patient) || ed.getArrivedPatients().size() != 0)
			fail("Arrived Patients List contains registered patient");
		if (!(ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient)) || ed.getRegisteredPatients().get(patient.getLevel()-1).size() != 1)
			fail("Registered Patients List doesn't contain registered patient");
		if (ed.getWaitingForTransportPatients().contains(patient) || ed.getWaitingForTransportPatients().size() != 0)
			fail("Waiting for Transport Patients List contains registered patient");
		if (ed.getWaitingForVerdictPatients().contains(patient) || ed.getWaitingForVerdictPatients().size() != 0)
			fail("Waiting for Verdict Patients List contains registered patient");
		if (ed.getReleasedPatients().contains(patient) || ed.getReleasedPatients().size() != 0)
			fail("Released Patients List contains registered patient");
		
		patient.setState("waitingfortransport");
		if (ed.getGeneratedPatients().contains(patient) || ed.getGeneratedPatients().size() != 0)
			fail("Generated Patients List contains waiting for transport patient");
		if (ed.getArrivedPatients().contains(patient) || ed.getArrivedPatients().size() != 0)
			fail("Arrived Patients List contains waiting for transport patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient) || ed.getRegisteredPatients().get(patient.getLevel()-1).size() != 0)
			fail("Registered Patients List contains waiting for transport patient");
		if (!(ed.getWaitingForTransportPatients().contains(patient)) || ed.getWaitingForTransportPatients().size() != 1)
			fail("Waiting for Transport Patients List doesn't contain waiting for transport patient");
		if (ed.getWaitingForVerdictPatients().contains(patient) || ed.getWaitingForVerdictPatients().size() != 0)
			fail("Waiting for Verdict Patients List contains waiting for transport patient");
		if (ed.getReleasedPatients().contains(patient) || ed.getReleasedPatients().size() != 0)
			fail("Released Patients List contains waiting for transport patient");
		
		patient.setState("waitingforverdict");
		if (ed.getGeneratedPatients().contains(patient) || ed.getGeneratedPatients().size() != 0)
			fail("Generated Patients List contains waiting for verdict patient");
		if (ed.getArrivedPatients().contains(patient) || ed.getArrivedPatients().size() != 0)
			fail("Arrived Patients List contains waiting for verdict patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient) || ed.getRegisteredPatients().get(patient.getLevel()-1).size() != 0)
			fail("Registered Patients List contains waiting for verdict patient");
		if (ed.getWaitingForTransportPatients().contains(patient) || ed.getWaitingForTransportPatients().size() != 0)
			fail("Waiting for Transport Patients List contains waiting for verdict patient");
		if (!(ed.getWaitingForVerdictPatients().contains(patient)) || ed.getWaitingForVerdictPatients().size() != 1)
			fail("Waiting for Verdict Patients List doesn't contain waiting for verdict patient");
		if (ed.getReleasedPatients().contains(patient) || ed.getReleasedPatients().size() != 0)
			fail("Released Patients List contains waiting for verdict patient");
		
		patient.setState("released");
		if (ed.getGeneratedPatients().contains(patient) || ed.getGeneratedPatients().size() != 0)
			fail("Generated Patients List contains released patient");
		if (ed.getArrivedPatients().contains(patient) || ed.getArrivedPatients().size() != 0)
			fail("Arrived Patients List contains released patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient) || ed.getRegisteredPatients().get(patient.getLevel()-1).size() != 0)
			fail("Registered Patients List contains released patient");
		if (ed.getWaitingForTransportPatients().contains(patient) || ed.getWaitingForTransportPatients().size() != 0)
			fail("Waiting for Transport Patients List contains released patient");
		if (ed.getWaitingForVerdictPatients().contains(patient) || ed.getWaitingForVerdictPatients().size() != 0)
			fail("Waiting for Verdict Patients List contains released patient");
		if (!ed.getReleasedPatients().contains(patient) || ed.getReleasedPatients().size() != 1)
			fail("Released Patients List doesn't contain released patient");
		
		Patient patient2 = new Patient("Saint-Denis", "Clément", "Quesnel", "Gold", "L5", 20);
		patient.setState("registered");
		patient2.setState("registered");
		if (ed.getRegisteredPatients().get(0).contains(patient) || ed.getRegisteredPatients().get(0).contains(patient2) || ed.getRegisteredPatients().get(0).size() != 0)
			fail("Patient 1 or Patient 2 are in the L1 queue");
		if (ed.getRegisteredPatients().get(1).contains(patient) || ed.getRegisteredPatients().get(1).contains(patient2) || ed.getRegisteredPatients().get(1).size() != 0)
			fail("Patient 1 or Patient 2 are in the L2 queue");
		if (ed.getRegisteredPatients().get(2).contains(patient2))
			fail("Patient 2 is in the L3 queue");
		if (!ed.getRegisteredPatients().get(2).contains(patient) || ed.getRegisteredPatients().get(2).size() != 1)
			fail("Patient 1 isn't in the L3 queue");
		if (ed.getRegisteredPatients().get(3).contains(patient) || ed.getRegisteredPatients().get(3).contains(patient2) || ed.getRegisteredPatients().get(3).size() != 0)
			fail("Patient 1 or 2 are in the L4 queue");
		if (ed.getRegisteredPatients().get(4).contains(patient))
			fail("Patient 1 is in the L5 queue");
		if (!ed.getRegisteredPatients().get(4).contains(patient2) || ed.getRegisteredPatients().get(4).size() != 1)
			fail("Patient 2 isn't in the L5 queue");
		
	}
}
