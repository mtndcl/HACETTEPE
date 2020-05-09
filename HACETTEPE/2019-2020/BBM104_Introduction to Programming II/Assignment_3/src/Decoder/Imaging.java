package Decoder;

public class Imaging extends ExaminationDecorator {

	private final Examination examination;
	public Imaging(Examination examination) {
		this.examination = examination;
		
	}
	@Override
    public String getDesc() {
        return examination.getDesc()+" Imaging";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+10;
    }
	
	


}
