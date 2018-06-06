package model;

public abstract class Equipment {
	
	String name;
	public String equip_type;
	int added_def;
	int added_atk;
	int adeed_spd;
	
	public Equipment() {
		System.out.println("장비 생성!");
	}
}
