import java.util.ArrayList;


public class ImagePost extends TextPost {
	String filename;
	String resolution;
	
	
	public ImagePost(int a,String username, String text, double longitude,
			double latitude, ArrayList<User> taggedfriend,String filename,String resolution) {
		super(a,username, text, longitude, latitude, taggedfriend);
		this.filename=filename;
		this.resolution=resolution;
	}
	public String getFilename() {
		return filename;
	}
	
	public String getResolution() {
		return resolution;
	}

}
