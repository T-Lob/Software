package maths;

public class Det implements ProbabilityDistribution {
	private double parameter;
	
	public Det(double parameter) {
		this.parameter=parameter;
	}
	
	@Override
	public double getSample() {
		return  this.parameter;
	}
}

