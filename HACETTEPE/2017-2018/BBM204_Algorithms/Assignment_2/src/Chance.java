import java.io.PrintWriter;

public class Chance {

	
	 class Node {
	    	String key;
	        Boolean secondchance;
	 
	       

			public Node(String item) {
	            key = item;
	            secondchance=false;
	        }
			
			
				
	    }

	 int index;
	 int maxsize;
	 int currentsize;
	 
	 
	Node[]   array;
	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
		array=new Node[maxsize];
	}

	String text="";
	Boolean print=false;
	String  del=null;
	Chance(){
		index=0;
		currentsize=0;
		
	}
	 void insert(String word,PrintWriter writer) {
		if(currentsize<maxsize){
			
			Node  newnode=new Node(word);
			array[index]=newnode;
			index++;
			if(index==maxsize){
				 index=0;
			}
			
				 currentsize++;
			
		}else{
			UpdateArray(word);
			
			
		}
		 writer.print("Page Fault\t");
		print(writer);
	
	}
	 void UpdateArray(String word) {
		
		 
		 
			 if(array[index].secondchance){
				 findeNochance();
			 }	 
			array[index].secondchance=false;
			array[index].key=word;
				 
			index++;
			if(index==maxsize){
				index=0;
			}

	}
	 void findeNochance() {
		 if(array[index].secondchance){
			 
			 array[index].secondchance=false;
			 print=true;
			 text=text+array[index].key+ " ";
			 index++;
			if(index==maxsize){
				index=0;
			}
	
		 }else{
			 return;
		 }
		 findeNochance();
		
	}
	void giveseconchance(String word) {
		 for(int i=0;i<currentsize;i++){ 
			 if(word.equals(array[i].key)){ 
				 array[i].secondchance=true;
			 }
		 }
	}
	String  selectdel(){
		
		if(currentsize==maxsize){
			for(int i=index ;i<maxsize;i++){
				
				if(!array[i].secondchance){
					return array[i].key;
				}
			}
			for(int i=0 ;i<index;i++){
				
				if(!array[i].secondchance){
					return array[i].key;
				}
			}
			
			return  array[index].key;

		}
		
		return del;

	}
	 void print(PrintWriter writer) {
		
			for(int i=0;i<currentsize;i++){
				 
				writer.print(array[i].key+" ");
			}
			
			if(print){
				print=false;
				writer.print("  Second chance  ");
				
				writer.print(" "+ text);
				text="";
		
			}
			writer.println();
		
	}
	 
	
	 
	
	
}
