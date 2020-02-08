import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelloJava {

	public static void main(String[] args)  throws IOException
	{	
		
		try {
			Scanner scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			//System.out.println(line);
			String[] splitted = line.split("\\s+");
			if(new String("Armstrong").equals(splitted[0])){
				
					Armstrong(Integer.parseInt(splitted[1]));
			}
			else if(new String("Arcsinh").equals(splitted[0])){
				
				Arcsinh(Double.parseDouble(splitted[1]));
				
			}
			else if (new String("IntegrateRiemann").equals(splitted[0]) | new String("IntegrateReimann").equals(splitted[0])){
				
				String func_name=splitted[1];
				IntegrateRiemann(func_name,Double.parseDouble(splitted[2]),Double.parseDouble(splitted[3]),Double.parseDouble(splitted[4]));
				
				
			}
				
				
				
			}
			scanner.close();
			} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
			}
		
		}
	
	//this function calculate IntegrateRiemann theory 
	public static double IntegrateRiemann(String name_of_func,double deger1, double deger2, double deger3)
	{
		
		double sum_partition_area = 0;
		double width = (deger2 - deger1)/deger3;
		int i;
		
		
		{
			
			if(new String("Func1").equals(name_of_func)){
				
				for(i=0;i<deger3;i++){
					sum_partition_area += Func1(deger1+ width*i + width/2)*width;
				}
				System.out.print("IntegrateRiemann "+name_of_func+" "+(int)deger1+" "+(int)deger2+" "+(int)deger3+" result: "+sum_partition_area+"\n");
			}
			
			else if(new String("Func2").equals(name_of_func)){
				
				for(i=0;i<deger3;i++){
					sum_partition_area += Func2(deger1+ width*i + width/2)*width;
				}
				System.out.print("IntegrateRiemann "+name_of_func+" "+(int)deger1+" "+(int)deger2+" "+(int)deger3+" result: "+sum_partition_area+"\n");
			}
			else if(new String("Func3").equals(name_of_func)){
				
				for(i=0;i<deger3;i++){
					sum_partition_area += Func3(deger1+ width*i + width/2)*width;
				}
				System.out.print("IntegrateRiemann "+name_of_func+" "+deger1+" "+deger2+" "+(int)deger3+" result: "+sum_partition_area+"\n");
			}
		}
		return 0;
	
		
		
			
		
	
		
		
	}
	//this function calculate Arcsinh value 
	public static double Arcsinh(double deger1)
	{	
		
		double dsum=0;
		int i;
		for(i=0;i<30;i=i+1)
		{
			dsum += (Math.pow(-1,i)*fonk(2*i)*Math.pow(deger1,(2*i+1)))/(Math.pow(4,i)*Math.pow(fonk(i),2)*(2*i+1));
		}
		
		System.out.println("Arcsinh "+deger1 +" result: " +dsum);
		return 0;
		
		
	}
	//this function control which number is armstrong number. 
	public static double Armstrong(int deger1)
	{
		
		int itemp=0;
		int isum=0;
		int i;
		System.out.print("Armstrong "+deger1+" result: ");
		for(i=0;Integer.toString(i).length()<=deger1;i=i+1)
		{
		
			isum=0;
			itemp = i;
			
			while(itemp != 0)
			{
				
				isum += Math.pow(itemp%10,Integer.toString(i).length());
				itemp = itemp/10;
			}
			if(isum == i){
				System.out.print(i+" ");
			}
			
		}
		
		
		
		return 0;
		
		
	}
	
	//this function calculate faktoriel fanction 
	public static double fonk( int deger)
	{
		
		int sum=1;
		while(deger>0)
		{
			sum=sum*deger;
			deger=deger-1;
		}
		
		
		
		return sum;
		
		
	}
	//this function calculate x2-x+3 of value
	public static double Func1( double deger)
	{
		
		
		return Math.pow(deger, 2)-deger+3;
		
		
	}
	//this function calculate (3sin(??)-4) of square value
	public static double Func2( double deger)
	{

		
		return Math.pow((3*Math.sin(deger)-4),2);
		
		
	}
	
	//this function calculate sin(x) value
	public static double Func3( double deger)
	{
		
		double dsum=0;
		int i;
		for(i=0;i<30;i=i+1)
		{
			dsum += (Math.pow(-1,i)*fonk(2*i)*Math.pow(deger,(2*i+1)))/(Math.pow(4,i)*Math.pow(fonk(i),2)*(2*i+1));
		}
		
		return dsum;
		
		
	}

}
