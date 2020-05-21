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
	
	public PatientDAOImpl() {
		
	
		patients=new ArrayList<Patient>();
		
		////read patient file
		try {

	           File f = new File("patient.txt");
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	           while ((readLine = b.readLine()) != null) {
	        	
	        	   String[] info=readLine.split("\t");
	        	   String[] line1=info[1].split(" ");
	        	   ///String id ,String name,String surname,String phoneNumber,String address
	        	   addPatient(info[0],line1[0], line1[1], info[2], info[3]);
	           }
	      }catch (Exception e) {
		}
	}
	@Override
	public ArrayList<Patient> getAllPatients() {
		
		return this.patients;
	}

	@Override
	public void removePatient(String id) {
		
		Patient patient= patients.stream().filter(patients -> id.equals(patients.getId())).findFirst().orElse(null);
		if(patient!=null) {
		
			patients.remove(patient);
		}else {
			System.err.println("No paient has this id ==> "+id);
		}

	}
	@Override
	public void addPatient(String id,String name,String surname,String phoneNumber,String address) {

   	   	Patient patient=new Patient(id,name,surname,phoneNumber,address);
   	   	patients.add(patient);
	}
	@Override
	public Patient getPatientByID(String id) {
		return patients.stream().filter(patients -> id.equals(patients.getId())).findFirst().orElse(null);
	}

}
