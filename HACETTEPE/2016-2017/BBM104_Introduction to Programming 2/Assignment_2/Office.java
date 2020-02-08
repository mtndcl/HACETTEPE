/**
 * make release_data and cover Title 
 * @author mockingbird
 *
 */
public class Office extends Items {
	String release_data;
	String coverT;
	
	public Office(String t,int a,double b,String c,String d) {
		super(t,a,b);
		this.release_data=c;
		this.coverT=d;
	}
	/**
	 * 
	 * @return get realease date 
	 */
	public String getrelease_data(){
		
		return release_data;
	}
	/**
	 * 
	 * @return get covet title
	 */
	public String getcoverT(){
		
		return coverT;
	}

}
