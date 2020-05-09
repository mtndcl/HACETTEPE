package Decoder;

public class Measurements extends ExaminationDecorator {

	private final Examination examination;
	public Measurements(Examination examination) {
		this.examination = examination;
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" Measurements";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+5;
    }
	

}
