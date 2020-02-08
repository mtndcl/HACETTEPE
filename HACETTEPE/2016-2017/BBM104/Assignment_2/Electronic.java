/**
 * make manufacturer,brand maxVolt and MaxWatt for Electronic
 * @author mockingbird
 *
 */
public class Electronic extends Items {
	String manufacturer;
	String brand;
	double maxW;
	double maxV;
	public Electronic(String t,int a,double b,String c,String d,double e,double f) {
		
		
		super(t,a,b);
		this.manufacturer=c;
		this.brand=d;
		this.maxV=e;
		this.maxW=f;
	}
	/**
	 * 
	 * @return get maxV for Electronic
	 */
	public double getW(){
		
		return maxW;
	}
	/**
	 * 
	 * @return get maxV for Electronic
	 */
	public double getV(){
		
		return maxV;
	}
	/**
	 * 
	 * @return get manufacturer for Electronic
	 */
	public String getmanufacturer(){
		
		return manufacturer;
	}
	/**
	 * 
	 * @return get brand for Electronic
	 */
	public String getbrand(){
	
	return brand;
	}
}
