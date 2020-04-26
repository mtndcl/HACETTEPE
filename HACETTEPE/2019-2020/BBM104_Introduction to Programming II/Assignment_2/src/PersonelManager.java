import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Models.Personnel;

public   class PersonelManager {

	
	
	
	private static ArrayList<Personnel>  personnels=new ArrayList<Personnel>();
	
	
	
	public void Register(Personnel personnel) {
		personnels.add(personnel);
	}
	
	public void print() throws IOException {
		for (Personnel personnel :personnels) {
			FileWriter file=CreateFile(personnel.getId()+".txt");
			
			file.write(personnel.getText());
			
			file.close();
			
		}
	}

	public void setWorkhour(String readLine) {
		
		
		String[] splitted=readLine.split("\t");
		
		Personnel personnel=findPersonelbyID(splitted[0]);
		
		if(personnel!=null) {
			personnel.setHourperWeek(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
		}
	}
	
	private static Personnel  findPersonelbyID(String id) {
		
		return personnels.stream().filter(personel -> id.equals(personel.getId())).findFirst().orElse(null);
		
	}
	
	public void SetSalary() {
		for (Personnel personnel : personnels) {
			
			personnel.SetSalary();
		}
	}
	
	private static FileWriter CreateFile(String name) {
		File file = new File(name);
		
		try {
			return  new FileWriter(file);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		return null;
	}

}
