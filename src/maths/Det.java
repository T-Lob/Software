package maths;

/**
 * This class contains the Deterministic ProbabilityDistribution methods.
 */
public class Det implements ProbabilityDistribution {
	private double parameter;
	
	/**
	 * Sets de deterministic law
	 * @param parameter of the deterministic law
	 */
	public Det(double parameter) {
		this.parameter=parameter;
	}
	
	/**
	 * @return the parameter 
	 */
	@Override
	public double getSample() {
		return  this.parameter;
	}
}

