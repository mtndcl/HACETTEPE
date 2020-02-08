/**
 * 
 * make box color for desktops
 * @author mockingbird
 *
 */
public class Desktop extends Computer {
	String boxcolor;
	public Desktop(String t,int a,double b,String c,String d,double e,double f,String h,String j,double k,double l,String m) {
		super(t,a,b,c,d,e,f,h,j,k,l);
		this.boxcolor=m;
		
		
	}
	/**
	 * @return get box color  for customers
	 */
	public String getboxcolor(){
		
		return boxcolor;
		
	}
	/**
	 * @param wrote desktop info 
	 */
	public void ShowInfo_Desktop(){
		
		
		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("Max Volt: "+(int)getV()+" V.");
		System.out.println("Max Watt: "+(int)getW()+" W.");
		System.out.println("Operating System: "+getopSys());
		System.out.println("CPU Type: "+getCPU());
		System.out.println("RAM Capacity:"+(int)getRAM()+" GB.");
		System.out.println("HDD Capacity: "+(int)getHDD()+" GB.");
		System.out.println("Box Color: "+getboxcolor());
	}

}
