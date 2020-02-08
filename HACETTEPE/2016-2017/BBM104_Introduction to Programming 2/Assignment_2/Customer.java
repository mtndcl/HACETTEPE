/**
 * 
 * make customer id, password,balance,paid and status for customers
 * @author mockingbird
 *
 */
public class Customer extends Person {
	int customerID;
	String password;
	double balance;
	double paid;
	public enum Status{
		CLASSIC,
		SILVER,
		GOLDEN
	};
	Status status;
	/*
	 * 
	 * 
	 */
	public Customer(String a,String b,String c,double h,String f,int k, Status j) {
		super(a,b,c);
		this.password=f;
		this.balance=h;
		this.customerID=k;
		this.status=j;
		this.paid=0;
		
	}
	
	
	/**
	 * @return get  ID  for customers
	 */
	public int getcustomerID(){
		
		return customerID;
	}
	/**
	 * @return get password  for customers
	 */
	public String getpassword(){
		
		return password;
	}

	/**
	 * @return get balance  for customers
	 */
	public double getbalance(){
		
		return balance;
	}
	/**
	 * @return get paid  for customers
	 */
	public double getpaid(){
		
		return paid;
	}
	/**
	 * @return get status  for customers
	 */
	public Status getstatus(){
		
		return status;
	}
	/**
	 * @param set customer status
	 */
	public Status setstatus(Status m){
		this.status=m;
		return status;
	}
	/**
	 * 
	 * @param take old password
	 * @return p	new password
	 */
	public String setpassword(String p){
		
		this.password=p;
		return password;
	}
/**
 * 
 * @param p	set balance when customer buy some items
 * 	
 */
	public void setBalance(double p){
		
		this.balance=p;

	}
	/**
	 * @param p		sum paid of customer 
	 */
	public void setpaid(double p){
		
		this.paid=p;

	}
	/**
	 * 
	 * @param a add new money balance of customer 
	 */
	public void addBalance(double a){
		this.balance=this.balance+a;
	}

	/**
	 * @param show customer info 
	 */
	void Display(){
		
		System.out.println("ismi: "+getname());
		System.out.println("id: "+getcustomerID());
		System.out.println("dogum gunu: "+getbirth_day());
		System.out.println("satatus"+getstatus());
	}

	
	
}
