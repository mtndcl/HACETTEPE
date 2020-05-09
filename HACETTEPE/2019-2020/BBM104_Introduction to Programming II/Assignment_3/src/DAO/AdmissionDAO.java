package DAO;

import java.util.ArrayList;

import Model.Admission;

public interface AdmissionDAO {
	
	public void AddAdmission(String id,String patientId);
	public ArrayList<Admission> getAllAdmission();
	public void AddExamination(String[] data); 
	public Admission getAdmission(String id);
	public void printExamination(Admission admission);
	
}	
