package core;

import java.util.ArrayList;

import Human.Patient;
import Human.Physician;
import others.Database;

public class Test {
	public static void main(String[] args) {
		Database.createED("ED");
		
		Physician physician = new Physician();
		System.out.println(Database.getPhysicianList());
		
		physician.setState("offduty");
		
		System.out.println(Database.getPhysicianList());
		
		
		
			
			
		
	
	}

}
