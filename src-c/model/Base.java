package model;


public abstract class Base{

	public String name;
	String type;
	public String master;
	Equipment[] e = new Equipment[3];
	public int hp;
	public int atk;
	public int def;
	int range; // 공격 범위
	public int spdX; // x축을 얼마나 움직일지
	public int spdY; // y축을 얼마나 움직일지
	public int locX; // 현재 말의 x축 위치 
	public int locY; // 현재 말의 y축 위치
	public String des;
	
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
		if(type.equals("기계형")) {
			hp += 10;
			atk += 5;
			def += 3;
		}
	}
	
	public abstract int move();

	public abstract int attack();
	
	public abstract void skill();

	public void skill(Base a, Base b) {
		// TODO Auto-generated method stub
		
	}
}
