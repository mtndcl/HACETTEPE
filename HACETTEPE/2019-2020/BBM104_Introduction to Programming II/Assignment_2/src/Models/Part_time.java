package Models;

import java.util.Arrays;

public class Part_time extends Employee {

	public Part_time(String name,String id,int year,String position) {
		// TODO Auto-generated constructor stub
		super(name, id, year,position);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		
		return "-part-time- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek);
		
	}
	@Override
	public void SetSalary() {
		
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		int sumhour=0;
		for (int hour : hourdperweek) {
			
			if(hour<10) {
				
			}else if(hour>20) {
				hour=20;
				sumhour+=hour;
			}else {
				sumhour+=hour;
			}
			
		}
		this.salary=Severance_pay+(sumhour*18);	
		super.SetSalary();
	}
}
