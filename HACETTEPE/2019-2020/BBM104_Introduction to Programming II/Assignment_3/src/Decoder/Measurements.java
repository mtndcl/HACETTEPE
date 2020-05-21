package Decoder;

public class Measurements extends ExaminationDecorator {

	
	public Measurements(Examination examination) {
		super(examination);
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" measurements";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+5;
    }
	

}
