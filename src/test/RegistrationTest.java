package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import events.PatientArrival;
import events.Registration;
import human.Nurse;
import human.Patient;
import others.Database;
import others.ED;

public class RegistrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@SuppressWarnings("unused")
	@Test
	public void testExecute() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient1 = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 10);
		Patient patient2 = new Patient("Saint-Denis", "T", "Lob", "Silver", "L1", 15);
		Nurse nurse = new Nurse("Saint-Denis");
		PatientArrival patArr1 = new PatientArrival("Saint-Denis", patient1);
		PatientArrival patArr2 = new PatientArrival("Saint-Denis", patient2);
		Registration registration = new Registration("Saint-Denis");
		
		patArr1.execute();
		patArr2.execute();
		registration.execute();
		
		if (!ed.getRegisteredPatients().get(3).contains(patient1) || ed.getRegisteredPatients().get(3).size() != 1)
			fail("Patient 1 not registered");
		if (!ed.getRegisteredPatients().get(0).contains(patient2) || ed.getRegisteredPatients().get(0).size() != 1)
			fail("Patient 2 not registered");
		
	}

}
