import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @author mockingbird
 *
 */
public  class ImagePost extends TextPost  implements PostInterface{

	private String filename;
	private String  resolutionx;
	private String  resolutiony;
	public ImagePost(String username,UUID idofpost, String text, double latitude,
			double longitude,ArrayList<String> taggedfriend,String filename,String resolutionx,String resolutiony) {
		super(username,idofpost, text, latitude, longitude,taggedfriend);
		
		this.resolutionx=resolutionx;
		this.resolutiony=resolutiony;
		this.filename=filename;
	}
	
	public String getresolutionx(){
		return resolutionx;
		
	}
	public String getresolutiony(){
		return resolutiony;
		
	}
	public String getfilename(){
		return filename;
		
	}
	void Showimage(String username ){
		System.out.println(getresolutiony());
		System.out.println("burada");
		
	}
}
