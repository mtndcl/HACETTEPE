package Decoder;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

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
	@Override
	public void addoperation(Admission admission, String[] line) {
		
			ArrayList<String>  ops=new ArrayList<String>();
			for (String xx : line) {
				ops.add(xx);
			}
		
			   
			   if(	ops.contains("imaging") && 
					ops.contains("measurements")  &&
					ops.contains("tests") &&
					ops.contains("doctorvisit") ) {
				   
				   admission.AddExamination(new Imaging(new Measurements(new Tests(new DoctorVisit(new OutpatientExamination())))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements") &&
					   ops.contains("tests")
					  ) {
				   admission.AddExamination(new Imaging(new Measurements(new Tests(new OutpatientExamination()))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements") &&
					   ops.contains("doctorvisit")
					  ) {
				   admission.AddExamination(new Imaging(new Measurements(new DoctorVisit(new OutpatientExamination()))));
				   
			   }else if(ops.contains("measurements") &&
					   ops.contains("tests") &&
					   ops.contains("doctorvisit")
					  ) {
				   admission.AddExamination(new Measurements(new Tests(new DoctorVisit(new OutpatientExamination()))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("tests") &&
					   ops.contains("doctorvisit")
					  ) {
				  admission.AddExamination(new Imaging(new Tests(new DoctorVisit(new OutpatientExamination()))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements")) {
				   admission.AddExamination(new Imaging(new Measurements(new OutpatientExamination())));
			   }else if(ops.contains("imaging") &&
					   ops.contains("tests") ) {
				   admission.AddExamination(new Imaging(new Tests(new OutpatientExamination())));
			   }else if(ops.contains("imaging") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Imaging(new DoctorVisit(new OutpatientExamination())));
			   }else if(ops.contains("measurements") &&
					   ops.contains("tests") ) {
				   admission.AddExamination(new Measurements(new Tests(new OutpatientExamination())));
			   }else if(ops.contains("measurements") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Measurements(new DoctorVisit(new OutpatientExamination())));
			   }else if(ops.contains("tests") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Tests(new DoctorVisit(new OutpatientExamination())));
			   }else if(ops.contains("tests")) {
				   admission.AddExamination(new Tests(new OutpatientExamination()));
			   }else if(ops.contains("doctorvisit")) {
				   admission.AddExamination(new DoctorVisit(new OutpatientExamination()));
			   }else if(ops.contains("measurements")) {
				   admission.AddExamination(new Measurements(new OutpatientExamination()));
			   }else if(ops.contains("imaging")) {
				   admission.AddExamination(new Imaging(new OutpatientExamination()));
			   }
		 
			   
		
	}
	
	
}
