package Decoder;

import java.io.FileWriter;
import java.io.IOException;

import Model.Admission;

public abstract class ExaminationDecorator  implements  Examination {
	
	

	@Override
    public String getDesc() {
        return "Decorator";
    }

	@Override
	public void printoperations(Admission admission,FileWriter fileWriter) {
		print("TotalCost for admission "+ admission.getId(),fileWriter);
		double total=0;
		for (Examination examination : admission.getExaminations()) {
			total+=examination.getPrice();
			print("\t"+ examination.getDesc()+ " " + examination.getPrice() +"$",fileWriter);
		}
		print("\tTotal: "+total +"$",fileWriter);
		
	}
	private void print(String string,FileWriter fileWriter) {
		try {
			fileWriter.write(string+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
