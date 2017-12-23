package others;

import java.lang.reflect.Array;
import java.util.ArrayList;

import maths.ProbabilityDistribution;
import maths.Uniform;

public class PatientGeneration {
	private String severityLevel;
	private ProbabilityDistribution probabilityDistribution;
	private ED ed;
	
	public PatientGeneration(String edName,ProbabilityDistribution probabilityDistribution, String severityLevel) {
		this.ed=Database.getEDbyName(edName);
		this.severityLevel=severityLevel;
		this.probabilityDistribution=probabilityDistribution;
		
	}
	
	public PatientGeneration(String edName, String severityLevel) {
		this.ed=Database.getEDbyName(edName);
		this.severityLevel=severityLevel;
		this.probabilityDistribution=new Uniform(0, 100);
		
	}
	
	public ProbabilityDistribution getProbabilityDistribution() {
		return probabilityDistribution;
	}
	public void setProbabilityDistribution(ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution = probabilityDistribution;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}
	
	public ED getEd() {
		return ed;
	}

	public String[] getGeneration(ED ed) {
		double d =probabilityDistribution.getSample();
		String [] array= {severityLevel , Double.toString(Database.getTime()+d), ed.getEDname(), Double.toString(d)};
		return array;
	}
	
	public void addGeneration(ED ed) {
		ed.getToBeGeneratedPatients().add(getGeneration(ed));
	}

	}


