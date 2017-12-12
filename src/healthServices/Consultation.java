package healthServices;

import java.util.Random;
import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;

/**
 * Consultation is part of healthServices
 * its duration is random, by default it is defined by Uniform(5,20)
 */
public class Consultation extends HealthServices {
	

	/**
	 * This constructs a Consultation
	 * <br> its default probabilityDistribution is an uniform law on [5;20].
	 * @param EDname The ED in which the Consultation will be created.
	 */
	public Consultation(String EDname) {
		this.probabilityDistribution= new Uniform(5,20);
		this.duration=this.probabilityDistribution.getSample();
		this.ED=Database.getEDbyName(EDname);
	}
	
	/**
	 * This constructs a Consultation
	 * @param EDname The ED in which the Consultation will be created.
	 * @param probabilityDistribution The probabilityDistribution of the Consultation
	 */
	public Consultation(String EDname,ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
		this.duration=this.probabilityDistribution.getSample();
		this.ED=Database.getEDbyName(EDname);
	}
	
	/**
	 * The result of the consultation indicates if the patient is free to go or if a test is required.
	 * If a test is required, the patient state is set to "waitingForTransport".
	 * @param patient the patient undergoing this consultation.
	 */
	public void result (Patient patient) {
		patient.setLocation(this.ED.waitingRoom);
		double x = 100*(new Random().nextDouble());
		if(0<=x & x<35) {
			patient.getPhysician().verdict(patient);
		}
		else if(35<=x & x<55) {
			
			patient.setDestination(this.ED.radioRoom);
			patient.setState("waitingForTransport");
		}
		else if(55<=x & x<95) {
			patient.setDestination(this.ED.bloodTestRoom);
			patient.setState("waitingForTransport");
		}
		else if(95<=x & x<=100) {
			patient.setDestination(this.ED.mriRoom);
			patient.setState("waitingForTransport");
		}
	}
}
