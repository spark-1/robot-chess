public class Pawn extends Base implements Pawnable {

    public Pawn(String name, String type, String master, int x, int y){
    	super(name, type, master, x, y);
    	super.des = "폰이 스킬을 씁니다! 두번연속하여 공격합니다!";
    	System.out.println("Pawn 생성");
    }

    public int attack(){
    	System.out.println("Pawn 공격함");
    	return 1;
    }
    
    public int move(){
    	System.out.println("Pawn 움직임");
    	return 1;
    }

	@Override
	public void skill(Base a, Base b) {
		System.out.println("Pawn skill 발동");
		b.hp -= a.atk - b.def;
		b.hp -= a.atk - b.def;
		System.out.println(a.name + "의 스킬 연속공격으로 " + a.master + "의 " + b.name + "의 체력이 " + b.hp + "로 되었습니다.");
	}
	
	public Movable getRookMoveable(){
		return null;
	}
}