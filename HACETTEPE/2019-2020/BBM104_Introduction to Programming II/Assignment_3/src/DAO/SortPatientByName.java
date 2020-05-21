package DAO;
import java.util.Comparator;

import Model.Patient;

public class SortPatientByName implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		
		return o1.getName().compareTo(o2.getName());
	}

}
