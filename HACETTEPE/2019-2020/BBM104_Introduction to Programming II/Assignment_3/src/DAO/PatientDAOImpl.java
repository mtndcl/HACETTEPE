package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import Model.Patient;

public class PatientDAOImpl implements PatientDAO  {
	
	
	
	private ArrayList<Patient> patients; 
	private FileWriter fileWriter;
	public PatientDAOImpl(FileWriter fileWriter) {
		
		this.fileWriter=fileWriter;
		patients=new ArrayList<Patient>();
		try {

	           File f = new File("patient.txt");
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           while ((readLine = b.readLine()) != null) {
	        	
	        	   String[] info=readLine.split(" ");
	        	   String[] line1=info[0].split("\t");
	        	   String[] line2=info[1].split("\t");
	        	   String Address=line2[2];
	        	  
	        		 Address+= " "+info[2];
	        	   
	        	   
	        	   Patient patient=new Patient(line1[0], line1[1], line2[0], line2[1],Address);
	        	  patients.add(patient);
	        	 		
	            	
	           }
	      }catch (Exception e) {
			
		}
	}
	@Override
	public ArrayList<Patient> getAllPatients() {
		
		Collections.sort(patients,new SortPatient());


		for (Patient patient : patients) {
			
				print(patient.toString());
			
		}
		return this.patients;
	}

	@Override
	public void removePatient(String id) {
		
		
		Patient patient= patients.stream().filter(patients -> id.equals(patients.getId())).findFirst().orElse(null);
		
		if(patient!=null) {
		
			print( "Patient "+patient.getId() +" "+ patient.getName()+" removed" );
			patients.remove(patient);
		}else {
			print( "No paient has this id ==> "+id );
		}
		
		
	}
	@Override
	public void addPatient(String id,String name,String surname,String phoneNumber,String address) {
		
   	   
   	   
   	   	Patient patient=new Patient(id,name,surname,phoneNumber,address);
   	
   	   	patients.add(patient);

   	  
   	   	print( "Patient "+patient.getId() +" "+ patient.getName()+" added" );
	

	}
	
	private void print(String string) {
		try {
			fileWriter.write(string+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
