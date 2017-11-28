package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Patient;
import human.Transporter;
import others.Database;
import others.ED;
import rooms.BoxRoom;

public class TransporterTest {

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
		Transporter transporter = new Transporter("Saint-Denis");
		
		transporter.setState("transporting");
		if (!(transporter.getState().equalsIgnoreCase("transporting")))
			fail("Transporter State not busy");
		if (!(ed.getTransporterList().get(1).contains(transporter)) || ed.getTransporterList().get(1).size() != 1)
			fail("Transporter not added to the occupied transporters");
		if (ed.getTransporterList().get(0).size() != 0)
			fail("Transporter added to the idle transporters");
		if (ed.getTransporterList().get(2).size() != 0)
			fail("Transporter added to the offduty transporters");
		
		
		transporter.setState("idle");
		if (!(transporter.getState().equalsIgnoreCase("idle")))
			fail("Transporter State is not idle");
		if (ed.getTransporterList().get(1).size() != 0)
			fail("Transporter added to the occupied transporters");
		if (!(ed.getTransporterList().get(0).contains(transporter)) || ed.getTransporterList().get(0).size() != 1)
			fail("Transporter not added to the idle transporters");
		if (ed.getTransporterList().get(2).size() != 0)
			fail("Transporter added to the offduty transporters");
		
		transporter.setState("offduty");
		if (!(transporter.getState().equalsIgnoreCase("offduty")))
			fail("Transporter State is not offduty");
		if (ed.getTransporterList().get(1).size() != 0)
			fail("Transporter added to the occupied transporters");
		if (ed.getTransporterList().get(0).size() != 0)
			fail("Transporter added to the idle transporters");
		if (!(ed.getTransporterList().get(2).contains(transporter)) || ed.getTransporterList().get(2).size() != 1)
			fail("Transporter not added to the offduty transporters");
	}

	@Test
	public void testSetCurrentPatient() {
		
		Transporter transporter = new Transporter("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		transporter.setCurrentPatient(patient);
		if (!transporter.getCurrentPatient().equals(patient))
			fail("Wrong current patient for transportation");
	}

	@Test
	public void testTransportation() {
		
		Transporter transporter = new Transporter("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 12);
		BoxRoom room = new BoxRoom("Saint-Denis");
		
		patient.setDestination(room);
		transporter.transportation(patient);
		if (!transporter.getState().equalsIgnoreCase("transporting"))
			fail("The transporter's state is not transporting");
		if (!transporter.getCurrentPatient().equals(patient))
			fail("Wrong transporter's current patient");
		if (!patient.getState().equalsIgnoreCase("transported"))
			fail("Wrong patient's state");
		if (!room.getState().equalsIgnoreCase("full"))
			fail("The room has a wrong room");
		if (!room.getPatient().equals(patient))
			fail("The room contains a wrong patient");
	}

	@Test
	public void testEndOfTransportation() {
		
		Transporter transporter = new Transporter("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 12);
		BoxRoom room = new BoxRoom("Saint-Denis");
		
		patient.setDestination(room);
		transporter.endOfTransportation(patient);
		if (!transporter.getState().equalsIgnoreCase("idle"))
			fail("The transporter's state is not idle");
		if (transporter.getCurrentPatient() != null)
			fail("Wrong transporter's current patient");
		if (!patient.getState().equalsIgnoreCase("waitingforexamination"))
			fail("Wrong patient's state");
		if (!patient.getLocation().equals(room))
			fail("Wrong patient's location");
		if (patient.getDestination() != null)
			fail("The patient still has a destination");
		
	}
}
