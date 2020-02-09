import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;



public class Assignment1 {

	
	
	
	
	public static void main(String[] args) {
		  
		
		
		
		ArrayList<String>  ouicklines=new ArrayList<>();
		ArrayList<String>  bubblelines=new ArrayList<>();
		ArrayList<String>  selectionlines=new ArrayList<>();
		String  filename=args[0];
		
		String  firstline = "some error";
		ArrayList<Float>  ouickselectedcolunm=new ArrayList<>();
		ArrayList<Float>  bubbleselectedcolunm=new ArrayList<>();
		ArrayList<Float>  selectionselectedcolunm=new ArrayList<>();
		int  index=31;
		try{
			index=Integer.parseInt(args[1])-1;
		}catch(Exception  e){
			System.out.println("print a int");
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int rownumber=0;
		    while ((line = br.readLine()) != null ) {
		    	if (rownumber==0) {
		    		firstline=line;
				}else{
					ouicklines.add(line);
					bubblelines.add(line);
					selectionlines.add(line);
				     String[]  split=line.split(",");
				     ouickselectedcolunm.add(getFloat(split[index]));
				     bubbleselectedcolunm.add(getFloat(split[index]));
				     selectionselectedcolunm.add(getFloat(split[index]));
				}
		    	rownumber++;
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
	
		///Long startTime =   System.currentTimeMillis();
	
		if (args[2].equals("T")) {
			//startTime =   System.currentTimeMillis();
			Quicksort(ouickselectedcolunm,ouicklines);
			//System.out.println("Quick Sorted "+(System.currentTimeMillis()-startTime));
			WriteOutput(filename,firstline,  ouicklines);
			
		}else if(args[2].equals("F")){
			//startTime =   System.currentTimeMillis();
			Quicksort(ouickselectedcolunm,ouicklines);
			///System.out.println("Quick Sorted "+(System.currentTimeMillis()-startTime));
			//startTime =   System.currentTimeMillis();
			bubblesort(bubbleselectedcolunm,bubblelines);
			//System.out.println("Bubble Sorted "+(System.currentTimeMillis()-startTime));
			//startTime =   System.currentTimeMillis();
			selectionsort(selectionselectedcolunm,selectionlines);
			//System.out.println("Selection Sorted "+(System.currentTimeMillis()-startTime));
			
		}
		
		
		//System.out.println("finished");
	}
	
	

	////Bubble sort algorithm
	
	private static void bubblesort(ArrayList<Float>  values,ArrayList<String>  lines){

	    int n = values.size();  
	 
         for(int i=0; i < n; i++){  
             for(int j=1; j < (n-i); j++){  
                    if(values.get(j-1) > values.get(j)){  
                               
                    	 ///////exchange index of float
                           float temp = values.get(j-1);  
                                
                           values.set(j-1, values.get(j));
                           
                           values.set(j, temp);
                           
                           ///////exchange index of line
                           String te = lines.get(j-1);  
                           
                            lines.set(j-1, lines.get(j));
                               
                            lines.set(j, te);
                           
                    }  
                          
               }  
         }	
	}

	///selection sort algorithm
	
	private static  void selectionsort(ArrayList<Float>  values,ArrayList<String>  lines)
    {
        int n = values.size();
 
        for (int i = 0; i < n-1; i++){
   
            int minindex = i;
            for (int j = i+1; j < n; j++){
                if (values.get(j) < values.get(minindex))
                	minindex = j;
            }
            
            ///////exchange index of Float
            float temp = values.get(minindex);  
            values.set(minindex, values.get(i));
   
            values.set(i, temp);
            
            ///////exchange index of line
            String te = lines.get(minindex);  
            lines.set(minindex, lines.get(i));
   
            lines.set(i, te);
           
        }
    }
	
	///Main of Quick  sort algorithm
	private static void Quicksort(ArrayList<Float> values,ArrayList<String>  lines) {

	       int  number = values.size();
	        sort(0, number - 1,values,lines);
	        
	       
	    }
	////////partition of Quick sort function
	private static void sort(int low, int high,ArrayList<Float>   values,ArrayList<String>  lines) {
	    	 
	      
	        
	        int i = low, j = high;
	
	        float pivot = values.get(low + (high-low)/2);


	        while (i <= j) {
	
	            while (values.get(i) < pivot) {
	                i++;
	            }
	  
	            while (values.get(j) > pivot) {
	                j--;
	            }
	            if (i <= j) {
	                exchange(i, j,values,lines);
	                i++;
	                j--;
	            }
	        }
	        // Recursion
	        if (low < j){
	            sort(low, j,values,lines);
	        }
	        if (i < high){
	            sort(i, high,values,lines);
	        }
	    }

	////////////exchange arraylist index of line and float function
	private static void exchange(int i, int j,ArrayList<Float>  values,ArrayList<String>  lines) {
		 ///////exchange index of float
	        float temp = values.get(i);
	        values.set(i, values.get(j));
	        values.set(j, temp);
	        
	        ///////exchange index of line
	        String  te=lines.get(i);
	        lines.set(i, lines.get(j));
	        lines.set(j, te);
	    }
	    
	 ///////////write output to csv file
	private static void WriteOutput( String filename,String firstline,ArrayList<String>  lines) {
		
			try{
				FileWriter writer = new FileWriter(filename,false);
				  
				writer.append(firstline+'\n');
	     
			     for(int i=0;i<lines.size();i++){

			    	  writer.append(lines.get(i)+'\n');
			    	  
			      }
	   
	        writer.flush();
	        writer.close();
	
			}catch(Exception  e){
				
				System.out.println("some unknown error occured!!!");
				e.printStackTrace();
			}		
		}
	    
	///////String to Float
	private static  float getFloat(String   string){
			return Float.parseFloat(string);
	}
	
	

}
