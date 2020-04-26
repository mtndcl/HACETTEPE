package Models;

import java.util.Arrays;

public abstract class Full_time extends Employee {

	public Full_time(String name,String id,int year,String position) {
		// TODO Auto-generated constructor stub
		super(name, id, year,position);
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Fulltme- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek);
		
	}
	
	@Override
	public void SetSalary() {
		// TODO Auto-generated method stub
		super.SetSalary();
	}
}
