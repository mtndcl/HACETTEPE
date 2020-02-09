import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Assignment2 {
	
	
	
	
	private static  MyVariable myVariable=new MyVariable();
	
	private   static  BinarySearchTree  tree=new BinarySearchTree();
	
	private   static  HeapTree  heaptree=new HeapTree();
	
	
	private   static  Chance  second=new Chance();
	
	private static  String[]  myframe;
	
	
	
	
	
	public static void main(String[] args) {
		  Long   start= System.currentTimeMillis();  
		
		//BinarySearchTree tree = new BinarySearchTree();

		PrintWriter writer=null;
		try {
			  writer=new PrintWriter("output.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    	String[]  words=line.split(" ");
		    	
		    	GotoImplementSystem(words,writer);
		    	
		   
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
		
		
		writer.println(myVariable.getNumberfault());
		long after=System.currentTimeMillis();
			
				
		writer.println("Elapsed Time  "+(after - start)+ "  millisecond(s)");

		writer.close();
		
		

	}

	
	
	private static void GotoImplementSystem(String[] words,PrintWriter  writer) {
		
		
		switch (words[0]) {
			case "SetMemory":
				
				
				
				myVariable.setMemory(Integer.parseInt(words[1]));
				writer.println("Memory "+words[1]);
				myframe=new String[myVariable.getMemory()];
				second.setMaxsize(Integer.parseInt(words[1]));
				
				heaptree.setMaxsizeOK(Integer.parseInt(words[1]));
				
				break;
			case "setReplacement":
				myVariable.setReplacementtype(words[1]);
				
				writesecondcolon(words[1],writer);
				break;
			case "setSearchStructure":
				myVariable.setSearchStructuretype(words[1]);
				writethirdcolon(words[1],writer);
				//System.out.println(words[1]);
				break;
			case "Read":
				
				ReadData(words[1],writer);
				break;
			default:
				break;
		}
		
		
	}


	


	private static void ReadData(String word,PrintWriter writer) {
		
			
			
			switch (myVariable.getReplacementtype()) {
				case "FIFO":
			
						
					AddelemtToSearch(word);
					
					
					
					if(!tree.have){
						
						
						
						
						
						
						
						if(myVariable.getCurretindex()>=myVariable.getMemory()){
							myVariable.setCurretindexOK(0);
						}
						
						DeleteSearchElement();
						
						myframe[myVariable.getCurretindex()]=word;
						
						myVariable.setCurretindex(1);
						
						
						if(myVariable.getCurrentsize()<myVariable.getMemory()){
							myVariable.setCurrentsize(1);
						}
						
						
						myVariable.setNumberfault(1);
					//	System.out.println("size "+myVariable.getCurrentsize());
						writepagefault(writer);
						
					}else{
						writer.print("\t\t");
						for(int i=0;i<myVariable.getCurrentsize();i++){
							
							writer.print(myframe[i]+" ");
						}
						writer.println();
					}
					
					//printF();
			
					
					break;
				case  "PriorityQueue":
						
					AddelemtToSearch(word);
					
					
					if(!tree.have){
						
						
						heaptree.insert(word);
					
						if(myVariable.getCurrentsize()<myVariable.getMemory()){
							myVariable.setCurrentsize(1);
						}
						
						DeleteSearchElementPRPORTY(heaptree.del);
						writer.print("Page Fault\t");
						
						myVariable.setNumberfault(1);
						for(int i=0;i<myVariable.getCurrentsize();i++){
							
							writer.print(heaptree.array[i]+" ");
							
						}
						writer.println();
					
						
					}else{
						writer.print("\t\t");
						for(int i=0;i<myVariable.getCurrentsize();i++){
							
							writer.print(heaptree.array[i]+" ");
							
						}
						writer.println();
					}
			
					break;
				case  "SecondChance":
					
					AddelemtToSearch(word);
					
					if(!tree.have){
				
						
						
						if(second.currentsize==myVariable.getMemory()){
						
							
							
							String DEL=second.selectdel();
							//System.out.println("DELETE ==>>> " +DEL);
							tree.deleteKey(DEL);
						}
				
						second.insert(word,writer);
					
						myVariable.setNumberfault(1);
						
			
						
					}else{
						second.giveseconchance(word);	
						
						 writer.print("\t\t");
						second.print(writer);
						
					}
					
						
						
					
			
					break;
					
		}
	}

	


	

	private static void DeleteSearchElementPRPORTY(String word) {
		
		
		if(myVariable.getCurrentsize()==myVariable.getMemory()){

				tree.deleteKey(word);
			
		}
		
	}





	private static void AddelemtToSearch(String word) {
		
			tree.insert(word);
	
	}


	


	private static void DeleteSearchElement() {
		
		
			if(myVariable.getCurrentsize()==myVariable.getMemory()){
				//System.out.println("size "+myVariable.getCurretindex());
				tree.deleteKey(myframe[myVariable.getCurretindex()]);
			}
		
		
	}


	private static void writepagefault(PrintWriter writer) {
		
		
		writer.print("Page Fault\t");
		for(int i=0;i<myVariable.getCurrentsize();i++){
			
			writer.print(myframe[i]+" ");
		}
		writer.println();
		
	}
	private static void writethirdcolon(String SearchStructuretype, PrintWriter writer) {
		
			writer.println("Binary Search Tree");
			
			switch (myVariable.getReplacementtype()) {
			case "FIFO":
				writer.println();
				break;

			default:
				break;
			}
	}

	private static void writesecondcolon(String Replacementtype,  PrintWriter writer) {
		switch (Replacementtype) {
		case "FIFO":
			writer.println("FIFO Page Replacement");
			break;
		case "PriorityQueue":
			writer.println("PriorityQueue Page Replacement");
			break;
		case "SecondChance":
			writer.println("SecondChance Page Replacement");
			break;

		default:
			break;
		}
		
	}
	

}
