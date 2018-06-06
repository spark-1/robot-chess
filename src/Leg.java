package Model;

public class Leg extends Equipment{

	public Leg(Base a, Equipment e) {
		this.equip_type = "Leg";
		a.e[2] = e;
		a.spdX += 1;
		a.spdY += 1;
		System.out.println(a.master + "가 " + a.name + "에게 다리를 장착합니다. 이동거리가 1증가합니다." );
	}

}
