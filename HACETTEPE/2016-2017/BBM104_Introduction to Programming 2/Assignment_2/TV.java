/**
 * make screen for tv
 * @author mockingbird
 *
 */
public class TV extends Electronic {
	double screen;
	
	public TV(String t,int a,double b,String c,String d,double e,double f,double h) {
		super(t,a,b,c,d,e,f);
		this.screen=h;
		
		
	}
	/**
	 * 
	 * @return	get screen
	 */
	public double getscreen(){
		return screen;
	}
	/**
	 * show tv info
	 */
	void ShowInfo_TV(){
		

		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp()+" $");
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice());
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("Max Volt: "+(int)getV()+" V.");
		System.out.println("Max Watt: "+(int)getW()+" W.");
		System.out.println("Screen size: "+(int)getscreen()+'"');
		
		
	}
}
