package DAO;
import java.util.Comparator;

import Model.Patient;

public class SortPatient implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		int score1 =Integer.parseInt(o1.getId());
		int score2 =Integer.parseInt(o2.getId());
 
		if (score1 > score2) {
			return 1;
		} else if (score1 < score2) {
			return -1;
		} else {
			return 0;
		}
	}

}
