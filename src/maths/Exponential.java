package maths;
import java.util.Random;

/**
 * This class contains the Exponential ProbabilityDistribution methods.
 */
public class Exponential implements ProbabilityDistribution {
	private double parameter;
	
	/**
	 * Sets the exponential law of parameter parameter.
	 * @param parameter The parameter of the Exponential law.
	 */
	public Exponential(double parameter) {
		this.parameter=parameter;
	}
	
	/**
	 * @return a random number following the Exponential law of parameter parameter
	 */
	@Override
	public double getSample() {
		return -1/parameter*Math.log(1-new Random().nextDouble());
	}
}

