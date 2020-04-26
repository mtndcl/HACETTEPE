package Models;

import java.util.Arrays;

public class Security extends Personnel {

	public Security(String name,String id,int year,String position) {
		// TODO Auto-generated constructor stub
		super(name, id, year,position);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Security- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek) +"salary : "+this.salary;
		
	}
	public void SetSalary() {

		
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		
		
		float hourOfWork=0;
		
		for (int hour : hourdperweek) {
			
			hourOfWork+=hour;
		}
				
		this.salary=Severance_pay+(hourOfWork*10)+24*15;
		super.SetSalary();
	}}
