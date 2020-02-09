
public class HeapTree {

	 class Node {
	    	String key;
	        Node left, right;
	 
	        public Node(String key) {
	            this.key = key;
	            left = right = null;
	        }
	    }

	 String  del="a";
	 int replacesize;
	    int maxsize;
	    int curretsize;
	    String[]   array;
	    HeapTree() { 
	        curretsize=0; 
	    }
	    
	    public void setMaxsizeOK(int size) {
			this.maxsize = size;
			array=new String[size];
			
		}
		void  insert(String  key){
	    	
	    	if(maxsize==curretsize){
	    		
	    		setmaxindex();
	    			
	    		array[replacesize]=key;
	    		
	    		return;
	    	}
	    	
	  
	    	curretsize++;
	        int i = curretsize - 1;
	        array[i] = key;
	
	        while (i != 0 && array[root(i)].compareTo(array[i]) > 0)
	        {
	           swap(array[i], array[root(i)]);
	           i = root(i);
	        }
	    	
	    }
	     



		  void  setmaxindex() {
			   del=array[0];
	    	 for(int i=0;i<array.length;i++){
	    		 
	    		 if(del.compareTo(array[i])<=0){
	    			
	    			 replacesize=i;
	    			 del=array[i];
	    			
	    		 }
	    	 }

		}

		void swap(String x, String y)
	    {
	        String temp = x;
	        x = y;
	        y = temp;
	    }
	    
	    int root(int i) { return (i-1)/2; }
	    
	    
	    
}
