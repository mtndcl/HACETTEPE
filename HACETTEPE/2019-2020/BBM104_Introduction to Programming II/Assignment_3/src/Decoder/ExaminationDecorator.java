package Decoder;

import Model.Admission;

public abstract class ExaminationDecorator  implements  Examination {
	
	
	
	protected Examination  examination;
	
	public ExaminationDecorator(Examination examination){
		this.examination=examination;
	}

	@Override
    public String getDesc() {
        return "Decorator";
    }
	

	@Override
	public void addoperation(Admission admission,String[] line) {
		
		
		
		 for(int i=0;i<line.length;i++) {
			   
			   if(	line[i].contains("imaging") && 
					line[i].contains("measurements")  &&
					line[i].contains("tests") &&
					line[i].contains("doctorvisit") ) {
				   
				   	examination=new Imaging(new Measurements(examination));
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("measurements") &&
					   line[i].contains("tests")
					  ) {
				   examination=new Imaging(new Measurements(new OutpatientExamination()));
				   
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("measurements") &&
					   line[i].contains("doctorvisit")
					  ) {
				   examination=new Imaging(new Measurements(new OutpatientExamination()));
				   
			   }else if(line[i].contains("measurements") &&
					   line[i].contains("tests") &&
					   line[i].contains("doctorvisit")
					  ) {
				   examination=new Imaging(new Measurements(new OutpatientExamination()));
				   
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("tests") &&
					   line[i].contains("doctorvisit")
					  ) {
				   examination=new Imaging(new Measurements(new OutpatientExamination()));
				   
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("measurements")) {
				   
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("tests") ) {
				   
			   }else if(line[i].contains("imaging") &&
					   line[i].contains("doctorvisit") ) {
				   
			   }else if(line[i].contains("measurements") &&
					   line[i].contains("tests") ) {
				   
			   }else if(line[i].contains("measurements") &&
					   line[i].contains("doctorvisit") ) {
				   
			   }else if(line[i].contains("tests") &&
					   line[i].contains("doctorvisit") ) {
				   
			   }else if(line[i].contains("tests")) {
				   
			   }else if(line[i].contains("doctorvisit")) {
				   
			   }else if(line[i].contains("measurements")) {
				   
			   }else if(line[i].contains("imaging")) {
				   
			   }
			   
			   admission.AddExamination(examination);
			   
		   }
	}


}
