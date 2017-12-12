package maths;


public class ProbabilityDistributionFactory {

	public static ProbabilityDistribution createPD(String PDType, int argument1, int argument2) {
		if (PDType == null) {
			return null;
		}
		
		if (PDType.equalsIgnoreCase("EXPONENTIAL")){
			return new Exponential(argument1);
		} else if (PDType.equalsIgnoreCase("UNIFORM")){
			return new Uniform(argument1,argument2);
		} else if (PDType.equalsIgnoreCase("DET")) {
			return new Det(argument1);
		}
		return null;	
	}

}
