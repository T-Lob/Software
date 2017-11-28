package healthServices;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;

public class XRay extends HealthServices {
	private ProbabilityDistribution probabilityDistribution= new Uniform(10,20);
	private int duration=this.probabilityDistribution.getSample();
	
	public XRay(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(10,20);
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public XRay(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.cost=cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		if (this.timeOfAvailability <= this.ED.getTime()) {
			this.setTimeOfAvailability(this.ED.getTime());
			patient.setLocation(this.ED.radioRoom);
			this.ED.radioRoom.setState("full");
			this.WaitingQueue.remove(patient);
			patient.setState("checked");
			this.timeOfAvailability += this.duration;
		}
	}
	
	public void endCheck (Patient patient) {
		if (this.timeOfAvailability == this.ED.getTime()) {
			patient.setState("waitingForVerdict");
			this.ED.radioRoom.setState("empty");
			this.outcome = "Radiography done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
			patient.getPhysician().getMessageBox().add(this.outcome);
			patient.addToBill(cost);
			this.duration = this.probabilityDistribution.getSample();
		}
	}	
}

