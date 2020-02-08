import java.sql.Date;
import java.util.UUID;
/**
 * 
 * @author mockingbird
 *
 */

public interface PostInterface {
		
	
	public void setText(String text);
	public String  getText();
	public UUID getID();
	public Date getDate(Date text);
	
	
}
