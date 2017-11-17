package healthServices;

import Human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;

public class XRay extends HealthServices {
	private ProbabilityDistribution probabilityDistribution= new Uniform(10,20);
	private int duration=this.probabilityDistribution.getSample();
	
	public XRay() {
		
	}
	public XRay(ProbabilityDistribution probabilityDistribution, int cost) {
		this.probabilityDistribution= probabilityDistribution;
		this.cost=cost;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		if (this.timeOfAvailability <= Database.getTime()) {
			this.setTimeOfAvailability(Database.getTime());
			patient.setLocation(Database.radioRoom);
			Database.radioRoom.setState("full");
			this.WaitingQueue.remove(patient);
			patient.setState("checked");
			this.timeOfAvailability += this.duration;
		}
	}
	
		public void endCheck (Patient patient) {
		if (this.timeOfAvailability == Database.getTime()) {
			patient.setState("waitingForVerdict");
			Database.radioRoom.setState("empty");
			this.outcome = "Radiography done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
			patient.getPhysician().addToMessageBox(this.outcome);
			patient.addToBill(cost);
		}
	
		
		
		
		
		
		
		
		}
	
	}

