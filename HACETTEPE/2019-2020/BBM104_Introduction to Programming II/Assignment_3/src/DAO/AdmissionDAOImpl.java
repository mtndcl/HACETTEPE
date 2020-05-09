package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Decoder.DoctorVisit;
import Decoder.Examination;
import Decoder.Imaging;
import Decoder.InpatientExamination;
import Decoder.Measurements;
import Decoder.OutpatientExamination;
import Decoder.Tests;
import Model.Admission;
import Model.Patient;

public class AdmissionDAOImpl implements  AdmissionDAO{
	
	
	private FileWriter  fileWriter;
	private ArrayList<Admission> admissions; 
	public AdmissionDAOImpl(FileWriter fileWriter) {
		admissions=new ArrayList<Admission>();
		this.fileWriter=fileWriter;
		try {

	           File f = new File("admission.txt");
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           Admission admission=null;
	           while ((readLine = b.readLine()) != null) {
	        	   String[] info=readLine.split("\t");
	        	   if( info[0].equals("Outpatient") ) {
	        		   
	        		   Examination examination=new OutpatientExamination();
	        		   String[]  line=info[1].split(" ");
	        		   for(int i=0;i<line.length;i++) {
	        			   switch (line[i]) {
	        			   	case "imaging":
	        			   		examination=new Imaging(examination);
	        			   		break;
	        			   	case "measurements":
	        			   		examination=new Measurements(examination);
	        			   		break;
	        			   	case "tests":
	        			   		examination=new Tests(examination);
	        			   		break;
	        			   	case "doctorvisit":
	        			   		examination=new Tests(examination);
	        			   		break;
	        			   	default:
	        			   		break;
						}
	        		   }
	        		   admission.AddExamination(examination);
	        		   
	        	   }else if(info[0].equals("Inpatient")){
	        		   Examination examination=new InpatientExamination();
	        		   String[]  line=info[1].split(" ");
	        		   for(int i=0;i<line.length;i++) {
	        			   switch (line[i]) {
	        			   	case "imaging":
	        			   		examination=new Imaging(examination);
	        			   		break;
	        			   	case "measurements":
	        			   		examination=new Measurements(examination);
	        			   		break;
	        			   	case "tests":
	        			   		examination=new Tests(examination);
	        			   		break;
	        			   	case "doctorvisit":
	        			   		examination=new Tests(examination);
	        			   		break;
	        			   	default:
	        			   		break;
						}
	        		   }
	        		   admission.AddExamination(examination);
	        		  
	        	   }else {
	        		   admission=new Admission(info[0], info[1]);
		        	   admissions.add(admission);
	        	   }
	        	  
	        	 
	        	 		
	            	
	           }
	      }catch (Exception e) {
			
		}
	}

	@Override
	public void AddAdmission(String id,String patientId) {
	
		Admission admission=new Admission(id, patientId);
 	   	admissions.add(admission);
 	   	
   	   	print( "Admission " +admission.getId()+ " created" );

		
	}

	@Override
	public ArrayList<Admission> getAllAdmission() {
		
		return this.admissions;
	}
	@Override
	public void AddExamination(String[] data) {
		
		
		Admission admission= admissions.stream().filter(admissions -> data[1].equals(admissions.getId())).findFirst().orElse(null);
		
		Examination examination = data[2].equals("Outpatient")  
				? new OutpatientExamination() 
				: new InpatientExamination();
	
		  for(int i=3;i<data.length;i++) {
			  
			  switch (data[i]) {
			   	case "imaging":
			   		examination=new Imaging(examination);
			   		break;
			   	case "measurements":
			   		examination=new Measurements(examination);
			   		break;
			   	case "tests":
			   		examination=new Tests(examination);
			   		break;
			   	case "doctorvisit":
			   		examination=new DoctorVisit(examination);
			   		break;
			   	default:
			   		break;
			  }
			   
		   }
		   admission.AddExamination(examination);
		   
		   ///Inpatient examination added to admission 7

		
			print(data[2]+" examination added to admission "+admission.getId());
		
	}

	private void print(String string) {
		try {
			fileWriter.write(string+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Admission getAdmission(String id) {
		return admissions.stream().filter(admissions -> id.equals(admissions.getId())).findFirst().orElse(null);

		
	}

	@Override
	public void printExamination(Admission admission) {
		
		print("TotalCost for admission "+ admission.getId());
		double total=0;
		for (Examination examination : admission.getExaminations()) {
			total+=examination.getPrice();
			print("\t"+ examination.getDesc()+ " " + examination.getPrice() +"$");
		}
		print("\tTotal: "+total +"$");
	}

}
