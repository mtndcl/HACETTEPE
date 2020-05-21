import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import DAO.AdmissionDAO;
import DAO.AdmissionDAOImpl;
import DAO.PatientDAO;
import DAO.PatientDAOImpl;
import DAO.SortAdmissionById;
import DAO.SortPatientById;
import DAO.SortPatientByName;
import Decoder.Examination;
import Model.Admission;
import Model.Patient;

public class Implantation {

	public static void ReadInputFile(String filename) throws IOException {
		
		///my output file
		FileWriter  fileWriter=GetFileWriter("output.txt");
		
	
		///Create patient DAO
		PatientDAO  patientDAO=new PatientDAOImpl();
		
		///Create Admission DAO
		AdmissionDAO  admissionDAO=new AdmissionDAOImpl() ;
		
		//////Read input file
		try {

	           File f = new File(filename);
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	        
	           while ((readLine = b.readLine()) != null) {
	        	   String[]  data=readLine.split(" ");
	        	  
	        	   
	        	   switch (data[0]) {
	        	   
	        	   	
	        	   
	        	   		//AddPatient
	        	   		case "AddPatient":
	        	   		
	        	   			String address="Address:";
	        	   			for(int i=5;i<data.length;i++) {
	        	   				address+=" "+data[i];
	        	   			}
	        	   		 	//String id ,String name,String surname,String phoneNumber,String address
	        	   			patientDAO.addPatient(data[1],data[2],data[3],data[4],address);
	        	   			
	        	   			
	        	   			fileWriter.write("Patient "+data[1]+" "+data[2]+" Added\n");
	        	   			break;
	        	   		//RemovePatient
	        	   		case "RemovePatient":
	        	   			
	        	   			Patient p=patientDAO.getPatientByID(data[1]);
	        	   			fileWriter.write("Patient "+p.getId()+" "+p.getName()+" removed\n");
	        	   			patientDAO.removePatient(data[1]);
	        	   			
	        	   			break;
	        	   		///CreateAdmission
	        	   		case "CreateAdmission":
		        	   		//Admission 7 created
	        	   			admissionDAO.AddAdmission(data[1],data[2]);	
	        	   			fileWriter.write("Admission "+data[1]+" created\n");
	        	   			break;
	        	   		//AddExamination
	        	   		case "AddExamination":
		        	   		
	        	   			admissionDAO.AddExamination(data);
	        	   			
	        	   			fileWriter.write(data[2]+" examination added to admission "+data[1]+"\n");
	        	   			break;
	        	   			
	        	   		//Total cost
	        	   		case "TotalCost":
	        	   			
	        	   			Admission admission=admissionDAO.getAdmission(data[1]);

	        	   			
	        	   			fileWriter.write("TotalCost for admission "+ admission.getId()+"\n");
	        	   			double total=0;
	        	   			for (Examination examination : admission.getExaminations()) {
	        	   				total+=examination.getPrice();
	        	   				fileWriter.write("\t"+ examination.getDesc()+ " " +(int) examination.getPrice() +"$\n");
	        	   			}
	        	   			fileWriter.write("\tTotal: "+(int)total +"$\n");
	        	   			break;
	        	   		//ListPatients
	        	   		case "ListPatients":
		        	   		
	        	   			fileWriter.write("Patient List:\n");
	        	   			Collections.sort(patientDAO.getAllPatients(),new SortPatientByName());
	        	   			for (Patient patient : patientDAO.getAllPatients()) {
	        	   					fileWriter.write(patient.toString()+"\n");
	        	   			}
	        	   			
	        	   			break;
	        	   		default:
	        	   			break;
	        	   }
	           }
	        }catch (Exception e) {
				e.printStackTrace();
	        }
		
		
		fileWriter.close();
		
		
		////Write patient output file
		FileWriter  patientwriter=GetFileWriter("Patientout.txt");
		
		Collections.sort(patientDAO.getAllPatients(),new SortPatientById());
		for (Patient patient : patientDAO.getAllPatients()) {
			patientwriter.write(patient.toString()+"\n");
		}
		patientwriter.close();
		
		///Write admission files
		FileWriter  admissiontwriter=GetFileWriter("Admissionout.txt");
		Collections.sort(admissionDAO.getAllAdmission(),new SortAdmissionById());
		for (Admission admission : admissionDAO.getAllAdmission()) {
			admissiontwriter.write(admission.getId()+"\t"+admission.getPatientId()+"\n");
			for (Examination examination : admission.getExaminations()) {
				admissiontwriter.write( examination.getDesc()+"\n");
   			}
		}
		admissiontwriter.close();

	}
	
	///create output.txt File
	private static FileWriter GetFileWriter(String filename) {
		 try{    
	           return new FileWriter(filename);    
	             
	          }
		 catch(Exception e){
			 e.printStackTrace();
			}
		return null;    
	        
	}

	
	

}
