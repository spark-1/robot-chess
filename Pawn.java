public class Pawn extends Base implements Pawnable {

    public Pawn(String name, String type, String master, int x, int y){
    	super(name, type, master, x, y);
    	super.des = "폰이 스킬을 씁니다! 두번연속하여 공격합니다!";
    	System.out.println("Pawn 생성");
    }

    public void attack(){
    	System.out.println("Pawn 공격함");
    }
    
    public void move(){
    	System.out.println("Pawn 움직임");
    }

	@Override
	public void skill() {
		System.out.println("Pawn skill 발동");
	}
}