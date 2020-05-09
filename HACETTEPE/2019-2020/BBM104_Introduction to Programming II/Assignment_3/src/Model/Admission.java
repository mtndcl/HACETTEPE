package Model;

import java.util.ArrayList;

import Decoder.Examination;

public class Admission {
	
	
	private String id;
	private String patientId;
	private ArrayList<Examination>  examinations;
	public ArrayList<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(ArrayList<Examination> examinations) {
		this.examinations = examinations;
	}

	public Admission(String id,String patientId) {
		this.id=id;
		this.patientId=patientId;
		this.examinations=new ArrayList<Examination>();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id : "+this.id+" patient id : "+this.patientId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void AddExamination(Examination examination) {
		
		examinations.add(examination);
	}

}
