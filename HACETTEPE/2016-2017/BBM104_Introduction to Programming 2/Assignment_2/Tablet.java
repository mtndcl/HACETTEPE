/**
 * make dimensions for tablet
 * @author mockingbird
 *
 */
public class Tablet extends Computer{
	double dimensions;
	public Tablet(String t,int a,double b,String c,String d,double e,double f,String h,String j,double k,double l,double  m) {
		super(t,a,b,c,d,e,f,h,j,k,l);
		this.dimensions=m;
		
	
	}
	/**
	 * 
	 * @return get dimensions
	 */
	public double getdimensions(){
		
		return dimensions;
	}
	/**
	 * show tablet info
	 */
	void ShowInfo_Tablet(){
		

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
		System.out.println("RAM Capacity: "+(int)getRAM()+" GB.");
		System.out.println("HDD Capacity: "+(int)getHDD()+" GB.");
		System.out.println("Dimension: "+(int)getdimensions()+" in.");
	}
}
