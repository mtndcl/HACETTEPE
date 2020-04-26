package Models;

import java.util.Arrays;

public class Chief extends Full_time {

	public Chief(String name, String id, int year,String position) {
		super(name, id, year,position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Chief- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek) +" Sla: "+this.salary;
		
	}
	@Override
	public void SetSalary() {
		
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		float dayOfWork=125*20;
		float overwork=0;
		for (int hour : hourdperweek) {
			float mesai=hour-40;
			if(mesai>8 &&  mesai >0) {
				
				overwork+=15*8;
					
			}else if(mesai<=8 && mesai>0){
				overwork+=(mesai)*15;

			}
		}
		this.salary=Severance_pay+dayOfWork+overwork;
		super.SetSalary();
	}
}
