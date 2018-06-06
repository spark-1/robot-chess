package model;

public class Armor extends Equipment{

	public Armor(Base a, Equipment e) {
		this.equip_type = "Armor"; 
		a.e[0] = e;
		a.def += 3;
		System.out.println(a.master + "가 " + a.name + "에게 아머를 장착합니다. 방어력이 3증가합니다." );
	}

}