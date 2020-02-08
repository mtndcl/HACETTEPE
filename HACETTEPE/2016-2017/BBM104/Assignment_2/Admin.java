/**
 * 
 * @param make admin's password 
 * @author mockingbird
 *
 */
public class Admin extends Employee {
	
	String password;
	

	public Admin(String a,String b,String c,double d,String e) {
		super(a,b,c,d);
		this.password=e;
		
		
		
	}
	/**
	 *
	 * @return get admin's password 
	 */
	public String getPassword(){
		
		
		return password;
		
	}
	/**
	 *
	 * @param wrote admin's info 
	 */
	void adminDisplay(){
		System.out.println("\n"+"----------- Admin info -----------");
		System.out.println("Admin name: "+getname());
		System.out.println("Admin e-mail:"+getemail());

		System.out.println("Admin date of birth: "+getbirth_day()+"\n");

	}

}
