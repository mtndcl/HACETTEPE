package Decoder;

import Model.Admission;

public interface Examination {
	
	
	///Get what structure have as text
	 public String getDesc();
	 ///Get price of Admission
	 public double getPrice();
	 ////Create Admission with examinations
	 public void  addoperation(Admission admission,String[]  operation);

	
	
}
