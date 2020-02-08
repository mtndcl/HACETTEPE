/**
 * make id of items ,price and typ for Items
 * @author mockingbird
 *
 */
public class Items {
	int ID;
	double price;
	String typ;
	public Items(String t,int a,double b) {
		super();
		this.ID=a;
		this.price=b;
		this.typ=t;
		
		
	}

	
	/**
	 * 
	 * @return get id of Items
	 */
	public int getID(){
		
		return ID;
		
	}
	/**
	 * 
	 * @return get price of Items
	 */

	public double getprice(){
		
		return  price;
		
	}
	/**
	 * 
	 * @return get typ of Items
	 */


	public String  gettyp(){
	
	return typ;
	
	}

		
	




}
