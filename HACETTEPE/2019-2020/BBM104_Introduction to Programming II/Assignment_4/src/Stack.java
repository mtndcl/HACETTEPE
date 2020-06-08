import java.util.ArrayList;

public class Stack {
	
	
	
	private ArrayList<Integer> data;
	
	public Stack() {
		data=new ArrayList<Integer>();
	}
	public void push(int value) {
		
		data.add(value);
	
	}
	public boolean isEmpty() {
		return this.data.size()==0 ? true : false;
	}
	public int pop() {

		int size=this.data.size();
		int value=this.data.get(size-1);
		this.data.remove(size-1);
	
		return value;
	}
	public int top() {
		int size=this.data.size();
		return this.data.get(size-1);
	}

}
