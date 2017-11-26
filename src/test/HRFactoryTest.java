package test;

import static org.junit.Assert.*;

import org.junit.Test;

import human.HRFactory;
import human.HumanResource;
import human.Nurse;
import human.Physician;
import human.Transporter;
import others.ED;

public class HRFactoryTest {

	@Test
	public void testCreateHRStringString() {
		ED ed = new ED("Saint-Denis");
		HRFactory factory = new HRFactory();
		
		HumanResource phys = factory.createHR("Saint-Denis", "physician", "Léo");
		HumanResource nurse = factory.createHR("Saint-Denis", "nurse", "Simon");
		HumanResource transporter = factory.createHR("Saint-Denis", "transporter", "Pierre");
		
		if (!(phys instanceof Physician))
			fail("mauvaise instanciation de Physician");
		
		if (!(nurse instanceof Nurse))
			fail("mauvaise instanciation de Nurse");
		
		if (!(transporter instanceof Transporter))
			fail("mauvaise instanciation de Transporter");
	}
}
