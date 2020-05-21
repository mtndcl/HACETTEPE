package Decoder;

public class Imaging extends ExaminationDecorator {

	///Imaging Class 
	public Imaging(Examination examination) {
		super(examination);
		
	}
	@Override
    public String getDesc() {
        return examination.getDesc()+" imaging";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+10;
    }
	
	


}
