package Model;

public class Patient {
	
	
	private String id;
	
	private String name;
	private String surname;
	
	private String phoneNumber;
	private String address;
	public Patient(String id ,String name,String surname,String phoneNumber,String address) {
		
		
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.phoneNumber=phoneNumber;
		this.address=address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		
		return  this.id
				+" "+this.name
				+" "+this.surname
				+" "+this.phoneNumber
				+" "+this.address ;
	}

}
