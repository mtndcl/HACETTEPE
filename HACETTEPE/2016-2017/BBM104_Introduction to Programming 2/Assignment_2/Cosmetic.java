/**
 * make expirationdate,weight,organic,manufacturer and brand for cosmetics
 * @author mockingbird
 *
 */
public class Cosmetic extends Items {
	String manufacturer;
	String brand;
	String expiration_date;
	double weight;
	int organic;
	
	public Cosmetic(String t,int a,double b,String c,String d,int e,String  f,double h) {
		super(t,a,b);
		this.expiration_date=f;
		this.weight=h;
		this.organic=e;
		this.manufacturer=c;
		this.brand=d;
		
	}
	/**
	 * @return get brand  for Cosmetic
	 */
	public String getbrand(){
		return brand;
	}
	/**
	 * @return get manufacturer  for Cosmetic
	 */
	public String getmanufacturer(){
		return manufacturer;
	}
	/**
	 * @return get expiration date  for Cosmetic
	 */
	public String getexpiration_date(){
		return expiration_date;
	}
	/**
	 * @return get weight  for Cosmetic
	 */
	public double getweight(){
		return weight;
	}
	/**
	 * @return get organic  for Cosmetic
	 */
	public int getorganic(){
		return organic;
	}

}
