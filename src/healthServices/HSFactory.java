package healthServices;

import maths.ProbabilityDistribution;

public class HSFactory {
	public static HealthServices createHS(String EDname, String HSname, ProbabilityDistribution PD) {
		if (HSname == null) {
			return null;
		}
		
		if (HSname.equalsIgnoreCase("BLOODTEST")){
			return new BloodTest(EDname, PD);
		} else if (HSname.equalsIgnoreCase("XRAY")){
			return new XRay(EDname, PD);
		} else if (HSname.equalsIgnoreCase("MRISCAN")) {
			return new MRIScan(EDname, PD);
		}
		return null;	
	}

}
