package healthServices;
import java.util.ArrayList;

import Human.Patient;
import Resources.Resource;
import maths.ProbabilityDistribution;

public abstract class HealthServices extends Resource {
	protected ArrayList<Patient> WaitingQueue = new ArrayList<Patient>();
	protected ProbabilityDistribution probabilityDistribution;
	protected int cost;
	protected String outcome;
	protected int duration;

}
