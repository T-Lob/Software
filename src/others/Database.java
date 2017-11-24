package others;

import java.util.ArrayList;


public class Database {
	private static  ArrayList<ED> generatedEDs = new ArrayList<ED>();

	public static ArrayList<ED> getGeneratedEDs() {
		return generatedEDs;
	}
	
	public static void addToGeneratedEDs(ED ed) {
		Database.generatedEDs.add(ed);
	}

	public static ED getEDbyName(String edName) {
		for (ED ed:generatedEDs) {
			if (ed.getEDname().equalsIgnoreCase(edName)) {
				return ed;
			}
		}
		return null;
	}
}