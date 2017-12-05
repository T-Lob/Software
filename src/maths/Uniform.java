package maths;
import java.util.Random;

/**
 * This class contains the Uniform ProbabilityDistribution methods.
 */
public class Uniform implements ProbabilityDistribution {
	private Double a;
	private Double b;
	
	/**
	 * Sets The uniform law on [a;b] where a and b are doubles
	 * @param a start of the interval, double
	 * @param b end of the interval, double
	 */
	public Uniform(Double a, Double b) {
		this.a=a;
		this.b=b;
	}
	
	/**
	 * Sets the uniform law on [a;b] where a and b are int
	 * @param a start of the interval, int
	 * @param b end of the interval, int
	 */
	public Uniform(int a, int b){
		this.a=(double)a;
		this.b=(double)b;
	}
	
	/**
	 * @return a random number following the uniform law on [a;b]
	 */
	@Override
	public double getSample() {
		return (this.b-this.a)*new Random().nextDouble() + this.a;
	}

}
