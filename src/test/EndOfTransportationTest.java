package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import events.Transportation;
import human.Patient;
import human.Transporter;
import others.Database;
import others.ED;

public class EndOfTransportationTest {

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
		Patient patient3 = new Patient("Saint-Denis", "T", "Lob", "Silver", "L1", 15);
		Transporter trans1 = new Transporter("Saint-Denis");
		Transporter trans2 = new Transporter("Saint-Denis");
		Transporter trans3 = new Transporter("Saint-Denis");
		
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		patient1.setLocation(ed.waitingRoom);
		patient1.setDestination(ed.bloodTestRoom);
		patient1.setState("waitingForTransport");
		patient2.setLocation(ed.waitingRoom);
		patient2.setDestination(ed.mriRoom);
		patient2.setState("waitingForTransport");
		patient3.setLocation(ed.waitingRoom);
		patient3.setDestination(ed.radioRoom);
		patient3.setState("waitingForTransport");
		
		Transportation transport1 = new Transportation("Saint-Denis");
		transport1.execute();
		Transportation transport2 = new Transportation("Saint-Denis");
		transport2.execute();
		Transportation transport3 = new Transportation("Saint-Denis");
		transport3.execute();
		
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		ed.getEventQueue().get(0).execute();
		ed.getEventQueue().remove(0);
		
		if (!trans1.getState().equalsIgnoreCase("idle") || !trans2.getState().equalsIgnoreCase("idle") || !trans3.getState().equalsIgnoreCase("idle"))
			fail("Transporters are not idle");
		if (!patient1.getLocation().equals(ed.bloodTestRoom) || !patient2.getLocation().equals(ed.mriRoom) || !patient3.getLocation().equals(ed.radioRoom))
			fail("Patients not in the right rooms");
	}

}
