import java.util.ArrayList;

public class Queue {
		
	
	private ArrayList<Integer> data;
	public Queue() {
		this.data=new ArrayList<Integer>();
	}

	
	public int dequeue()
	{
			int value=this.data.get(0);
			this.data.remove(0);
			return value;
	}

	public void enqueue(int item)
	{
		this.data.add(item);
	}

	public Boolean isEmpty()
	{
		return this.data.size()==0 ? true : false;
	}
	


	




}
