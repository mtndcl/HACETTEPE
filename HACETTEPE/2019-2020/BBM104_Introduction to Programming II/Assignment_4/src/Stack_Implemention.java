import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Stack_Implemention {

				
		
		private static Stack stack=new Stack();
		
		private static FileWriter fileWriter;
		
		//read stack.txt file then push all value to created stack 
		public static void readstackFile(String filename) throws IOException {
			fileWriter=new FileWriter("stackOut.txt");
			try {

		           File f = new File(filename);
		          
		           BufferedReader b = new BufferedReader(new FileReader(f));
		           String readLine =null;
		        
		           while ((readLine = b.readLine()) != null) {
		        	   String[]  data=readLine.split(" ");
		        	   for (int i = data.length-1; i >=0; i--) {
		        		   stack.push(Integer.parseInt(data[i]));
		        	   }

		           }
		     }catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		//Handle set the apply them
		public static void HandleStack(String[] data) throws IOException {
			
			switch (data[1]) {
				case "removeGreater":
					removeGreater(Integer.parseInt(data[2]));
					break;
				case "calculateDistance":
					calculateDistance();
					break;
				case "addOrRemove":
					addOrRemove(Integer.parseInt(data[2]));
					break;
				case "reverse":
					Reverse(Integer.parseInt(data[2]));
					break;
				case "sortElements":
					sortElements();	
					break;
				case "distinctElements":
					distinctElements();
					break;
		
				default:
					break;
			}
		}
		
		///Find number of distinct element in stack
		private static void distinctElements() throws IOException {
			
			HashSet<Integer>  set=new HashSet<Integer>();
			Stack  temp=new Stack();
			while (!stack.isEmpty()) {
				
				int value=stack.pop();
				temp.push(value);
				set.add(value);
				
			}
			while (!temp.isEmpty()) {
				stack.push(temp.pop());
			}
			fileWriter.write("After distinctElements:\n");
			fileWriter.write("Total distinct element="+set.size()+"\n");
	
		}
		///Sort stack elements 
		private static void sortElements() throws IOException {
			
			Stack temp = new Stack(); 
	        while(!stack.isEmpty()) 
	        { 
	            int tmp = stack.pop(); 
	            while(!temp.isEmpty() && temp.top()< tmp) 
	            { 
	            	stack.push(temp.pop()); 
	            } 
	            temp.push(tmp); 
	        }
	        stack=temp;
	        fileWriter.write("After sortElements:\n");
	    	print();
		}
		
		///reverse the stack
		private static void Reverse(int value) throws IOException {
			
			Stack temp=new Stack();
			Stack temp2=new Stack();
			for (int i = 0; i < value; i++) {
				
				temp.push(stack.pop());
			}
			while (!temp.isEmpty()) {
				temp2.push(temp.pop());
			}
			while (!temp2.isEmpty()) {
				stack.push(temp2.pop());
			}
			fileWriter.write("After reverse "+value+":\n");
			print();
		}
		//Add or remove element respect to giving element
		private static void addOrRemove(int value) throws IOException {
			
			if (value>0) {
				Random r=new Random();
				stack.push(r.nextInt(50));
			}else if (value<0) {
				value=Math.abs(value);
				for (int i = 0; i < value; i++) {
					
					if (!stack.isEmpty()) {
						stack.pop();
					}
				}
				value=-value;
			}
			fileWriter.write("After addOrRemove "+value+":\n");
			print();
			
		}
		
		///calculate distance of current stack
		private static void calculateDistance() throws IOException {
			
			int sum=0;
			ArrayList<Integer>  popped=new ArrayList<Integer>();
			Stack temp=new Stack();
	 		while (!stack.isEmpty()) {
				int value=stack.pop();
				temp.push(value);
				Stack  temp_stack=stack; 
				while (!temp_stack.isEmpty()) {
					int next_value=temp_stack.pop();
					sum+=Math.abs(value-next_value);
					popped.add(next_value);
				}
				for (int i = popped.size()-1; i >=0; i--) {
					stack.push(popped.get(i));
				}
				popped.clear();

			}
	 		while (!temp.isEmpty()) {
				stack.push(temp.pop());
				
			}
	 		
	 		fileWriter.write("After calculateDistance:\n");
	 		fileWriter.write("Total distance="+sum+"\n");
	 	
	 		
			
		}
		
		//Remove greater element from stack respect giving number
		private static void removeGreater(int value) throws IOException {
			
			
			
			Stack  temp_stack=new Stack();
			while (!stack.isEmpty()) {
				int stack_value=stack.pop();
				if (value>=stack_value) {
					temp_stack.push(stack_value);
				}			
			}
			while (!temp_stack.isEmpty()) {
				
				stack.push(temp_stack.pop());
			}
			
			fileWriter.write("After removeGreater "+value +":\n");
			print();

		}

	
		////Print stack elements.
		private static void print() throws IOException {
			
			Stack tmp=new  Stack();
		
			while (!stack.isEmpty()) {
				
				int value=stack.pop();
				tmp.push(value);
				fileWriter.write(value +" ");
			}
			while (!tmp.isEmpty()) {
				stack.push(tmp.pop());
			}
			fileWriter.write("\n");
		}
		//Close file then print final stack element
		public static void Close() throws IOException {
			fileWriter.close();
			FileWriter fileWriter=new FileWriter("stack.txt");
			while (!stack.isEmpty()) {
				fileWriter.write(Integer.toString(stack.pop())+" ");
			}
			fileWriter.close();	
		}
		
		

	}


