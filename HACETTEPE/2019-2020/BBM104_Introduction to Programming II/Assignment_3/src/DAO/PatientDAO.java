package DAO;

import java.util.ArrayList;

import Model.Patient;

public interface PatientDAO {
	
	
	///get all patients
	public ArrayList<Patient> getAllPatients(); 
    //remove patient by id
    public void removePatient(String id);
    ///Add new patient with giving informations
    public void addPatient(String id, String name, String surname, String phoneNumber, String address);
    ///get patient by id
	public Patient getPatientByID(String id);

}
