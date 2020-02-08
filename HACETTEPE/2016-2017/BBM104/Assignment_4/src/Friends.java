
import java.util.ArrayList;
/**
 * 
 * make carts for who add some item in cart
 * @author mockingbird
 *
 */
public class Friends {
	String username;
	ArrayList<String> friends = new ArrayList<String>();

	public Friends(String a,String b) {

		
	this.username=a;
	this.friends.add(b);
	
	
	}
	
	public String getusername(){
		
		return username;
	}

	
	public ArrayList<String> getfriend(){
		
		return friends;
	}
	

	public void addtofriend(String a) {
		this.friends.add(a);
		
	}
}
