package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Nurse;
import human.Patient;
import others.Database;
import others.ED;
import rooms.BoxRoom;

public class NurseTest {
	

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
	public void testSetState() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Nurse nurse = new Nurse("Saint-Denis");
		
		nurse.setState("busy");
		if (!(nurse.getState().equalsIgnoreCase("busy")))
			fail("Nurse State not busy");
		if (!(ed.getNurseList().get(1).contains(nurse)) || ed.getNurseList().get(1).size() != 1)
			fail("Nurse not added to the occupied nurses");
		if (ed.getNurseList().get(0).size() != 0)
			fail("Nurse added to the idle nurses");
		if (ed.getNurseList().get(2).size() != 0)
			fail("Nurse added to the offduty nurses");
		
		
		nurse.setState("idle");
		if (!(nurse.getState().equalsIgnoreCase("idle")))
			fail("Nurse State is not idle");
		if (ed.getNurseList().get(1).size() != 0)
			fail("Nurse added to the occupied nurses");
		if (!(ed.getNurseList().get(0).contains(nurse)) || ed.getNurseList().get(0).size() != 1)
			fail("Nurse not added to the idle nurses");
		if (ed.getNurseList().get(2).size() != 0)
			fail("Nurse added to the offduty nurses");
		
		nurse.setState("offduty");
		if (!(nurse.getState().equalsIgnoreCase("offduty")))
			fail("Nurse State is not offduty");
		if (ed.getNurseList().get(1).size() != 0)
			fail("Nurse added to the occupied nurses");
		if (ed.getNurseList().get(0).size() != 0)
			fail("Nurse added to the idle nurses");
		if (!(ed.getNurseList().get(2).contains(nurse)) || ed.getNurseList().get(2).size() != 1)
			fail("Nurse not added to the offduty nurses");
	}

	@Test
	public void testRegistration() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Nurse nurse = new Nurse("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		nurse.registration(patient);
		if (!patient.getState().equalsIgnoreCase("registered"))
			fail("The patient's state hasn't been modified");
		if (!ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("The patient hasn't been added to the registered patients Queue");
		if (ed.getArrivedPatients().contains(patient) || ed.getGeneratedPatients().contains(patient))
			fail("The patient hasn't been removed from the other queues");
		if (!patient.getLocation().equals(ed.waitingRoom))
			fail("The patient hasn't been place in the waiting room");
	}

	@Test
	public void testInstallation() {
		
		Nurse nurse = new Nurse("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 15);
		BoxRoom room = new BoxRoom("Saint-Denis");
		
		nurse.installation(patient, room);
		if (!nurse.getState().equalsIgnoreCase("busy"))
			fail("Nurse state not busy");
		if (!nurse.getCurrentPatient().equals(patient))
			fail("Wrong nurse's current patient");
		if (!patient.getState().equalsIgnoreCase("installed"))
			fail("Patient's state not transported");
		if (!patient.getDestination().equals(room))
			fail("Wrong patient's destination");
		if (!room.getState().equalsIgnoreCase("full"))
			fail("Wrong state for the room");
		if (!room.getPatient().equals(patient))
			fail("Wrong patient for the room");
	}

	@Test
	public void testEndOfInstallation() {
		
		Nurse nurse = new Nurse("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 15);
		BoxRoom room = new BoxRoom("Saint-Denis");
		
		nurse.installation(patient, room);
		nurse.endOfInstallation(patient);
		if (!nurse.getState().equalsIgnoreCase("idle"))
			fail("Wrong nurse state");
		if (nurse.getCurrentPatient() != null)
			fail("The nurse still has a patient");
		if (!patient.getLocation().equals(room))
			fail("Patient has a wrong location");
		if (patient.getDestination() != null)
			fail("The patient still has a destination");
		if (!patient.getState().equalsIgnoreCase("waiting"))
			fail("The patient's state is not waiting");
		if (!room.getState().equalsIgnoreCase("onlypatient"))
			fail("Wrong room state");
	}
}
