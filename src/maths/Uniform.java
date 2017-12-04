package maths;
import java.util.Random;

public class Uniform implements ProbabilityDistribution {
	private Double a;
	private Double b;
	
	public Uniform(Double a, Double b) {
		this.a=a;
		this.b=b;
	}
	
	public Uniform(int a, int b){
		this.a=(double)a;
		this.b=(double)b;
	}
	
	@Override
	public double getSample() {
		return (this.b-this.a)*new Random().nextDouble() + this.a;
	}

}
