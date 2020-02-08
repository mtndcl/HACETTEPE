/**
 * make baby sensetive for skin care
 * @author mockingbird
 *
 */
public class Skin_care extends Cosmetic {
	int baby;
	public Skin_care(String t,int a,double b,String c,String d,int e,String  f,double h,int g) {
		super(t,a,b,c,d,e,f,h);
		this.baby=g;
	}
	/**
	 * 
	 * @return get baby sensitive
	 */
	public int getbaby(){
		
		return baby;
	}
	
	/**
	 * show skin care info
	 */
	void ShowInfo_Skin_care(){
		String	b = getorganic()==1 ? "Yes" : "No";
		String a = getbaby()==1 ? "Yes" : "No";
		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Manufacturer: "+getmanufacturer());
		System.out.println("Brand: "+getbrand());
		System.out.println("Organic: "+b);
		System.out.println("Expiration Date: "+getexpiration_date());
		System.out.println("Weight: "+(int)getweight()+" g.");
		System.out.println("Baby Sensetive : "+a);
		
	}
}
