package Decoder;

public class Tests extends ExaminationDecorator {


	public Tests(Examination examination) {
		super(examination);
		
	}
	
	@Override
    public String getDesc() {
        return examination.getDesc()+" tests";
    }
 
 
    @Override
    public double getPrice() {
        return examination.getPrice()+7;
    }


}
