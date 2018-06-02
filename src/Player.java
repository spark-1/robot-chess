package Model;

public class Player {
	
	String name;
	Delegator d;
	Base a;
	
	public Player(String name) {
		this.d = new Delegator();
		this.name = name;
	}
	
	public void control(Base a){
		this.a = a;
		System.out.println(name + "이(가) " + a.name + "을(를) 조종합니다.");
	}
	
	public void move(Base a, int toX, int toY, Base board[][]){
		control(a);
		d.move(a, toX, toY, board);
	}
	
	public void attack(Base a, Base b, Base board[][]){
		control(a);
		d.attack(a, b, board);
	}
	
	public void skill(Base a, Base board[][]){
		control(a);
		d.skill(a, board);
	}
	
	public void skill(Base a, Base b, Base board[][]){
		control(a);
		d.skill(a, b, board);
	}
	
	public void equip(Base a, Equipment e){
		control(a);
		d.equip(a , e);
	}
}