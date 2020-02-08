
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * 
 * @author mockingbird
 * @version 1.0.0
 */


public class User {
	

	String namesurname;
	String user_name;
	String password;
	String  birth_date;
	String school;
	String  relationshipstatus;
	
	public User(String takenamesurname,String takeusername,String takepassword,String takebirthdate,String takeschool,String  takerelationship) {
		this.namesurname = takenamesurname;
		this.user_name = takeusername;
		this.password = takepassword;
		this.school = takeschool;
		this.birth_date=takebirthdate;
		this.relationshipstatus=takerelationship;
		
		
	}
	
	
	public String getnamesurname() {
		
		return namesurname;
	}
	public String setnamesurname(String name) {
		this.namesurname = name;

		return name;
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
	public String getrelationshipstatus() {
		
		return relationshipstatus;
	}
	public String setrelationshipstatus(String statu) {
		this.relationshipstatus = statu;
		return relationshipstatus;
	}


	


	
	

}
