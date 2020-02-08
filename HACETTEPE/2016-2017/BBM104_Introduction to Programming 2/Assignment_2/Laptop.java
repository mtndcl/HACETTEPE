/**
 * make hdmi for laptop
 * @author mockingbird
 *
 */
public class Laptop extends Computer {
	int HDMI;
	
	public Laptop(String t,int a,double b,String c,String d,double e,double f,String h,String j,double k,double l,int m) {
		super(t,a,b,c,d,e,f,h,j,k,l);
		this.HDMI=m;
		
	
	}
	/**
	 * 
	 * @return HDMI for laptop
	 */
	public int getHDMI(){
		
	
		return HDMI;
		
	}
	
	/**
	 * show laptop info 
	 */
	void ShowInfo_Laptop(){
		
		String	b = getHDMI()==1 ? "Yes" : "No";
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
		System.out.println("HDMI support: "+b);
		
	}
}
