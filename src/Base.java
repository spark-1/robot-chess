
public abstract class Base{

	String name;
	String type;
	String master;
	Equipment[] e = new Equipment[3];
	int maxHp;
	int hp;
	int atk;
	int def;
	int range; // 공격 범위
	int spdX; // x축을 얼마나 움직일지
	int spdY; // y축을 얼마나 움직일지
	int locX; // 현재 말의 x축 위치 
	int locY; // 현재 말의 y축 위치
	String des;
	
	public Base(String name, String type, String master, int x, int y){
		hp = 100;
		atk = 10;
		def = 0;
		range = 1;
		spdX = 1;
		spdY = 1;
		locX = x;
		locY = y;
		this.name = name;
		this.type = type;
		this.master = master;
		
		if(name.equals("룩")) {
			hp += 50;
			atk += 20;
			def += 2;
			spdX += 2;
			spdY += 2;
			range += 1;
		}
		if(name.equals("킹")) {
			hp += 100;
			atk -= 5;
		}
		if(type.equals("기계형")) {
			hp += 10;
			atk += 5;
			def += 3;
		}
		maxHp = hp;
	}
	
	public abstract Movable getRookMoveable();
	
	public abstract int move();

	public abstract int attack();
	
	public abstract void skill(Base a, Base b);
}
