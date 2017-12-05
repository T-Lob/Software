package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import events.EndOfInstallation;
import events.Installation;
import events.PatientArrival;
import events.Registration;
import events.ReplacePatient;
import human.Nurse;
import human.Patient;
import others.Database;
import others.ED;
import rooms.BoxRoom;

public class ReplacePatientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@Test
	public void testExecute() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patientL4 = new Patient("Saint-Denis", "Smo", "Koco", "Gold", "L4", 10);
		Patient patientL1 = new Patient("Saint-Denis", "T", "Lob", "Silver", "L1", 15);
		BoxRoom room = new BoxRoom("Saint-Denis");
		Nurse nurse = new Nurse("Saint-Denis");
		Database.remplacedPatient = patientL4;
		
		PatientArrival patientArrival4 = new PatientArrival("Saint-Denis", patientL4);
		PatientArrival patientArrival1 = new PatientArrival("Saint-Denis", patientL1);
		Registration registrationl4 = new Registration("Saint-Denis");
		Registration registrationl1 = new Registration("Saint-Denis");
		Installation installationl4 = new Installation("Saint-Denis", patientL4, nurse, room);
		EndOfInstallation endOfInstallationl4 = new EndOfInstallation(installationl4);
		ReplacePatient replace = new ReplacePatient("Saint-Denis");
		patientArrival4.execute();
		registrationl4.execute();
		installationl4.execute();
		endOfInstallationl4.execute();
		patientArrival1.execute();
		registrationl1.execute();
		
		Patient patientTest = new Patient("Saint-Denis");
		ed.getRegisteredPatients().get(3).add(patientTest);
		replace.execute();
		
		if (!ed.getRegisteredPatients().get(3).get(0).equals(patientL4) || ed.getRegisteredPatients().get(3).size() != 2)
			fail("L4 Patient isn't in the registered list or he's not on the right sport");
		if (!patientL4.getLocation().equals(ed.waitingRoom))
			fail("The patient is not in the waiting room");
	}

}
