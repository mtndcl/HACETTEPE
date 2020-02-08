import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;
/**
 * 
 * @author mockingbird
 *
 */

public  class TextPost extends Post implements PostInterface{
	
	public TextPost(String username,UUID idofpost, String text, double latitude,double longitude,ArrayList<String> taggedfriend) {
		super(username,idofpost, text, latitude, longitude,taggedfriend);
		
		
		
	}
	
	@Override
	public void setText(String text) {
	
		
	}
	@Override
	public UUID getID() {
		
		return null;
	}
	@Override
	public Date getDate(Date text) {
		
		return null;
	}

	
	
	
}
