import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @author mockingbird
 *
 */
public abstract class  Post implements PostInterface {
	ArrayList<String> taggedfriend = new ArrayList<String>();
	private Location loca;
	public ImagePost image;
	
	private String text;
	private UUID idofpost;
	private Date postdate;
	private double latitude;
	private double longitude;
	private String username;
	public Post(String username,UUID idofpost,String text,double latitude ,double longitude,ArrayList<String> taggedfriend){
		super();
		this.idofpost=idofpost;
		this.latitude=latitude;
		this.longitude=longitude;
		this.text=text;
		this.username=username;
		this.taggedfriend=taggedfriend;
	}
	public double getduration() {
		return getduration(); 
		}
	public String getfilename() {
		return getfilename(); 
		}
	public String getresolutionx() {
		return getresolutionx(); 
		}
	public String getresolutiony() {
		return getresolutiony(); 
		}
	public double getLatitude() {
		return latitude;
	}
	
	public String getusername() {
		return username;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public ArrayList<String> gettaggedfriend() {
		return taggedfriend;
	}
	public UUID getIdofpost() {
		return idofpost;
	}
	public String getText() {
		return text;
	}
	
	public Date getPostdate() {
		return postdate;
	}
	public void ShowText(){
		System.out.println("amk");
		System.out.println("Date: "+getPostdate());
		System.out.println("Location: "+getLatitude()+" "+getLongitude());
	}

}
