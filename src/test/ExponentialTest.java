package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.Exponential;

public class ExponentialTest {

	@Test
	public void testGetSample() {
		Exponential exp1 = new Exponential(0.2);
		double moy = 0;
		
		for (int i = 1; i<=100000; i++)
			moy += exp1.getSample();
		moy*=0.00001;
		
		if (moy<=5.4 || moy>=5.6)
			fail("La moyenne ne correspond pas");
	}

}
