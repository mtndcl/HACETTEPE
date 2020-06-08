import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
			
		/////Read stack.txt file
		Stack_Implemention.readstackFile("stack.txt");
		///Read queue.txt file
		Queue_Implemention.readqueueFile("queue.txt");
		///Read Command.txt file then apply Rules
		readCommandFile(args[0]);
		//Close stack
		Stack_Implemention.Close();
		///close Queue
		Queue_Implemention.Close();
	       
		
	}
	////This function read command then call rules about input file
	public static void readCommandFile(String filename) {
		try {

	           File f = new File(filename);
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	        
	           while ((readLine = b.readLine()) != null) {
	        	   String[]  data=readLine.split(" ");
	        	   
	        	 
	        	   if (data[0].equals("S")) {
	        		  Stack_Implemention.HandleStack(data);
	        	   }else if(data[0].equals("Q")){
	        		   Queue_Implemention.HandleQueue(data);
	        	   }

	           }
	     }catch (Exception e) {
			e.printStackTrace();
		}

	}
}
