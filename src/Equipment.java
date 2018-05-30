
public abstract class Equipment {
	
	String name;
	String equip_type;
	int added_def;
	int added_atk;
	int adeed_spd;
	String checktype() {
		return equip_type;		
	}
	
	
	public Equipment() {
		System.out.println("Equipment 추상클래스");
	}

}
