/**
 * make last duration for perfume
 * @author mockingbird
 *
 */
public class Perfume extends Cosmetic {
	String lasting_duration;
	
	public Perfume(String t,int a,double b,String c,String d,int e,String  f,double h,String g) {
		super(t,a,b,c,d,e,f,h);
		this.lasting_duration=g;
	}
	public String getlasting_duration(){
		return lasting_duration;
	}
	/**
	 * show perfume info 
	 */
	void ShowInfo_Perfume(){
		String	b = getorganic()==1 ? "Yes" : "No";
		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("Organic: "+b);
		System.out.println("Expiration Date: "+getexpiration_date());
		System.out.println("Weight: "+(int)getweight()+" g.");
		System.out.println("Lasting Duration: "+getlasting_duration()+" min.");
		
	}
}
