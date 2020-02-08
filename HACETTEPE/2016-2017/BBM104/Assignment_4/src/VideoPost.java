import java.util.ArrayList;


public class VideoPost extends TextPost {
	String filename;
	double duration;
	public VideoPost(int a,String username, String text, double longitude,
			double latitude, ArrayList<User> taggedfriend,String filename,double duration) {
		super(a,username, text, longitude, latitude, taggedfriend);
		this.filename=filename;
		this.duration=duration;
	}
	public String getFilename() {
		return filename;
	}
	public double getDuration() {
		return duration;
	}

}
