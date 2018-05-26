
public class Player {
	
	String name;
	Delegator d;
	Base b;
	
	public Player() {
		this.d = new Delegator();
	}
	public Player(String name) {
		this.d = new Delegator();
		this.name = name;
	}
	
	public void control(Base b){
		this.b = b;
		System.out.println(name + "이(가) " + b.name + "을(를) 조종합니다.");
	}
	
	public void move(int toX, int toY, Base b, Base[][] board){
		control(b);
		d.move(toX, toY, b, board);
	}
	
	public void attack(int toX, int toY, Base b, Base[][] board){
		control(b);
		d.attack(toX, toY, b, board);
	}
	
	public void skill(int toX, int toY, Base b, Base[][] board){
		control(b);
		d.skill(toX, toY, b, board);
	}
	
	//public void equip(Base b, Equipment e){
	//	control(b);
	//	d.equip(b, e);
	//}
}