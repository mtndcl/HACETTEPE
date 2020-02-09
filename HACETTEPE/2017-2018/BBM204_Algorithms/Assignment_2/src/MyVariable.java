
public class MyVariable {
	
	
	
	private int memory;
	private String  Replacementtype;
	private String  SearchStructuretype;
	private int curretindex;
	private int currentsize;
	private int numberfault;
	private Boolean  have;
	
	public MyVariable() {
		this.curretindex=0;
		this.currentsize=0;
		this.numberfault=0;
		
	}
	
	public Boolean getHave() {
		return have;
	}

	public void setHave(Boolean have) {
		this.have = have;
	}

	public int getCurrentsize() {
		return currentsize;
	}

	public int getNumberfault() {
		return numberfault;
	}

	public void setNumberfault(int numberfault) {
		this.numberfault = this.numberfault +numberfault;
	}

	public void setCurrentsize(int currentsize) {
		this.currentsize =this.currentsize+ currentsize ;
	}

	public int getCurretindex() {
		return curretindex;
	}
	
	public void setCurretindexOK(int curretindex) {
		this.curretindex =curretindex;
	}
	public void setCurretindex(int curretindex) {
		this.curretindex = this.curretindex+curretindex;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public String getReplacementtype() {
		return Replacementtype;
	}
	public void setReplacementtype(String replacementtype) {
		Replacementtype = replacementtype;
	}
	public String getSearchStructuretype() {
		return SearchStructuretype;
	}
	public void setSearchStructuretype(String searchStructuretype) {
		SearchStructuretype = searchStructuretype;
	}
	
	
}
