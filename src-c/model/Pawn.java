package model;

public class Pawn extends Base implements Pawnable {
	
	public Pawn(String name, String type, String master, int x, int y) {
		super(name, type, master, x, y);
		super.PassiveSkillDescription = "폰이 패시브 스킬을 씁니다. 체력이 5회복됩니다.";
		super.ActiveSkillDescription = "폰이 스킬을 씁니다! 두번연속하여 공격합니다!";
		System.out.println("Pawn 생성");
		activeSkill = 1;
	}

	public int attack() {
		System.out.println("Pawn 공격함");
		activeSkill = 1;
		return 1;
	}

	public int move() {
		System.out.println("Pawn 움직임");
		activeSkill = 1;
		return 1;
	}

	public void skill() {
		System.out.println("Pawn 패시브 skill 발동");
		hp += 5;
		if (hp > maxHp) {
			hp = maxHp;
		}
		activeSkill = 1;
	}

	@Override
	public int skill(Base a, Base b) {
		if (activeSkill == 1) { // 공격 가능 상태이면 스킬을 써줍니다
			System.out.println("Pawn 액티브 skill 발동");
			b.hp -= a.atk - b.def;
			b.hp -= a.atk - b.def;
			System.out.println(a.name + "의 스킬 연속공격으로 " + a.master + "의 " + b.name + "의 체력이 " + b.hp + "로 되었습니다.");
			activeSkill = -1;
			return 1;
		}
		else {
			return 0;
		}
	}

	public Movable getRookMoveable() {
		return null;
	}
}