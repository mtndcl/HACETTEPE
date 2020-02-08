/**
 * make medicated for hair care
 * @author mockingbird
 *
 */
public class Hair_care extends Cosmetic {
	protected int medicated;
	public Hair_care(String t,int a,double b,String c,String d,int e,String  f,double h,int j) {
		super(t,a,b,c,d,e,f,h);
		this.medicated=j;
		
		
	}
	/**
	 * 
	 * @return get medicated for hair care
	 */
	public int getmedicated(){
		return medicated;
	}
	/**
	 * show hair cares info
	 */
	void ShowInfo_Hair_care(){
		
		String	b = getorganic()==1 ? "Yes" : "No";
		String a = getmedicated()==1 ? "Yes" : "No";
		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("organic : "+ b);
		System.out.println("Expiration Date: "+getexpiration_date());
		System.out.println("Weight: "+(int)getweight()+" g.");
		System.out.println("Medicated: "+a);
		
	}
}
