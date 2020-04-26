package Models;

import java.util.Arrays;

public class Worker extends Full_time {

	public Worker(String name, String id, int year,String position) {
		super(name, id, year,position);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Worker- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek);
		
	}
	
	@Override
	public void SetSalary() {
		
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		float dayOfWork=105*20;
		float overwork=0;
		for (int hour : hourdperweek) {
			float mesai=hour-40;
			if(mesai>10) {
				
				overwork+=11*10;
					
			}else if(mesai<=10 && mesai>0){
				overwork+=(mesai)*11;

			}
		}
		this.salary=Severance_pay+dayOfWork+overwork;
		super.SetSalary();
	}

}
