package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.Det;

public class DetTest {

	@Test
	public void testGetSample() {
		Det det = new Det(3.12);
		
		if (det.getSample() != 4)
			fail ("det ne retourne pas le bon paramètre");
	}

}
