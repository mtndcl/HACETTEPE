import java.util.ArrayList;

/*
 * 
 */
public class TextPost {
	String username;
	String text;
	double longitude;
	double latitude;
	int a;
	ArrayList<User> etiketlenenler = new ArrayList<User>();
	public TextPost(int a,String username ,String text,double longitude,double latitude,ArrayList<User> etiketlenenler){
		super();
		this.username=username;
		this.text=text;
		this.longitude=longitude;
		this.latitude=latitude;
		this.etiketlenenler=etiketlenenler;
		this.a=a;
		
	}
	
	public String getUsername() {
		return username;
	}
	public int getid() {
		return a;
	}
	
	public String getText() {
		return text;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public ArrayList<User> getTaggedfriend() {
		return etiketlenenler;
	}
	
}
