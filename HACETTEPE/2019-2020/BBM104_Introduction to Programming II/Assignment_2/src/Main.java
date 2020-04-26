import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Models.Chief;
import Models.Faculty_Member;
import Models.Officer;
import Models.Part_time;
import Models.ResearchAssistant;
import Models.Security;
import Models.Worker;



public class Main {

	
	
	private static PersonelManager manager=new PersonelManager();
	public static void main(String[] args) throws IOException {
		
		
		Readdata(args[0]);
		
		ReadWeekTime(args[1]);
		manager.SetSalary();
		manager.print();
	}
	
	

	private static void ReadWeekTime(String filename) {
		
		try {

	           File f = new File(filename);
	           @SuppressWarnings("resource")
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           while ((readLine = b.readLine()) != null) {
	                
	            	
	            	manager.setWorkhour(readLine);
	            	
	           }
	      }catch (Exception e) {
			
		}
	}


	private static void Readdata(String filename) {
		
		try {

	           File f = new File(filename);
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           while ((readLine = b.readLine()) != null) {
	                
	            	String[] splitted=readLine.split("\t");
	            	String worktype=splitted[2];
	            	switch (worktype) {
					case "FACULTY_MEMBER":
					
						Faculty_Member faculty_Member=new Faculty_Member(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(faculty_Member);
						break;
					case "WORKER":
						
						Worker worker=new Worker(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(worker);
						break;
					case "SECURITY":
						
						Security security=new Security(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(security);
						break;
					case "OFFICER":
						
						Officer officer=new Officer(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(officer);
						break;
					case "CHIEF":
						
						Chief chief=new Chief(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(chief);
						break;
					case "PARTTIME_EMPLOYEE":
	
						Part_time part_time=new Part_time(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(part_time);
						break;
					case "RESEARCH_ASISTANT":
						
						ResearchAssistant assistant=new ResearchAssistant(splitted[0],splitted[1],Integer.parseInt(splitted[3]),splitted[2]);
						manager.Register(assistant);
						break;

					default:
						break;
					}
	            }
	            b.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		

	}
}
