import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;





public class Assignment3 {

	
	
	
	
	
	private static ArrayList<String[]>  data=new ArrayList<>();
	
	private static ArrayList<String>  mypairslist=new ArrayList<>();
	
	private static  Double[][]   graph;
	
	
	public static void main(String[] args) {
	
		
		
		ReadData(args[0],args[1]);
		graph=new Double[data.size()][data.size()];
		
		
		for(int i=0;i<data.size();i++){
			
			
			for(int a=0;a<data.size();a++){
				
				
				if(data.get(i).equals(data.get(a))){
					graph[i][a]=0.0;
				}else{
					graph[i][a]=findSimilariti(data.get(i), data.get(a));
				}
			
			}

		}
		 MST mst = new MST();
		
		 
		 mst.Span(graph,data,args[2],Integer.parseInt(args[3])  ,data.size());
		 

		 
	}

	private static void ReadData(String  worddec,String  wordpairs) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(wordpairs))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    
		    	String[]   word=line.split("-");
		    	
		    	if(word.length>1){
		    	 	mypairslist.add(word[0]);
			    	mypairslist.add(word[1]);
			    		
		    	}
		   
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(worddec))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    	String[]   words=line.split(" ");
		    	
		    	words[0]=words[0].substring(1,words[0].length()-1);
		    	
		    	for(int i=0;i<mypairslist.size();i++){
		    			if(mypairslist.get(i).equals(words[0])  &&  !ishave(mypairslist.get(i))){
		    				
		    				data.add(words);
		    		}	
		    	}
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
		
		
		
	}
	private static boolean ishave(String  pair) {
		
		
		
		for(String[]  words : data){
			
			if(pair.equals(words[0])){
				return true;
			}
			
		}
		return false;
	}
	private static  Double  findSimilariti(String[]   vector0 ,String[]  vector1){
		
		
		Double dot = 0.0;
		Double  lenght0 = 0.0;
		Double  lenght1 = 0.0;
		for(int i=1; i<vector0.length;i++){
			
			
			Double  firstvectoreleman=Double.parseDouble(vector0[i]);
			Double  secondvectoreleman=Double.parseDouble(vector1[i]);
			dot=dot+firstvectoreleman*secondvectoreleman;
			lenght0=lenght0+(Math.pow(firstvectoreleman, 2));
			lenght1=lenght1+(Math.pow(secondvectoreleman, 2));
		}
		
		return dot/(Math.sqrt(lenght0)*Math.sqrt(lenght1));
	}
	
	
	


}
