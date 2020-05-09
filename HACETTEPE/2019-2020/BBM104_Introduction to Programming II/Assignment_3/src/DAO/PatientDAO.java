package DAO;

import java.util.ArrayList;

import Model.Patient;

public interface PatientDAO {
	
	public ArrayList<Patient> getAllPatients(); 
    
    public void removePatient(String id);
    
     public void addPatient(String id, String name, String surname, String phoneNumber, String address);

	

}
