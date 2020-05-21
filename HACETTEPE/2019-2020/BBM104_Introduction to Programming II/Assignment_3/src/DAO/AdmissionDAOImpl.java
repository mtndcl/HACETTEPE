package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	
	private ArrayList<Admission> admissions; 
	public AdmissionDAOImpl() {
		////read admission file
		admissions=new ArrayList<Admission>();
		try {

	           File f = new File("admission.txt");
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           Admission admission=null;
	           while ((readLine = b.readLine()) != null) {
	        	   String[] info=readLine.split("\t");
	        	   if(info[0].equals("Outpatient") ||  info[0].equals("Inpatient")  ) {
	        		   
	        		   Examination examination = info[0].equals("Outpatient")  
	        					? new OutpatientExamination() 
	        					: new InpatientExamination();
	        					

	        		   String[]  line=info[1].split(" ");
	        		   
	        			examination.addoperation(admission, line);

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
		
		
		examination.addoperation(admission, Arrays.copyOfRange(data, 3,data.length));
		
		 
	}
	@Override
	public Admission getAdmission(String id) {
		return admissions.stream().filter(admissions -> id.equals(admissions.getId())).findFirst().orElse(null);	
	}


	

}
