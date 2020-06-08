import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Queue_Implemention {
	
	private static Queue queue=new Queue();

	private static FileWriter fileWriter;
	
	
	///Read queue.txt file then enqueue to value to queue
	public static void readqueueFile(String filename) throws IOException {
		fileWriter=new FileWriter("queueOut.txt");
		try {

	           File f = new File(filename);
	          
	           BufferedReader b = new BufferedReader(new FileReader(f));
	           String readLine =null;
	        
	           while ((readLine = b.readLine()) != null) {
	        	   String[]  data=readLine.split(" ");
	        	   for (String value : data) {
	        		   queue.enqueue(Integer.parseInt(value));
	        	   }

	           }
	     }catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	///Look for rule
	public static void HandleQueue(String[] data) throws IOException {
		
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
	
	///calculate distinct element number in queue
	private static void distinctElements() throws IOException {
		
		HashSet<Integer>  set=new HashSet<Integer>();
		Queue tmp=new  Queue();
		while (!queue.isEmpty()) {
			int value=queue.dequeue();
			tmp.enqueue(value);
			set.add(value);
		}
		
		queue=tmp;
		
		fileWriter.write("After distinctElements:\n");
		fileWriter.write("Total distinct element="+set.size()+"\n");
		
		
	}
	
	//Sort queue about values
	private static void sortElements() throws IOException {
		
		Stack temp = new Stack(); 
        while(!queue.isEmpty()) 
        { 
          
            int tmp = queue.dequeue(); 
            while(!temp.isEmpty() && temp.top()< tmp) 
            { 
            	queue.enqueue(temp.pop()); 
            }           
            temp.push(tmp); 
        }
        while (!temp.isEmpty()) {
			
			queue.enqueue(temp.pop());
		}
        fileWriter.write("After sortElements:\n");
        print();
		
	}
	
	//Reverse desired queue size 
	private static void Reverse(int value) throws IOException {
		
		Stack tmp=new Stack();
		Queue tmp2=new Queue();
		for (int i = 0; i < value; i++) {
			tmp.push(queue.dequeue());
		}
		while (!queue.isEmpty()) {
			tmp2.enqueue(queue.dequeue());
		}
		while (!tmp.isEmpty()) {
			queue.enqueue(tmp.pop());
			
		}
		while (!tmp2.isEmpty()) {
			queue.enqueue(tmp2.dequeue());
			
		}
		
		fileWriter.write("After reverse "+value+":\n");
		print();
		
	}
	
	///Enqueue new element to queue or dequeue about giving value 
	private static void addOrRemove(int value) throws IOException {
		
		if (value>0) {
			
			Random r=new Random();
			for (int i = 0; i < value; i++) {
				queue.enqueue(r.nextInt(50));
			}
		}else if (value<0) {
			value=Math.abs(value);
			for (int i = 0; i < value; i++) {
				queue.dequeue();
			}
			value=-value;
		}
		
		fileWriter.write("After addOrRemove "+value+":\n");
		print();
		
	}
	///Calculate distance for queue
	private static void calculateDistance() throws IOException {
		int sum=0;
		ArrayList<Integer>  popped=new ArrayList<Integer>();
		
 		while (!queue.isEmpty()) {
			int value=queue.dequeue();
			
			Queue  temp_queue=queue; 
			while (!temp_queue.isEmpty()) {
				int next_value=temp_queue.dequeue();
				sum+=Math.abs(value-next_value);
				popped.add(next_value);
			}
			for (int i = popped.size()-1; i >=0; i--) {
				queue.enqueue(popped.get(i));
			}
			popped.clear();

		}
 		
 		fileWriter.write("After calculateDistance:\n");
 		fileWriter.write("Total distance="+sum+"\n");
 	
		
	}
	
	//Remove element which ones bigger then giving number
	private static void removeGreater(int value) throws IOException {
		
		Queue temp=new Queue();
		
		while (!queue.isEmpty()) {
			int queue_value=queue.dequeue();
			if (queue_value<value) {
				temp.enqueue(queue_value);
			}
		}
		queue=temp;
		fileWriter.write("After removeGreater "+value+":\n");
		print();
				
		
	}
	
	///print current queue to file
	private static void print() throws IOException {
		
		Queue tmp=new Queue();
		while (!queue.isEmpty()) {
			
			int value=queue.dequeue();
			fileWriter.write(value+" ");
			tmp.enqueue(value);
		}
		queue=tmp;
		fileWriter.write("\n");
	}
	
	//Close printing file and write last queue to queue.txt file
	public static void Close() throws IOException {
		fileWriter.close();
		FileWriter fileWriter=new FileWriter("queue.txt");
		while (!queue.isEmpty()) {
			fileWriter.write(Integer.toString(queue.dequeue())+" ");
		}
		fileWriter.close();	
		
	}
	
}
