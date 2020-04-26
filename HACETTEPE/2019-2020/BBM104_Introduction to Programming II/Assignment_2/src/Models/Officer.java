package Models;

import java.util.Arrays;

public class Officer extends Personnel {
	
	
	public Officer(String name,String id,int year,String position) {
		// TODO Auto-generated constructor stub
		super(name, id, year,position);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Officer- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek) +"saalary "+this.salary;
		
	}
	@Override
	public void SetSalary() {

		float ssBenefits=(2600/100 )*65;
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		
		
		float overwork=0;
		for (int hour : hourdperweek) {
			float mesai=hour-40;
			if(mesai>10) {
				
				
				overwork+=10*20;
					
			}else if(mesai<=10){
				overwork+=(mesai)*20;
				
				
			}
		}
				
		this.salary=2600+ssBenefits+Severance_pay+overwork;
		super.SetSalary();
	}
}
