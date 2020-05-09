package Decoder;

public class DoctorVisit extends ExaminationDecorator {
	
	
	private final Examination examination;
	public DoctorVisit(Examination examination) {
		this.examination = examination;
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" DoctorVisit";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+15;
    }



}
