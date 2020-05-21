package DAO;

import java.util.ArrayList;

import Model.Admission;

public interface AdmissionDAO {
	
	////AddAdmission
	public void AddAdmission(String id,String patientId);
	////Get all existing admission
	public ArrayList<Admission> getAllAdmission();
	///Add Examination
	public void AddExamination(String[] data); 
	///get admission by id
	public Admission getAdmission(String id);
	///print Admission

	
}	
