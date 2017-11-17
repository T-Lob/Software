package healthServices;
import Human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.ED;


public class Consultation extends HealthServices {
	

	public Consultation(ED ed) {
		this.probabilityDistribution= new Uniform(5,20);
		this.duration=this.probabilityDistribution.getSample();
		this.ED=ed;
	}
	public Consultation(ED ed,ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
		this.duration=this.probabilityDistribution.getSample();
		this.ED=ed;
	}
	
	public void result (Patient patient) {
		patient.getLocation().setState("empty");
		Uniform U = new Uniform(0,100);
		int x = U.getSample();
		if(0<=x && x<35) {
			patient.getPhysician().verdict(patient);
		}
		if(35<=x && x<55) {
			patient.setDestination(this.ED.radioRoom);
			patient.setState("waitingForTransport");
		}
		if(55<=x && x<95) {
			patient.setDestination(this.ED.bloodTestRoom);
			patient.setState("waitingForTransport");
		}
		if(95<=x && x<=100) {
			patient.setDestination(this.ED.mriRoom);
			patient.setState("waitingForTransport");
		}
	}
	
	
	
	

}
