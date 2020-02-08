import java.util.ArrayList;

/**
 * 
 * @author mockingbird
 *
 */


public class User {
	
	static ArrayList<Post> textposts = new ArrayList<Post>();
	static ArrayList<Post> videoposts = new ArrayList<Post>();
	static ArrayList<Post> allposts = new ArrayList<Post>();
	static ArrayList<Post> imageposts = new ArrayList<Post>();

	String really_name;
	String user_name;
	String password;
	String  birth_date;
	String school;
	int ID;
	
	public User(int ID,String really_name,String user_name,String password,String birth_date,String school ) {
		this.really_name = really_name;
		this.user_name = user_name;
		this.password = password;
		this.school = school;
		this.birth_date=birth_date;
		this.ID=ID;
	}
	public String getReally_name() {
		
		return really_name;
	}
	public String setReally_name(String name) {
		this.really_name = name;

		return really_name;
	}
	
	public String getUser_name() {
		
		return user_name;
	}
	
	public String getPassword() {
		
		return password;
		
	}
		public String setPassword(String passwod) {
			this.password=passwod;
		return password;
		
	}
	
	public String getbirth_date() {
		return birth_date;
	}
	public String setbirth_date(String date) {
		this.birth_date = date;

		return birth_date;
	}
	
	public String getSchool() {
		
		return school;
	}
	public String setSchool(String school) {
		this.school = school;

		return school;
	}
	public int getID() {
		
		return ID;
	}
	

}
