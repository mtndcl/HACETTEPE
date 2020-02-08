/**
 * make name email and birth date for person
 * @author mockingbird
 *
 */
public class Person {
	private String name;
	private String email;
	private String birth_day;
		
	public Person(String a,String b,String c){
		super();
		this.name=a;
		this.email=b;
		this.birth_day=c;
		
		
	}	
/**
 * 
 * 
 * @return get names
 */
	public String getname(){
		
		
		return name;
		
		
		
	}
	/**
	 * 
	 * @return  get email
	 */
	public String getemail(){
		
		
		return email;
		
		
		
	}
	/**
	 * 
	 * @return	getbirth day
	 */
	public String getbirth_day(){
		
		
		return birth_day;
		
		
		
	}
	
	
}

