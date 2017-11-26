package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.Exponential;

public class ExponentialTest {

	@Test
	public void testGetSample() {
		Exponential exp1 = new Exponential(0.2);
		double moy = 0.001;
		
		for (int i = 1; i<=1000; i++)
			moy += exp1.getSample();
		
		if (moy<=4.9 & moy>=5.1)
			fail("La moyenne ne correspond pas");
	}

}
