import Model.Horse;

public class BaseList {
	public String name;
	public String type;
	public String NT;
	
	public BaseList(String n, String t){
		this.name = n;
		this.type = t;
		this.NT = this.name+"<"+this.type+">";
			
	}
}
