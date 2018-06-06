package model;


public class Horse {
	int id;
	String name;
	String type;
	int hp;
	int atk;
	int def;
	int hrange;
	int spdX;
	int spdY;
	
	public Horse() {
		
	}
	public Horse(int id,String name,String type,int hp,	int atk,	int def,	int hrange, int spdX, int spdY) {
		this.id= id;
		this.name = name;
		this.type = type;
		this.hp = hp;
		this.atk = atk;
		this.def= def;
		this.hrange = hrange;
		this.spdX = spdX;
		this.spdY = spdY;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getHrange() {
		return hrange;
	}
	public void setHrange(int hrange) {
		this.hrange = hrange;
	}
	public int getSpdX() {
		return spdX;
	}
	public void setSpdX(int spdX) {
		this.spdX = spdX;
	}
	public int getSpdY() {
		return spdY;
	}
	public void setSpdY(int spdY) {
		this.spdY = spdY;
	}
		
}
