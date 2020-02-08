/**
 * 
 * 
 * make operating system,CPU,RAM and HDD for computers
 * @author mockingbird
 *
 */
public class Computer extends Electronic {
	String opSys;
	String CPU;
	double RAM;
	double HDD;
	public Computer(String t,int a,double b,String c,String d,double e,double f,String h,String j,double k,double l) {
		super(t,a,b,c,d,e,f);
		this.opSys=h;
		this.CPU=j;
		this.RAM=k;
		this.HDD=l;
		
	}
	/**
	 * @return get operating system for Computer
	 */
	public String getopSys(){
		
		return opSys;
	}
	/**
	 * @return get CPU  for Computer
	 */
	
	public String getCPU(){
		
		return CPU;
	}
	/**
	 * @return get ram  for Computer
	 */
	public double getRAM(){
		
		return RAM;
	}
	/**
	 * @return get HDD  for Computer
	 */
	public double getHDD(){
		
		return HDD;
	}
	
}
