/**
 * make screen type for smartphone
 * @author mockingbird
 *
 */
public class Smartphone extends Electronic {
	String screen_type;
	public Smartphone(String t,int a,double b,String c,String d,double e,double f,String h) {
		super(t,a,b,c,d,e,f);
		this.screen_type=h;
		
	}
	/**
	 * 
	 * @return	get screen type
	 */
	public String getscreen_type(){
		return screen_type;
	}
	/**
	 * show smartphone info
	 */
	void ShowInfo_Smartphone(){
		
		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("Max Volt: "+(int)getV()+" V.");
		System.out.println("Max Watt: "+(int)getW()+" W.");
		System.out.println("Screen Type: "+getscreen_type());
	}
}
