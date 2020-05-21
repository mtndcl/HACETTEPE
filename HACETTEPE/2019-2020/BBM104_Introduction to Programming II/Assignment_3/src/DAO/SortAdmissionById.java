package DAO;

import java.util.Comparator;
import Model.Admission;

public class SortAdmissionById implements  Comparator<Admission>{

	@Override
	public int compare(Admission o1, Admission o2) {
		
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
