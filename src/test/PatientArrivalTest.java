package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import events.PatientArrival;
import human.Patient;
import others.ED;

public class PatientArrivalTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@Test
	public void testExecute() {
		
		Patient patient1 = new Patient("Saint-Denis");
		Patient patient2 = new Patient("Saint-Denis");
		PatientArrival patArr1 = new PatientArrival("Saint-Denis", patient1);
		PatientArrival patArr2 = new PatientArrival("Saint-Denis", patient2);
		
		patArr1.execute();
		patArr2.execute();
		
		if (!patient1.getState().equalsIgnoreCase("arrived") || !patient2.getState().equalsIgnoreCase("arrived"))
			fail("Patients have wrong state");
	}

}
