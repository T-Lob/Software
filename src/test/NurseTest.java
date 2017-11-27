package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Nurse;
import human.Patient;
import others.Database;
import others.ED;

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
		if (ed.getNurseList().get(0).contains(nurse) || ed.getNurseList().get(0).size() != 0)
			fail("Nurse added to the idle nurses");
		if (ed.getNurseList().get(2).contains(nurse) || ed.getNurseList().get(2).size() != 0)
			fail("Nurse added to the offduty nurses");
		
		
		nurse.setState("idle");
		if (!(nurse.getState().equalsIgnoreCase("idle")))
			fail("Nurse State is not idle");
		if (ed.getNurseList().get(1).contains(nurse) || ed.getNurseList().get(1).size() != 0)
			fail("Nurse added to the occupied nurses");
		if (!(ed.getNurseList().get(0).contains(nurse)) || ed.getNurseList().get(0).size() != 1)
			fail("Nurse not added to the idle nurses");
		if (ed.getNurseList().get(2).contains(nurse) || ed.getNurseList().get(2).size() != 0)
			fail("Nurse added to the offduty nurses");
		
		nurse.setState("offduty");
		if (!(nurse.getState().equalsIgnoreCase("offduty")))
			fail("Nurse State is not offduty");
		if (ed.getNurseList().get(1).contains(nurse) || ed.getNurseList().get(1).size() != 0)
			fail("Nurse added to the occupied nurses");
		if (ed.getNurseList().get(0).contains(nurse) || ed.getNurseList().get(0).size() != 0)
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
		fail("Not yet implemented");
	}

	@Test
	public void testEndOfInstallation() {
		fail("Not yet implemented");
	}
}
