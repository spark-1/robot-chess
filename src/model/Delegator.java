package model;

public class Delegator {

	public Delegator() {
	}

	public void move(Base a, int toX, int toY, Base board[][]) {
		if (((Math.abs(a.locX - toX) <= a.spdX) && (a.locY == toY)) || ((Math.abs(a.locY - toY) <= a.spdY) && (a.locX == toX))) {
			System.out.println("중개자가 움직여줍니다");
			if(a.move() == 0){ // 룩이 움직일 수 없으면
				return;
			}
			board[toX][toY] = a;
			board[a.locX][a.locY] = null;
			a.locX = toX;
			a.locY = toY;
			System.out.println(a.master + "의 " + a.name + "이(가) " + a.locX + "행" + a.locY + "열로 움직였습니다.");
			Game.turn *= -1;
		} else {
			System.out.println(a.name + "의 이동범위 밖입니다.");
		}
	}
	
	public void attack(Base a, Base b, Base board[][]) {
		if (Math.abs(a.locX - b.locX) + Math.abs(a.locY - b.locY) <= a.range) {
			System.out.println("중개자가 공격을 시도해줍니다.");
			if(a.attack() == 0){ // 룩이 공격할 수 없으면
				return;
			}
			b.hp -= a.atk - b.def;
			if(b.hp <= 0){
				System.out.println(a.master + "의 " + a.name + "의 공격으로 " + b.master + "의 " + b.name + "이(가) 죽었습니다.");
				board[b.locX][b.locY] = null;
				if(b instanceof King) {
					Game.win();
				}
			}
			else{
				System.out.println(a.master + "의 " + a.name + "의 공격으로 " + b.master + "의 " + b.name + "의 체력이 " + b.hp + "로 되었습니다.");
			}
			Game.turn *= -1;
		} else {
			System.out.println(a.name + "의 공격범위 밖입니다");
		}
	}

	public void skill(Base a, Base board[][]) { // 패시브 스킬인 경우
			System.out.println("중개자가 스킬을 써줍니다.");
			a.skill();
			Game.turn *= -1;
			System.out.println(a.master + "의 " + a.name + "이(가) 스킬을 사용했습니다");
	}
	
	public void skill(Base a, Base b, Base board[][]) { // 공격 스킬인 경우
		if (Math.abs(a.locX - b.locX) + Math.abs(a.locY - b.locY) <= a.range) {
			System.out.println("중개자가 스킬을 써줍니다.");
			if(a.skill(a, b) == 0){ // 액티브 스킬을 사용할 수 없으면
				return;
			}
			if(b.hp <= 0){
				System.out.println(a.master + "의 " + a.name + "의 스킬공격으로 " + b.master + "의 " + b.name + "이(가) 죽었습니다.");
				board[b.locX][b.locY] = null;
				if(b instanceof King) {
					Game.win();
				}
			}
			else{
				System.out.println(a.master + "의 " + a.name + "이(가) 스킬공격을 사용했습니다");
			}
			Game.turn *= -1;
			
		} else {
			System.out.println(a.name + "의 스킬범위 밖입니다");
		}
	}

	public void equip(Base a, Equipment e) {
		System.out.println("중개자가 장비를 장착해줍니다.");
		if (e.getClass().getName().equals("model.Armor") && a.e[0] == null) {
			a.e[0] = e;
			a.def += 3;
			System.out.println(a.master + "가 " + a.name + "에게 아머를 장착합니다. 방어력이 3증가합니다." );
		}
		else if (e.getClass().getName().equals("model.Weapon") && a.e[1] == null) {
			a.e[1] = e;
			a.atk += 5;
			System.out.println(a.master + "가 " + a.name + "에게 무기를 장착합니다. 공격력이 5증가합니다." );
		}
		else if (e.getClass().getName().equals("model.Leg") && a.e[2] == null) {
			a.e[2] = e;
			a.spdX += 1;
			a.spdY += 1;
			System.out.println(a.master + "가 " + a.name + "에게 다리를 장착합니다. 이동거리가 1증가합니다." );
		}
	}
}
