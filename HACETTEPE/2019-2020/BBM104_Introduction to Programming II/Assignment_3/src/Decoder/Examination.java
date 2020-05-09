package Decoder;

import java.io.FileWriter;

import Model.Admission;

public interface Examination {
	
	 public String getDesc();
	 public double getPrice();
	
	 void printoperations(Admission admission, FileWriter fileWriter);

	
	
}
