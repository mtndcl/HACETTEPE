import java.util.ArrayList;
import java.util.UUID;
/**
 * 
 * @author mockingbird
 *
 */

public  class VideoPost extends TextPost implements PostInterface{
	private double duration;
	private String filename;
	public VideoPost(String username,UUID idofpost, String text, double latitude,
			double longitude,ArrayList<String> taggedfriend,String filename,double duration) {
		super(username,idofpost, text, latitude, longitude,taggedfriend);
		
		this.duration=duration;
		this.filename=filename;
		
	 
	}
	public double getduration(){
		return duration;
		
	}
	
	public String getfilename(){
		return filename;
		
	}
}
