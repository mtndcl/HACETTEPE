package Models;

import java.util.Arrays;

public abstract class Employee extends Personnel {

	public Employee(String name,String id,int year,String position) {
		super(name, id, year,position);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Employee- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek);
		
	}
	
	@Override
	public void SetSalary() {
		// TODO Auto-generated method stub
		super.SetSalary();
	}

}
