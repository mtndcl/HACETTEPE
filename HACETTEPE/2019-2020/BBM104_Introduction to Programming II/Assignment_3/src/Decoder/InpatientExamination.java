package Decoder;

import java.util.ArrayList;

import Model.Admission;

public class InpatientExamination implements Examination{
	
	
	
	public InpatientExamination() {
		// TODO Auto-generated constructor stub
	
	}


	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return "Inpatient";
	}


	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Inpatient";
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
				   
				   admission.AddExamination(new Imaging(new Measurements(new Tests(new DoctorVisit(new InpatientExamination())))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements") &&
					   ops.contains("tests")
					  ) {
				   admission.AddExamination(new Imaging(new Measurements(new Tests(new InpatientExamination()))));
				   
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements") &&
					   ops.contains("doctorvisit")
					  ) {
				   admission.AddExamination(new Imaging(new Measurements(new DoctorVisit(new InpatientExamination()))));
				   
			   }else if(ops.contains("measurements") &&
					   ops.contains("tests") &&
					   ops.contains("doctorvisit")
					  ) {
				   admission.AddExamination(new Measurements(new Tests(new DoctorVisit(new InpatientExamination()))));
			   }else if(ops.contains("imaging") &&
					   ops.contains("tests") &&
					   ops.contains("doctorvisit")
					  ) {
				  admission.AddExamination(new Imaging(new Tests(new DoctorVisit(new InpatientExamination()))));
			   }else if(ops.contains("imaging") &&
					   ops.contains("measurements")) {
				   admission.AddExamination(new Imaging(new Measurements(new InpatientExamination())));
			   }else if(ops.contains("imaging") &&
					   ops.contains("tests") ) {
				   admission.AddExamination(new Imaging(new Tests(new InpatientExamination())));
			   }else if(ops.contains("imaging") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Imaging(new DoctorVisit(new InpatientExamination())));
			   }else if(ops.contains("measurements") &&
					   ops.contains("tests") ) {
				   admission.AddExamination(new Measurements(new Tests(new InpatientExamination())));
			   }else if(ops.contains("measurements") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Measurements(new DoctorVisit(new InpatientExamination())));
			   }else if(ops.contains("tests") &&
					   ops.contains("doctorvisit") ) {
				   admission.AddExamination(new Tests(new DoctorVisit(new InpatientExamination())));
			   }else if(ops.contains("tests")) {
				   admission.AddExamination(new Tests(new InpatientExamination()));
			   }else if(ops.contains("doctorvisit")) {
				   admission.AddExamination(new DoctorVisit(new InpatientExamination()));
			   }else if(ops.contains("measurements")) {
				   admission.AddExamination(new Measurements(new InpatientExamination()));
			   }else if(ops.contains("imaging")) {
				   admission.AddExamination(new Imaging(new InpatientExamination()));
			   }
		 } 
			   
		
	




}
