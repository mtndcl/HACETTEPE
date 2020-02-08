import java.util.ArrayList;
/**
 * make Customer ID,order Date and totalCost
 * @author mockingbird
 *
 */
public class Order {
	
	int CustomerID;
	String orderDate;
	double totalCost;
	ArrayList<Integer> purchased_items = new ArrayList<Integer>() ;
	public Order(String a,double b,ArrayList<Integer> c,int d) {
		this.orderDate=a;
		this.totalCost=b;
		this.purchased_items.addAll(c);
		this.CustomerID=d;
	}
	/**
	 * 
	 * @return get order date
	 */
	public String getorderDate(){
		
		return orderDate;
		
	}
	/**
	 * 
	 * @return get total cost
	 */
	public double gettotalCost(){
		
		return totalCost;
		
	}
	/**
	 * 
	 * @return get customer id so make a cart for him or her
	 */
	public int getCustomerID(){
		
		return CustomerID;
		
	}
	/**
	 * 
	 * @return make cart 
	 */
	public ArrayList<Integer> getpurchased_items(){
	
	return purchased_items;
	
}
	
}
