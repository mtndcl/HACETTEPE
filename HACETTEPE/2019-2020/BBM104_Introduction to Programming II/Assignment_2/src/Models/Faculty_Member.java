package Models;

import java.util.Arrays;

public class Faculty_Member extends Academician {
		
	
	public Faculty_Member(String name,String id,int year,String position) {
		super(name, id, year,position);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-faculty member- name : "+this.name +" id : " +this.id +"  year : "+this.year +" -- "+Arrays.toString(this.hourdperweek)  +" Salary is : "+this.salary;
	}
	@Override
	public void SetSalary() {
		
		float Special_service_benefits=(this.basesalary/100 )*135;
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		
		float addCourseFee=0;
		for (int hour : hourdperweek) {
			float mesai=hour-40;
			if(mesai>8) {
				
				
					addCourseFee+=8*20;
					
			}else if(mesai<=8){
				addCourseFee+=(mesai)*20;
				
				
			}
		}
		
		this.salary=this.basesalary+Special_service_benefits+Severance_pay+addCourseFee;
		super.SetSalary();
	}

}
