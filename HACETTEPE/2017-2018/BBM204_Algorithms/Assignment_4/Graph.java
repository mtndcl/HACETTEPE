import java.util.ArrayList;

public class Graph {
	
	
	
	
  private ArrayList<Node> nodes;
  
  private int noOfNodes;
  
  private ArrayList<Edge> edges ;
  
  
  
  
  public Graph(ArrayList<Edge> edge) {
	  
	  
	  
	  
	 
	 this.edges=new ArrayList<>();
	  
	 for (Edge e : edge) {
		 this.edges.add(e);
		 if (e.getswi()) {
			Assignment4.active.add(Assignment4.names.get(e.getFromNodeIndex())+Assignment4.names.get(e.getToNodeIndex()));
		}
	 }
    // create all nodes ready to be updated with the edges
    this.noOfNodes = calculateNoOfNodes(edges);
    this.nodes = new ArrayList<>();
    for (int n = 0; n < this.noOfNodes; n++) {
      this.nodes.add(new Node());
    }
    // add all the edges to the nodes, each edge added to two nodes (to and from)
    
    for (int edgeToAdd = 0; edgeToAdd < edges.size(); edgeToAdd++) {
    	
    	
    	
     this.nodes.get(edges.get(edgeToAdd).getFromNodeIndex()).getEdges().add(edges.get(edgeToAdd));
     
    // System.out.println(Assignment4.names.get(edges.get(edgeToAdd).getFromNodeIndex()   )+  " ====>> " +Assignment4.names.get(edges.get(edgeToAdd).getToNodeIndex()   ));
      
     
     //this.nodes.get(edges.get(edgeToAdd).getToNodeIndex()).getEdges().add(edges.get(edgeToAdd));
    }
  }
  
  
  
  
  private int calculateNoOfNodes(ArrayList<Edge> edges) {
    int noOfNodes = 0;
    for (Edge e : edges) {
      if (e.getToNodeIndex() > noOfNodes  )
        noOfNodes = e.getToNodeIndex();
      if (e.getFromNodeIndex() > noOfNodes)
        noOfNodes = e.getFromNodeIndex();
    }
    noOfNodes++;
    return noOfNodes;
  }
  // next video to implement the Dijkstra algorithm !!!
  public void calculateShortestDistances( int sourceindex) {
    // node 0 as source
    this.nodes.get(sourceindex).setDistanceFromSource((float) 0.0);
    int nextNode = sourceindex;
    // visit every node
    
    
  
   
    for (int i = 0; i < this.nodes.size(); i++) {
      // loop around the edges of current node
      ArrayList<Edge> currentNodeEdges = this.nodes.get(nextNode).getEdges();
      
      for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
        int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
        // only if not visited
        if (!this.nodes.get(neighbourIndex).isVisited()   &&  !this.nodes.get(neighbourIndex).isMaintance()    ) {
        	
        	
        	// System.out.println("   ========>>  "+Assignment4.names.get(nextNode));
        	
        	
        	 ArrayList<String>  myarray=new ArrayList<>();
        	 for (String string : this.nodes.get(nextNode).getTrack()) {
        		 myarray.add(string);
			}
        	 myarray.add(Assignment4.names.get(neighbourIndex));
          float tentative = this.nodes.get(nextNode).getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
          
          if (tentative < nodes.get(neighbourIndex).getDistanceFromSource()) {
        	  
        	  
        	  
        	  
        	  nodes.get(neighbourIndex).settrack(myarray);
        	  //System.out.println("name "+Assignment4.names.get(neighbourIndex));
        	 
        	 
            nodes.get(neighbourIndex).setDistanceFromSource(tentative);
            
            
          }
        }
      }
      // all neighbours checked so node visited
      
      nodes.get(nextNode).setVisited(true);
      // next node must be with shortest distance
      
      nextNode = getNodeShortestDistanced(nextNode);
      //System.out.println("tentative  "+Assignment4.names.get(nextNode));
      
   }
  }
  // now we're going to implement this method in next part !
  private int getNodeShortestDistanced(int currentnode) {
    int storedNodeIndex = 0;
    float storedDist =500;
    for (int i = 0; i < this.nodes.size(); i++) {
      float currentDist = this.nodes.get(i).getDistanceFromSource();
      
     
  		//System.out.println("  ths si +"+currentnode +"  "+  i+" " +findusedEdge(currentnode ,i).getBroke());
      
      /// !findusedEdge(currentnode ,i).getBroke() 
      if (!this.nodes.get(i).isVisited() && currentDist < storedDist   &&   !this.nodes.get(i).isMaintance()   ) {
    	  
    	  		
    	  
    	  
    	  //	System.out.println("  ths si +"+ Assignment4.names.get(i));
    	  
    	  
			storedDist = currentDist;
	    	storedNodeIndex = i;
			
			
    	 
    		 
        
      }
    }
    return storedNodeIndex;
  }
 




// display result
  public float distance(int indexto) {
    //String output = "Number of nodes = " + this.noOfNodes;
   // output += "\nNumber of edges = " + this.edges.size();
    for (int i = 0; i < this.nodes.size(); i++) {
    	
    	if (i==indexto) {
			return   nodes.get(i).getDistanceFromSource();
		}
     // output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes.get(i).getDistanceFromSource());
    }
    
    return  0;
    //System.out.println(output);
  }
  

  public ArrayList<Node> getNodes() {
    return nodes;
  }
  public int getNoOfNodes() {
    return noOfNodes;
  }
  public ArrayList<Edge> getEdges() {
    return this.edges;
  }
  
 
 
}