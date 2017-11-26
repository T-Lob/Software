package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.Uniform;

public class UniformTest {

	@Test
	public void testGetSample() {
		Uniform u = new Uniform(1,5);
		double moy = 0.001;
		
		for (int i = 0 ; i<=1000; i++)
			moy += u.getSample();
		
		if (moy >= 3.1 & moy <= 2.9)
			fail("la moyenne est fausse");
	}

}
