package Models;

import java.util.Arrays;

public abstract class Personnel {
	
	
	
	
	protected float salary;
	protected int[]  hourdperweek;
	protected String name;
	protected String id;
	protected int year;
	protected String position;
	public Personnel(String name, String id , int year,String position) {
		// TODO Auto-generated constructor stub
		super();
		this.name=name;
		this.id=id;
		
		this.position=position;
		this.year=year;
		this.hourdperweek=new int[4];
	}
	
	
	public void setHourperWeek(int w1,int w2, int w3 ,int w4) {
		
		this.hourdperweek[0]=w1;
		this.hourdperweek[1]=w2;
		this.hourdperweek[2]=w3;
		this.hourdperweek[3]=w4;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-personel- name : "+this.name +" id : " +this.id +"  year : "+this.year +" -- "+Arrays.toString(this.hourdperweek);
	}
	public String getText() {
		
		String[] names=this.name.split(" ");
		String text="Name  : "+names[0] +"\n"
				+ "Surname : "+names[1]+"\n"
				+"Registration Number : "+this.id+"\n"
				+"Position : "+this.position+"\n"
				+"Year of Start : "+this.year+"\n"
				+"Total Salary : "+this.salary +" TL\n";
				
		
		return  text;
	}

	public void SetSalary() {
		// TODO Auto-generated method stub
		
	}

	

}
