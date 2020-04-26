package Models;



public abstract class Academician extends Personnel  {

	protected float basesalary=2600;
	public Academician(String name,String id, int year,String position) {
		super(name, id, year,position);
		
	}
	

}
