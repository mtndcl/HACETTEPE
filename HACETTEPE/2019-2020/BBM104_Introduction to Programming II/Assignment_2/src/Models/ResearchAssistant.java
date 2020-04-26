package Models;

import java.util.Arrays;

public class ResearchAssistant extends Academician {

	public ResearchAssistant(String name,String id,int year,String position) {
		// TODO Auto-generated constructor stub
		super(name, id, year,position);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-Research asisitan- name : "+this.name +" id : " +this.id +"  year : "+this.year+" -- "+Arrays.toString(this.hourdperweek)+" salary: "+this.salary;
		
	}
	@Override
	public void SetSalary() {
		
		float Special_service_benefits=(this.basesalary/100 )*105;
		float Severance_pay=( 2020-this.year)*20*(0.8f);
		
		float addCourseFee=0;
		
		
		this.salary=this.basesalary+Special_service_benefits+Severance_pay+addCourseFee;
		super.SetSalary();
	}
}
