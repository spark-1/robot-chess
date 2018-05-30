
public class Delegator {

	public Delegator() {
	}

	public void attack(Base a, Base b) {
		if (Math.abs(a.locX - b.locX) + Math.abs(a.locY - b.locY) <= a.range) {
			System.out.println("중개자가 공격을 시도해줍니다.");
			a.attack();
			b.hp -= a.atk - b.def;
			System.out.println(a.name + "의 공격으로 " + a.master + "의 " + b.name + "의 체력이 " + b.hp + "로 되었습니다.");
			Game.turn *= -1;
		} else {
			System.out.println(a.name + "의 공격범위 밖입니다");
		}
	}

	public void move(Base a, int toX, int toY, Base board[][]) {
		if (Math.abs(a.locX - toX) <= a.spdX && Math.abs(a.locY - toY) <= a.spdY) {
			System.out.println("중개자가 움직여줍니다");
			a.move();
			board[toX][toY] = a;
			board[a.locX][a.locY] = null;
			a.locX = toX;
			a.locY = toY;
			Game.turn *= -1;
		} else {
			System.out.println(a.name + "의 이동범위 밖입니다.");
		}
	}

	public void skill(Base a, Base b) {
		if (Math.abs(a.locX - b.locX) + Math.abs(a.locY - b.locY) <= a.range) {
			System.out.println("중개자가 스킬을 써줍니다.");
			b.skill();
			Game.turn *= -1;
		} else {
			System.out.println(a.name + "의 공격범위 밖입니다");
		}
	}

	public void equip(Base b, Equipment e) {
		System.out.println("중개자가 장비를 장착해줍니다.");
		if (e.getClass().getName().equals("Armor") && b.e[0] == null) {
			b.e[0] = e;
			b.def += 3;
			System.out.println(b.master + "가 " + b.name + "에게 아머를 장착합니다. 방어력이 3증가합니다." );
		}
		else if (e.getClass().getName().equals("Weapon") && b.e[1] == null) {
			b.e[1] = e;
			b.atk += 5;
			System.out.println(b.master + "가 " + b.name + "에게 무기를 장착합니다. 공격력이 5증가합니다." );
		}
		else if (e.getClass().getName().equals("Leg") && b.e[2] == null) {
			b.e[2] = e;
			b.spdX += 1;
			b.spdY += 1;
			System.out.println(b.master + "가 " + b.name + "에게 다리를 장착합니다. 이동거리가 1증가합니다." );
		}
	}
}
