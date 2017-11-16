import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		for (int i=0;i<5;i++) {
			ArrayList<Patient> L = new ArrayList<Patient>();
			Database.addToRegisteredPatients(L); }
		System.out.println(Database.getRegisteredPatients());
			
			
		
		
		
	
	}

}
