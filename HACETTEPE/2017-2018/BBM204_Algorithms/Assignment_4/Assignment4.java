import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Assignment4 {

	
	
	
	
	
	//private static  PrintWriter   output ;
	public static ArrayList<String>   names=new ArrayList<>();
	private  static  ArrayList<Edge>   myedges=new  ArrayList<>();
	
	public  static  ArrayList<String>   active=new  ArrayList<>();
	
	private  static   Graph g;
	public static void main(String[] args) {
	
		
		
		 BuildRail(args[0]);
		 
		 SetDistance(args[1]) ;
		
		 g = new Graph(myedges);
			
		
		 
		 GetCommands(args[2]);
	
			/*    
		 
	 for (String string : names) {
			System.out.println("xfnx"+string);
		}
	
		 * 
		 * 
			System.out.println("--------------------");
		 for (int i = 0; i < g.getNodes().size(); i++) {
			 System.out.println(names.get(i));
			
		}*/
		 
		   
	}
	
	
	private static void GetCommands(String commands) {
		try (BufferedReader br = new BufferedReader(new FileReader(commands))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    	CommandProgress(line);
		
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
		
	}

	private static void CommandProgress(String line) {
		
		
		String[]  Commands=line.split(" ");
		switch (Commands[0]) {
			
			case "MAINTAIN":
				
				MAINTAIN(Commands);
				
				break;
			case "SERVICE":
				
				
				
				SERVICE(Commands);
				
				break;
			case "BREAK":
				
				
				
				BREAK(Commands);
				
			break;
			case "REPAIR":
				
				
				
				REPAIR(Commands);
				
			break;
			case "ADD":
				
				
				
				ADD(Commands);
				
			break;
			case "LINK":
				
				
				
				LINK(line);
				
			break;
			
			case "ROUTE":
				
				
				
				//System.out.println("==============================================");
				
				ROUTE(line);
				
				
				
				//for (Edge eed : g.getEdges()) {
			//		System.out.println(eed.getBroke());
			//	}
				break;
			case "LISTROUTESFROM":
				
				
				
				LISTROUTESFROM(Commands);
			
			break;
			case "LISTMAINTAINS":
				
				
				
				LISTMAINTAINS(Commands[0]);
				
			break;
			case "LISTACTIVERAILS":
				
				
				
				LISTACTIVERAILS(Commands[0]);
				
			break;
			case "LISTBROKENRAILS":
				
				
				
				
				LISTBROKENRAILS(Commands[0]);
			break;
			case "LISTCROSSTIMES":
				
				
				
				
				LISTCROSSTIMES();
			break;
			case "TOTALNUMBEROFJUNCTIONS":
				
				
				
				
				TOTALNUMBEROFJUNCTIONS();
				
			break;
			case "TOTALNUMBEROFRAILS":
				
				
				
				
				TOTALNUMBEROFRAIL();
				
			break;
			default:
				
				
				Neyapoyonkades(line);
			
			
		}
		
	}



	private static void LISTCROSSTIMES() {
		
		
		System.out.println("COMMAND IN PROCESS >> LISTCROSSTIMES");
		System.out.print("  # of cross times: ");
		for (int i = 0; i < g.getNodes().size(); i++) {
			
			
			if (g.getNodes().get(i).getTotalpasses()>0) {
				
				
				System.out.print(names.get(i)+":"+g.getNodes().get(i).getTotalpasses()+" ");
				/**
				 * 
				 * 
				 * 
				 * COMMAND IN PROCESS >> LISTCROSSTIMES
# of cross times: A:1 C:1 D:1
Command "LISTCROSSTIMES" has been executed successfully!*/
				
				
				
			}
		
		}
		System.out.println();
		System.out.println("  Command " +"\" "+"LISTCROSSTIMES" +"\"  has been executed successfully!");
	}


	private static void ROUTE(String line) {
		
		
		
		
		
		//System.out.println("  ======================== "  +g.getEdges().size());
		String[]  commands=line.split(" ");
		System.out.println("COMMAND IN PROCESS >> "+line);
		String[]  in=commands[1].split(">");
		
		int toindex=findindex(in[1]);
		//System.out.println("index "+names.get(findindex(in[0])));	
		g.calculateShortestDistances(findindex(in[0]));
		
		
		
		
		ArrayList<String>  tr=new ArrayList<>();
		tr.add(in[0]);
		for (String string : g.getNodes().get(toindex).getTrack()) {
			tr.add(string);
		}
		if (g.getNodes().get(toindex).getTrack().size()>0) {
			System.out.println("  Time (in min): "+  findtime(g.distance(findindex(in[1]) ) ,Float.parseFloat(commands[2])));
			
			System.out.println("  Total # of switch changes: " +numberswi(tr));
			System.out.print("  Route from "+ in[0]+" to "+ in[1]+": "+in[0] +" ");
			
		}else{
			
			System.out.print("  No route from " + in[0]+ " to "+in[1] +" found currently!");
			
			
		}
		
		for (int i = 0; i < g.getNodes().get(toindex).getTrack().size(); i++) {
			
			System.out.print(g.getNodes().get(toindex).getTrack().get(i) +" " );
			int  index=findindex(g.getNodes().get(toindex).getTrack().get(i));
			
			for (int j = 0; j <g.getNodes().size(); j++) {
				
				if (j==index) {
					g.getNodes().get(j).incerasetotalpassesd();
				}
			}
			//System.out.println("trackk  " +g.getNodes().get(toindex).getTrack().get(i)  +"  new ssc "+g.getEdges().size());	
		}
		
		
		
		System.out.println();
		
		
		
		
		
		for (int i = 0; i < g.getNodes().size(); i++) {
			g.getNodes().get(i).setVisited(false);
		}
		
		
		
		
		///g.printResult();
		// g.printResult(); 
		
		System.out.println("  Command " +"\" "+line+"\"  has been executed successfully!");
	}


	private static String numberswi(ArrayList<String> track) {
		
		int number=0;
		
		String  name=null;
		for (int j = 0; j < track.size()-1; j++) {
			name=track.get(j)+track.get(j+1);
			for (int i = 0; i < active.size(); i++) {
				if (name.equals(active.get(i))) {
					number++;
					//System.out.println("bububu  "+name);
				}
				
			}
			
		}
		
		return Integer.toString(track.size()-number);
	}


	private static String findtime(float distance,float  vel) {
		
		float  min=(distance/vel)*60;
		
		return Float.toString(min);
	}


	private static void Neyapoyonkades(String commands) {
		/*
		 * 
		 * COMMAND IN PROCESS >> DUMMYCOMMAND
Unrecognized command "DUMMYCOMMAND"!
Page 9*/
		System.out.println("COMMAND IN PROCESS >> "+commands);
		
		System.out.println("  Unrecognized command " +"\" "+commands +"\"!");
	}


	private static void TOTALNUMBEROFRAIL() {
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS");
		System.out.println("  Total # of rails: "+(g.getEdges().size()));
		System.out.println("  Command " +"\" "+"TOTALNUMBEROFRAILS" +"\"  has been executed successfully!");
	}


	private static void TOTALNUMBEROFJUNCTIONS() {


			/*
			 * 
			 * COMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS
			Total # of junctions: 4
			Command "TOTALNUMBEROFJUNCTIONS" has been executed successfully!*/
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS");
		System.out.println("  Total # of junctions: "+(g.getNodes().size()));
		System.out.println("  Command " +"\" "+"TOTALNUMBEROFJUNCTIONS" +"\"  has been executed successfully!");
	}


	private static void LISTBROKENRAILS(String string) {
		
		ArrayList<String>  broke=new ArrayList<>();
 		for (int i = 0; i < g.getEdges().size(); i++) {
			 
			
 			if (g.getEdges().get(i).getBroke()) {
 				broke.add(names.get(g.getEdges().get(i).getFromNodeIndex())+">"+names.get(g.getEdges().get(i).getToNodeIndex()));
			}
			
			
		}
 		bubblesort(broke);
 		System.out.println("COMMAND IN PROCESS >> LISTBROKENRAILS");
 		System.out.print("  Broken rails: ");
 		for (String bro : broke) {
 			System.out.print(bro +" ");
		}
 		System.out.println();
 		System.out.println("  Command " +"\" "+"LISTBROKENRAILS" +"\"  has been executed successfully!");
 		/*
 		 * COMMAND IN PROCESS >> LISTBROKENRAILS
Broken rails: A>B
Command "LISTBROKENRAILS" has been executed successfully!/
 		 */
		
	}
	

	private static void LISTACTIVERAILS(String string) {
		
		
		
		/*
		 * 
		 * COMMAND IN PROCESS >> LISTACTIVERAILS
Active Rails: A>C B>A C>A D>C
Command "LISTACTIVERAILS" has been executed successfully!*/
		System.out.println("COMMAND IN PROCESS >> LISTACTIVERAILS");
		System.out.print("  Active Rails: ");
		for (int i = 0; i < g.getEdges().size(); i++) {
				
			if (g.getEdges().get(i).getswi()) {
				System.out.print(names.get(g.getEdges().get(i).getFromNodeIndex()) +">"+names.get(g.getEdges().get(i).getToNodeIndex())+"  " );
			}
		}
		System.out.println();
		System.out.println("  Command " +"\" "+"LISTACTIVERAILS" +"\"  has been executed successfully!");
	}


	private static void LISTMAINTAINS(String commands) {
		
		ArrayList<String>   main=new ArrayList<>();
		for (int i=0;  i<g.getNodes().size();  i++ ) {
			
			if (g.getNodes().get(i).isMaintance()) {
				main.add(names.get(i));
				//System.out.println("  maintance ");
			}
		}
		
		bubblesort(main);
		
		System.out.println("COMMAND IN PROCESS >> " +commands);
		System.out.print("  Intersections under maintenance: ");
		
		for (String string : main) {
			System.out.print(string+" ");
		}
		System.out.println();
		System.out.println("   Command " +"\" "+commands +"\"  has been executed successfully!");
/*
 * 
 * Intersections under maintenance: A B
Command "LISTMAINTAINS" has been executed successfully!*/
		
	}


	private static void LINK(String commands) {

    	String[]  inputs=commands.split(":");
    	String[]  root=inputs[0].split(" ");
		
		String[]   targets=inputs[1].split(">")[0].split(",");
		
		
		String  cango=inputs[1].split(">")[1];
		
		for (int i = 0; i < targets.length; i++) {
			
			String[]   info=targets[i].split("-");
			
			//System.out.println("new edege " +root[1] +"d "+findindex(root[1]) +" "+findindex(info[0]));
			
			Edge edge=new Edge(findindex(root[1]), findindex(info[0]));
			Edge opp=new Edge( findindex(info[0]),findindex(root[1]));
			
			edge.setLenght(Float.parseFloat(info[1]));
			opp.setLenght(Float.parseFloat(info[1]));
			if (cango.equals(targets[i])) {
				edge.setSwi(true);
			}
			//edges.add(edge);
			//edges.add(opp);
			g.getEdges().add(edge);
			g.getEdges().add(opp);
			
		
		}
		//;
		/*
		 * 
		 * COMMAND IN PROCESS >> LINK D:A-12,B-7,C-4>C
Command "LINK D:A-12,B-7,C-4>C" has been executed successfully!*/
		System.out.println("COMMAND IN PROCESS >> " +commands);
		System.out.println("   Command " +"\" "+commands +"\"  has been executed successfully!");
	}


	private static void ADD(String[] commands) {
		names.add(commands[1]);
		
		
		g.getNodes().add(new Node());
		//g.getNodes().add(new Node());
	//	System.out.println("ADD "+commands[1]);
		System.out.println("COMMAND IN PROCESS >> ADD " +commands[1]);
		System.out.println("   Command " +"\"ADD  "+commands[1] +"\"  has been executed successfully!");
		
	}


	private static void REPAIR(String[] commands) {
		
		String[]  in=commands[1].split(">");
		
		
		int  from=findindex(in[0]);
		int to=findindex(in[1]);
		//System.out.println("cödvdv"+Arrays.toString(in)  +" from "+from+" to "+to);
		for (int i =g.getEdges().size()-1 ; i >=0; i--) {
			
			
			//System.out.println("metnn "+edges.get(i).getFromNodeIndex());
			
			if (g.getEdges().get(i).getFromNodeIndex()==from  && g.getEdges().get(i).getToNodeIndex()==to) {
				
				//System.out.println("REPAIR "+names.get(from) +" ------- "+names.get(to) );
				g.getEdges().get(i).setBroke(false);
			
			}
		}
		System.out.println("COMMAND IN PROCESS >> REPAIR " +names.get(from) +">"+names.get(to));
		System.out.println("   Command " +"\"REPAIR  "+names.get(from) +">"+names.get(to)+"\"  has been executed successfully!");
		
		
	}


	private static void BREAK(String[] commands) {
		
		String[]  in=commands[1].split(">");
		//System.out.println("-------------------------------------------------------------------------------- "  );
		int  from=findindex(in[0]);
		int to=findindex(in[1]);

		for (int i =g.getEdges().size()-1 ; i >=0; i--) {
			
			
		
			
			if (g.getEdges().get(i).getFromNodeIndex()==from  &&  g.getEdges().get(i).getToNodeIndex()==to) {
				
			
				
				g.getEdges().get(i).setBroke(true);
			}
		}
		
		//g.uptade(myedges);
			//		System.out.println("-------------------------------------------------------------------------------- "  );
	
		System.out.println("COMMAND IN PROCESS >> BREAK " +names.get(from) +">"+names.get(to));
		System.out.println("   Command " +"\"BREAK  "+names.get(from) +">"+names.get(to)+"\"  has been executed successfully!");
		
		
	}
	
	private static void SERVICE(String[] commands) {

		int index=findindex(commands[1]);

		g.getNodes().get(index).setMaintance(true);
			
		for (int i = 0; i < g.getNodes().size(); i++) {
			
			if (i==findindex(commands[1])) {
				 g.getNodes().get(i).setMaintance(false);
				 System.out.println("service  ==>> "+names.get(i));
			}
		}
		
		
		
		
		System.out.println("COMMAND IN PROCESS >> SERVICE "+commands[1]);
		System.out.println("   Command " +"\"SERVICE  "+commands[1]+"\"  has been executed successfully!");
		
	}

	private static void MAINTAIN(String[] commands) {
		 	/*
		 	 * 
		 	 * COMMAND IN PROCESS >> MAINTAIN A
		Command "MAINTAIN A" has been executed successfully!*/
		
		int index=findindex(commands[1]);

		g.getNodes().get(index).setMaintance(true);
			
		for (int i = 0; i < g.getNodes().size(); i++) {
			
			if (i==findindex(commands[1])) {
				 g.getNodes().get(i).setMaintance(true);
				// System.out.println("Maintence  ==>> "+names.get(i));
			}
		}
		
		
		
		
		System.out.println("COMMAND IN PROCESS >> MAINTAIN "+commands[1]);
		System.out.println("   Command " +"\"MAINTAIN  "+commands[1]+"\"  has been executed successfully!");
		
	}


	private static void LISTROUTESFROM(String[] name) {
		
		
		//System.out.println(" name  "+name);
		
		ArrayList<String>  sort=new ArrayList<>();
		int index=findindex(name[1]);
		
		System.out.println("COMMAND IN PROCESS >> LISTROUTESFROM " +name[1]);
		System.out.print("   Routes from " +name[1]+":  ");
		for (int i = 0; i < g.getEdges().size(); i++) {
			if (index==g.getEdges().get(i).getFromNodeIndex()) {
				
				
				
				sort.add(names.get(g.getEdges().get(i).getToNodeIndex()));
				/*
				 * 
				 * COMMAND IN PROCESS >> LISTROUTESFROM KARAKOY
	Routes from KARAKOY: KABATAS SIRKECI TAKSIM TUNEL_SISHANE
	Command "LISTROUTESFROM KARAKOY"  has been executed successfully!
				 * */
				
				//System.out.println(names.get(edges.get(i).getToNodeIndex()));
				
			}
			
		}
		bubblesort(sort);
		for (String string : sort) {
			System.out.print(string +"   ");
		}
		System.out.println();
		System.out.println("   Command " +"\"LISTROUTESFROM  "+name[1]+"\"  has been executed successfully!");
	}

	private static void bubblesort(ArrayList<String>  values){

	    int n = values.size();  
	 
         for(int i=0; i < n; i++){  
             for(int j=1; j < (n-i); j++){  
                    if(values.get(j-1).compareTo(values.get(j)) > 0){  
                               
                    	 ///////exchange index of float
                           String temp = values.get(j-1);  
                                
                           values.set(j-1, values.get(j));
                           
                           values.set(j, temp);
                           
                           ///////exchange index of line
                          
                           
                    }  
                          
               }  
         }	
	}
	private static void SetDistance(String secondfile) {
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(secondfile))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    	
		    	String[]   info=line.split(" ");
		    	String[]	stopsnames=info[0].split("-");
		    	
		    	
		    	for (Edge edge : myedges) {
					
		    		
		    		if (isthis(edge.getFromNodeIndex(),edge.getToNodeIndex(),stopsnames)) {
						edge.setLenght(Float.parseFloat(info[1]));
					}
				}
		    	
		    	
	    	
   
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
		}
	}
		
	

private static boolean isthis(int fromNodeIndex, int toNodeIndex ,String[]  stopsnames) {
	
	return ((toNodeIndex==findindex(stopsnames[0]))  &&  fromNodeIndex==findindex(stopsnames[1]))  ||   (toNodeIndex==findindex(stopsnames[1])  &&  fromNodeIndex==findindex(stopsnames[0]));
}




private static void BuildRail(String firstfile) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(firstfile))) {
		    String line;
		    while ((line = br.readLine()) != null ) {
		    	
		    	
		    
		    	String[]  inputs=line.split(":");
				
				String[]   targets=inputs[1].split(">")[0].split(",");
				
				String  cango=inputs[1].split(">")[1];
				
				
				
				
				
				///
				if (isexist(inputs[0])) {
					
				}else{
					names.add(inputs[0]);
				}
				
				for (int i = 0; i < targets.length; i++) {
					
					if (isexist(targets[i])) {
						
						
						
					}else{
						names.add(targets[i]);
					}
					
					
					
				}
				///////////////////////////set
				for (int i = 0; i < targets.length; i++) {
					
					
					Edge edge=new Edge(findindex(inputs[0]), findindex(targets[i]));

					if (cango.equals(targets[i])) {
						edge.setSwi(true);
					}
					myedges.add(edge);
				
				}
			
		    }
		}catch(Exception e){
			System.out.println("Are you sure you have this file");
			e.printStackTrace();
			
		}
	}

private  static int  findindex(String  name){
	
	
	for (int i = 0; i < names.size(); i++) {
		
		if (name.equals(names.get(i))) {
			return i;
		}
		
	}
	return -1;
}

private static boolean isexist(String name) {
	for (String str : names) {
		
		if (str.equals(name)) {
			return true;
		}
		
		
	}
	
	return false;
	
	
}
	
	
	
}
