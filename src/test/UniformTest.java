package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.Uniform;

public class UniformTest {

	@Test
	public void testGetSample() {
		Uniform u = new Uniform(1,5);
		double moy = 0;
		
		for (int i = 0 ; i<=100000; i++)
			moy += u.getSample();
		moy*=0.00001;
		
		if (moy >= 2.6 || moy <= 2.4)
			fail("la moyenne est fausse");
	}

}
