/**
 * 
 * make composer and songs for CVDV
 * @author mockingbird
 *
 */
public class DVCV extends Office {
	String composer;
	String song;
	
	public DVCV(String t,int a,double b,String c,String d,String e,String f) {
		super(t,a,b,c,d);
		this.composer=e;
		this.song=f;
	}
	public String getcomposer(){
		return composer;
	}
	public String getsongs(){
		return song;
	}
	
	/**
	 * @param show CDDV info
	 */
	void ShowInfo_DVCV(){
		

		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Release Date: "+getrelease_data());
		System.out.println("Title: "+getcoverT());
		System.out.println("songs: "+getsongs());
		System.out.println("composer: "+getcomposer());
		
		
		
		
	}
}
