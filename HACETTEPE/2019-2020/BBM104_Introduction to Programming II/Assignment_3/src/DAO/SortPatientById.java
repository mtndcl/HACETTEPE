package DAO;

import java.util.Comparator;

import Model.Patient;

public class SortPatientById implements   Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		
		int one =Integer.parseInt(o1.getId());
		int two =Integer.parseInt(o2.getId());
		if(one>two) {
			return 1;
			
		}else if (two>one) {
			return -1;
		}
		return 0;
	}

}
