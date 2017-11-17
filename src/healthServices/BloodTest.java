package healthServices;

import Human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.ED;

public class BloodTest extends HealthServices {
	private ProbabilityDistribution probabilityDistribution= new Uniform(15,90);
	private int duration=this.probabilityDistribution.getSample();
	
	public BloodTest(ED ed) {
		this.ED=ed;
		
	}
	public BloodTest(ED ed,ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
		this.ED=ed;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		if (this.timeOfAvailability <= this.ED.getTime()) {
			this.setTimeOfAvailability(this.ED.getTime());
			patient.setLocation(this.ED.bloodTestRoom);
			this.ED.bloodTestRoom.setState("full");
			this.WaitingQueue.remove(patient);
			patient.setState("checked");
			this.timeOfAvailability += this.duration;
		}
	}
	
		public void endCheck (Patient patient) {
		if (this.timeOfAvailability == this.ED.getTime()) {
			patient.setState("waitingForVerdict");
			this.ED.bloodTestRoom.setState("empty");
			this.outcome = "Bloodtest done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
			patient.getPhysician().addToMessageBox(this.outcome);
			patient.addToBill(cost);
		}
	
		
		
		
		
		
		
		
		}

}
