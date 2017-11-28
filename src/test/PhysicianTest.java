package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import healthServices.Consultation;
import human.Patient;
import human.Physician;
import others.Database;
import others.ED;

public class PhysicianTest {

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
		Physician phys = new Physician("Saint-Denis");
		
		phys.setState("visiting");
		if (!(phys.getState().equalsIgnoreCase("visiting")))
			fail("Physician State not busy");
		if (!(ed.getPhysicianList().get(1).contains(phys)) || ed.getPhysicianList().get(1).size() != 1)
			fail("Physician not added to the occupied physicians");
		if (ed.getPhysicianList().get(0).size() != 0)
			fail("Physician added to the idle physicians");
		if (ed.getPhysicianList().get(2).size() != 0)
			fail("Physician added to the offduty physicians");
		
		
		phys.setState("idle");
		if (!(phys.getState().equalsIgnoreCase("idle")))
			fail("Physician State is not idle");
		if (ed.getPhysicianList().get(1).size() != 0)
			fail("Physician added to the occupied physicians");
		if (!(ed.getPhysicianList().get(0).contains(phys)) || ed.getPhysicianList().get(0).size() != 1)
			fail("Physician not added to the idle physicians");
		if (ed.getPhysicianList().get(2).size() != 0)
			fail("Physician added to the offduty physicians");
		
		phys.setState("offduty");
		if (!(phys.getState().equalsIgnoreCase("offduty")))
			fail("Physician State is not offduty");
		if (ed.getPhysicianList().get(1).size() != 0)
			fail("Physician added to the occupied physicians");
		if (ed.getPhysicianList().get(0).size() != 0)
			fail("Physician added to the idle physicians");
		if (!(ed.getPhysicianList().get(2).contains(phys)) || ed.getPhysicianList().get(2).size() != 1)
			fail("Physician not added to the offduty physicians");
	}

	@Test
	public void testGetUsername() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		
		if (!phys.getUsername().equalsIgnoreCase("Smokoco"))
			fail("Wrong username");
	}

	@Test
	public void testSetUsername() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		
		phys.setUsername("T-Lob");
		if (!phys.getUsername().equalsIgnoreCase("t-lob"))
			fail("Username not changed");
	}

	@Test
	public void testGetCurrentPatients() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		Patient patient = new Patient("Saint-Denis");
		
		phys.getCurrentPatients().add(patient);
		if (!phys.getCurrentPatients().get(0).equals(patient))
			fail("Current Patients not updated");
	}

	@Test
	public void testGetHistoryPatients() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		Patient patient = new Patient("Saint-Denis");
		
		phys.getHistoryPatients().add(patient);
		if (!phys.getHistoryPatients().get(0).equals(patient))
			fail("History Patients not updated");
	}

	@Test
	public void testGetMessageBox() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		
		phys.getMessageBox().add("bonjour");
		if (!phys.getMessageBox().get(0).equalsIgnoreCase("bonjour"))
			fail("Wrong Message Box");
	}

	@Test
	public void testGetLastMessage() {
		
		Physician phys = new Physician("Saint-Denis", "Hamza", "Kadiri", "Smokoco");
		
		phys.getMessageBox().add("bonjour");
		phys.getMessageBox().add("hello");
		if (!phys.getLastMessage().equals("hello"))
			fail("Wrong last message in the Message Box");
	}

	@Test
	public void testConsultation() {
		
		Physician phys = new Physician("Saint-Denis");
		Consultation consultation = new Consultation("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		phys.consultation(patient, consultation);
		if (!(phys.getState().equalsIgnoreCase("visiting")))
			fail("Wrong physician state after consultation");
		if (!phys.getCurrentPatients().get(0).equals(patient) || phys.getCurrentPatients().size() != 1)
			fail("Current Patients not updated after consultation");
		if (!phys.getHistoryPatients().get(0).equals(patient) || phys.getHistoryPatients().size() != 1)
			fail("History Patients not updated after consultation");
		if (!patient.getPhysician().equals(phys))
			fail("Patient's physician not updated after consultation");
		if (!patient.getState().equalsIgnoreCase("visited"))
			fail("Patient's state not updated after consultation");
	}

	@Test
	public void testEndOfConsultation() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerdict() {
		
		Physician phys = new Physician("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		phys.getCurrentPatients().add(patient);
		phys.verdict(patient);
		if (phys.getCurrentPatients().size() != 0)
			fail("Current patients' list still contains patient");
		if (phys.getState().equalsIgnoreCase("released"))
			fail("Wrong patient's state");
	}

}
