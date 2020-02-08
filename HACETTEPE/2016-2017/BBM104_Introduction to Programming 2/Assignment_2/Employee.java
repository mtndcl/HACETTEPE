/**
 * 
 * make salary for Employees
 * @author mockingbird
 *
 */
public class Employee extends Person {
	
	private double salary;
	
	public Employee(String a,String b,String c,double d) {
		super(a,b,c);
		this.salary=d;
		
		
		
		
	}
	
	/**
	 * 
	 * @return	salary for Employees
	 */
	
	public double getSalary(){
		
		
		return salary;
		
		
		
	}

}
