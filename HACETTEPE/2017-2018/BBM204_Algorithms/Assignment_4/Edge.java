public class Edge {
	
	
	
	
  private int fromNodeIndex;
  private int toNodeIndex;
  private Float length;
  
  
  private  Boolean  broke;
  
  private  Boolean  swi;
  public Edge(int fromNodeIndex, int toNodeIndex) {
    this.fromNodeIndex = fromNodeIndex;
    this.toNodeIndex = toNodeIndex;
    this.swi=false;
    this.broke=false;
  }
  
  
  public void setBroke(Boolean  bro) {
	  this.broke=bro;
	  }
  public void setSwi(Boolean  sw) {
	  this.swi=sw;
	  }
  
  public Boolean getBroke() {
	    return broke;
	  }
  public Boolean getswi() {
	    return swi;
	  }
  public int getFromNodeIndex() {
    return fromNodeIndex;
  }
  public int getToNodeIndex() {
    return toNodeIndex;
  }
  public Float getLength() {
    return length;
  }
  
  public void setLenght(Float lenght) {
	
	  
	  //System.out.println(Assignment4.names.get(fromNodeIndex)  +"  eeeeeee "+Assignment4.names.get(toNodeIndex)  +"uxubb "+lenght);
	  
	  this.length=lenght;
}
  // determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
  public int getNeighbourIndex(int nodeIndex) {
    if (this.fromNodeIndex == nodeIndex) {
      return this.toNodeIndex;
    } else {
      return this.fromNodeIndex;
   }
  }
}