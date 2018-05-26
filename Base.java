
public abstract class Base{

	String name;
	String type;
	Equipment[] e = new Equipment[3];
	int hp;
	int atk;
	int def;
	int range; // 공격 범위
	int spdX; // x축을 얼마나 움직일지
	int spdY; // y축을 얼마나 움직일지
	int locX; // 현재 말의 x축 위치 
	int locY; // 현재 말의 y축 위치
	
	public Base(String name, String type){
		hp = 100;
		atk = 10;
		def = 0;
		range = 2;
		spdX = 1;
		spdY = 1;
		locX = 1;
		locY = 1;
		this.name = name;
		this.type = type;
	}
	
	public abstract void status_change();
	
	public abstract void move();

	public abstract void attack();
	
	public abstract void skill();
}
