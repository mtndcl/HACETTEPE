import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import DAO.AdmissionDAO;
import DAO.AdmissionDAOImpl;
import DAO.PatientDAO;
import DAO.PatientDAOImpl;
import Decoder.Examination;
import Model.Admission;
import Model.Patient;

public class Main {

	public static void main(String[] args) {
		
		FileWriter  fileWriter=GetFileWriter();
		
		PatientDAO  patientDAO=new PatientDAOImpl(fileWriter);
		
		AdmissionDAO  admissionDAO=new AdmissionDAOImpl(fileWriter) ;
		
		try {

	           File f = new File(args[0]);
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	        
	           while ((readLine = b.readLine()) != null) {
	        	   String[]  data=readLine.split(" ");
	        	 //  System.err.println(readLine);
	        	   switch (data[0]) {
	        	   		case "AddPatient":
	        	   			String Address="";
	        	   		 	for(int i=5;i<data.length;i++) {
	        	   		 		Address+=" " +data[i];
	  	        	   		}
	        	   		 	
	        	   			patientDAO.addPatient(data[1],data[2],data[3],data[4],Address);
	        	   			break;
	        	   		case "RemovePatient":
		        	   		
	        	   			patientDAO.removePatient(data[1]);	
	        	   			break;
	        	   		case "CreateAdmission":
		        	   		
	        	   			admissionDAO.AddAdmission(data[1],data[2]);	
	        	   			break;
	        	   		case "AddExamination":
		        	   		
	        	   			admissionDAO.AddExamination(data);
	        	   			break;
	        	   		case "TotalCost":
	        	   			
	        	   			Admission admission=admissionDAO.getAdmission(data[1]);

	        	   			admissionDAO.printExamination(admission);
	        	   			
	        	   			break;
	        	   		case "ListPatients":
		        	   		
	        	   			patientDAO.getAllPatients();
	        	   			break;

	        	   		default:
	        	   			break;
	        	   }
	           }
	        }catch (Exception e) {
				
	        }
		
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static FileWriter GetFileWriter() {
		 try{    
	           FileWriter fw=new FileWriter("output.txt");    
	             
	           return fw;
	          }
		 catch(Exception e){
			 e.printStackTrace();
			}
		return null;    
	        
	}

}
