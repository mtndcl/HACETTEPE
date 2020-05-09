package Decoder;

import java.io.FileWriter;
import java.io.IOException;

import Model.Admission;

public class OutpatientExamination implements Examination{
	public OutpatientExamination() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return "Outpatient";
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Outpatient";
	}
	private void print(String string,FileWriter fileWriter) {
		try {
			fileWriter.write(string+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void printoperations(Admission admission, FileWriter fileWriter) {
		print("TotalCost for admission "+ admission.getId(),fileWriter);
		double total=0;
		for (Examination examination : admission.getExaminations()) {
			total+=examination.getPrice();
			print("\t"+ examination.getDesc()+ " " + examination.getPrice() +"$",fileWriter);
		}
		print("\tTotal: "+total +"$",fileWriter);
		
		
	}
	




}
