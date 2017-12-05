package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import events.Installation;
import events.Registration;
import human.Nurse;
import human.Patient;
import others.Database;
import others.ED;
import rooms.BoxRoom;
import rooms.ShockRoom;

public class InstallationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@Test
	public void testExecute() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient1 = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 10);
		Patient patient2 = new Patient("Saint-Denis", "T", "Lob", "Silver", "L1", 15);
		Nurse nurse1 = new Nurse("Saint-Denis");
		Nurse nurse2 = new Nurse("Saint-Denis");
		BoxRoom boxRoom = new BoxRoom("Saint-Denis");
		ShockRoom shockRoom = new ShockRoom("Saint-Denis");
		Registration registration = new Registration("Saint-Denis");
		Installation instal1 = new Installation("Saint-Denis", patient1, nurse1, boxRoom);
		Installation instal2 = new Installation("Saint-Denis", patient2, nurse2, shockRoom);
		
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		registration.execute();
		instal1.execute();
		instal2.execute();
		
		if (!patient1.getDestination().equals(boxRoom) || !patient2.getDestination().equals(shockRoom))
			fail("Patients have wrong destination");
		if (!boxRoom.getState().equalsIgnoreCase("reserved") || !shockRoom.getState().equalsIgnoreCase("reserved"))
			fail("Rooms have wrong state");
		if (!nurse1.getState().equalsIgnoreCase("busy") || !nurse2.getState().equalsIgnoreCase("busy"))
			fail("Nurses not busy");
		if (ed.getEventQueue().size() != 2)
			fail("Event Queue doesn't contain EndOfInstallation");
	}

}
