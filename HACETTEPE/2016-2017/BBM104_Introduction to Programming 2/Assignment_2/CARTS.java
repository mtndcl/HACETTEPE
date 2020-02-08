import java.util.ArrayList;
/**
 * 
 * make carts for who add some item in cart
 * @author mockingbird
 *
 */
public class CARTS {
	int CustomerIDC;
	ArrayList<Integer> Cart_List1 = new ArrayList<Integer>();

	public CARTS(int a,int b) {

	this.CustomerIDC=a;
	this.Cart_List1.add(b);
	
	
	}
	/**
	 * @return get Customer ID  for Carts 
	 */
	public int getCustomerIDC(){
		
		return CustomerIDC;
	}
	/**
	 * @return get Cart List for Carts 
	 */
	public ArrayList<Integer> getItemIDC(){
		
		return Cart_List1;
	}
	/**
	 * @param a
	 * 		item id take in cart 
	 */
	
	public void addtocart(int a){
		this.Cart_List1.add(a);
				
	}
}
