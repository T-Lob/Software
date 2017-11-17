import maths.ProbabilityDistribution;
import maths.Uniform;

public class Consultation extends HealthServices {
	

	public Consultation() {
		this.probabilityDistribution= new Uniform(5,20);
		this.duration=this.probabilityDistribution.getSample();
	}
	public Consultation(ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
		this.duration=this.probabilityDistribution.getSample();
	}
	
	public void result (Patient patient) {
		
		Uniform U = new Uniform(0,100);
		int x = U.getSample();
		if(0<=x && x<35) {
			patient.setState("released");
		}
		if(35<=x && x<55) {
			patient.setDestination(Database.radioRoom);
			patient.setState("waitingForTransport");
		}
		if(55<=x && x<95) {
			patient.setDestination(Database.bloodTestRoom);
			patient.setState("waitingForTransport");
		}
		if(95<=x && x<=100) {
			patient.setDestination(Database.mriRoom);
			patient.setState("waitingForTransport");
		}
	}
	
	
	
	

}
