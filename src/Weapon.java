package Model;

public class Weapon extends Equipment{

	public Weapon(Base a, Equipment e) {
		this.equip_type = "Weapon";
		a.e[1] = e;
		a.atk += 5;
		System.out.println(a.master + "가 " + a.name + "에게 무기를 장착합니다. 공격력이 5증가합니다." );
	}


}
