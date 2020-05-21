package Decoder;

public class DoctorVisit extends ExaminationDecorator {
	
	
	///DoctorVisit Class 
	public DoctorVisit(Examination examination) {
		super(examination);
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" doctorvisit";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+15;
    }




}
