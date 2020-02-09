
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

 class MST{

    public static int SIZE;
    
    PrintWriter  writer;
    
    
    
    private  static   int  mycount=0;
   
    int minKey(Double key[], Boolean mstSet[])
    {
        Double min = (double) Integer.MIN_VALUE;
        int min_index=-1;
        
        for (int v = 0; v < SIZE; v++){
        	
            if (mstSet[v] == false && key[v] > min)
            {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }
 
    void print(int parent[], int n, Double graph[][],ArrayList<String[]>  data,int cluster)
    {
    	
    	
    	String[][]  array=new   String[SIZE][2]; 
    	
    	int total=(int) Math.floor((SIZE-1)/cluster);
    	
     
        for (int i = 1; i <SIZE; i++){
          
            array[i-1][0]=data.get(parent[i])[0];
            array[i-1][1]=data.get(i)[0];
            
        }
        
        String  root=findhead(array[0][0], array);

		builTree(array, total, root,new ArrayList<String>());

    }
    private ArrayList<String>  builTree(String[][]   array,int cluster,String  head,ArrayList<String>  tree){
    	
    	if(mycount>cluster){
    		writer.println();
    		mycount=0;
    	}
    	
    	tree.add(head);
    	writer.print(head+" ");
    	mycount++;

    	ArrayList<String>  childs=FindChild(array, head);
    	if (childs.size()==0) {

    		return tree;
		}
    	
    	for (int i = 0; i < childs.size(); i++) {
			
    		tree= builTree(array, cluster, childs.get(i), tree);
			
		}
    	if (mycount>cluster) {
			return tree;
		}
    	return tree;
    	
		}
    
    private  String findhead(String  ahh,String[][]  array){
    	
    		
    	for (int i = 0; i < array.length-1; i++) {
			if (array[i][1].equals(ahh)) {
				
				return findhead(array[i][0], array);
			}
		}
    	
    	return ahh;
    	
    }
    
 
     private   ArrayList<String>  FindChild(String[][]  array,String  parent){
    	 
    	 ArrayList<String>  childs=new ArrayList<>();
    	 for(int i=0; i<array.length-1;i++){
    		 
    		 if(array[i][0].equals(parent)){
    			 
    			childs.add(array[i][1]);
    		 }
    	 }
    	 
		return childs;
		}
   
    void Span(Double graph[][] ,ArrayList<String[]> data ,String  outputname,int cluster,int size )
    {
    	
    	
    	SIZE=size;
    	
    	try {
			writer=new PrintWriter(outputname);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
       
        int roots[] = new int[SIZE];
 
     
        Double key[] = new Double [SIZE];
 
     
        Boolean mstSet[] = new Boolean[SIZE];
 
       
        for (int i = 0; i < SIZE; i++)
        {
            key[i] =(double) Integer.MIN_VALUE;
            mstSet[i] = false;
        }
 
      
        key[0] = (double) 0;    
                       
        roots[0] = -1; 
 
        for (int count = 0; count < SIZE-1; count++)
        {
          
            int minkey = minKey(key, mstSet);
      
            mstSet[minkey] = true;
 
 
            for (int i = 0; i < SIZE; i++)
 
      
                if (graph[minkey][i]!=0 && mstSet[i] == false &&
                    graph[minkey][i] >  key[i])
                {
                	roots[i]  = minkey;
                    key[i] = graph[minkey][i];
                }
        }
         print(roots,SIZE, graph,  data,cluster);
        
        writer.close();
    }
    
}