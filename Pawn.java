public class Pawn extends Base implements Pawnable {

    public Pawn(String name, String type){
    	super(name, type);
    	System.out.println("Pawn 생성");
    }

    public void attack(){
    	System.out.println("Pawn 공격함");
    }
    
    public void move(){
    	System.out.println("Pawn 움직임");
    }

	public void status_change(){
		System.out.println("Pawn 능력변화");
	}

	@Override
	public void skill() {
		System.out.println("Pawn skill 발동");
	}
}