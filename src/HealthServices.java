import java.util.ArrayList;

import maths.ProbabilityDistribution;

public class HealthServices extends Resource {
	protected ArrayList<Patient> WaitingQueue = new ArrayList<Patient>();
	protected ProbabilityDistribution probabilityDistribution;
	protected int cost;
	protected String outcome;

}
