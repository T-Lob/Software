package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import healthServices.Consultation;
import human.Patient;
import human.Physician;
import others.Database;
import others.ED;
import rooms.BoxRoom;

public class ConsultationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@Test
	public void testResult() {

		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		Consultation consultation = new Consultation("Saint-Denis");
		BoxRoom room = new BoxRoom("Saint-Denis");
		Physician phys = new Physician("Saint-Denis");
		double radio = 0;
		double bloodtest = 0;
		double mri = 0;
		
		patient.setDestination(room);
		patient.setPhysician(phys);
		patient.setLocation(room);
		for (int i=0 ; i <= 1000000; i++) {
			patient.setDestination(room);
			consultation.result(patient);
			if (patient.getDestination().equals(ed.radioRoom)) {
				radio++;
			} else if (patient.getDestination().equals(ed.bloodTestRoom)) {
				bloodtest++;
			} else if (patient.getDestination().equals(ed.mriRoom)) {
				mri++;
			}
		}
		radio*=0.0001;
		bloodtest*=0.0001;
		mri*=0.0001;
		
		if (radio<19.9 || radio>20.1)
			fail("Radio rate is wrong");
		if (bloodtest<39.9 || bloodtest>40.1)
			fail("Bloodtest rate is wrong");
		if (mri<4.9 || mri>5.1)
			fail("MRI rate is wrong");
	}
}
