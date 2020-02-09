import java.util.ArrayList;

public class Node {
  
	private float distanceFromSource = 600;
  
	private boolean visited;
	
	
	private  ArrayList<String>   track;
	
	private Boolean underMaintenance;
	
	
	
	private  int  totalpasses;
  
	private ArrayList<Edge> edges = new ArrayList<Edge>(); // now we must create edges
	
	
	public Node() {
		this.track=new ArrayList<>();
		this.totalpasses=0;
		this.underMaintenance=false;
	}
	
	
	public void settrack(ArrayList<String>  f){
		
		this.track=f;
				}
	public ArrayList<String> getTrack() {
		return track;
	}

	public int getTotalpasses() {
	    return totalpasses;
	  }
	
	public void incerasetotalpassesd() {
		this.totalpasses=this.totalpasses+1;
	  }
  public float getDistanceFromSource() {
    return distanceFromSource;
  }
  public void setDistanceFromSource(float tentative) {
    this.distanceFromSource = tentative;
  }
  
  public boolean isMaintance() {
	    return underMaintenance;
	  }
  public boolean isVisited() {
    return visited;
  }
  public void setVisited(boolean visited) {
    this.visited = visited;
  }
  public void setMaintance(boolean m) {
	    this.underMaintenance = m;
	  }
  public ArrayList<Edge> getEdges() {
    return edges;
  }
  public void setEdges(ArrayList<Edge> edges) {
    this.edges = edges;
  }
}