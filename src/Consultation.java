import maths.Uniform;

public class Consultation extends HealthServices {
	
	public Consultation() {
		this.probabilityDistribution= new Uniform(5,20);
		this.duration=this.probabilityDistribution.getSample();
	}
	
	
	

}
