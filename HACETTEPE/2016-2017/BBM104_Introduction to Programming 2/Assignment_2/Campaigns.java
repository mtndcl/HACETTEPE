/**
 * 
 * make startdate,enddate,itemtyp and discountrate for Campaigns
 * @author mockingbird
 *
 */
public class Campaigns {
	String startDate;
	String endDate;
	String itemType;
	double discountRate;
	public Campaigns(String a,String b, String c,double d) {
		this.startDate=a;
		this.endDate=b;
		this.itemType=c;
		this.discountRate=d;
		
	}
	/**
	 * @return get startDate for Campaigns 
	 */
	public String getstartDate(){
		
		return startDate;
	}
	/**
	 * @return get end Date for Campaigns 
	 */
	public String getendDate(){
		
		return endDate;
	}
	/**
	 * @return get  item's Type for Campaigns 
	 */
	public String getitemType(){
	
	return itemType;
	}
	/**
	 * @return get discount Rate for Campaigns 
	 */
	public double getdiscountRate(){
	
	return discountRate;
	}
}
