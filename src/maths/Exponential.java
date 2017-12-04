package maths;
import java.util.Random;

public class Exponential implements ProbabilityDistribution {
	private double parameter;
	
	public Exponential(double parameter) {
		this.parameter=parameter;
	}
	
	@Override
	public double getSample() {
		return -1/parameter*Math.log(1-new Random().nextDouble());
	}
}

