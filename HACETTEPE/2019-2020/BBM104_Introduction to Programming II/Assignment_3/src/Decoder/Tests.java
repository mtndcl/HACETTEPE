package Decoder;

public class Tests extends ExaminationDecorator {

	private final Examination examination;
	public Tests(Examination examination) {
		this.examination = examination;
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" Tests";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+7;
    }


}
