package model;

public class King extends Base {

    public King(String name, String type, String master, int x, int y){
    	super(name, type, master, x, y);
    	System.out.println("King 생성");
    }

    public int attack(){
    	System.out.println("King 공격함");
    	return 1;
    }
    
    public int move(){
    	System.out.println("King 움직임");
    	return 1;
    }

	@Override
	public int skill(Base a, Base b) {
		System.out.println("King skill 발동");
		System.out.println("턴 낭비");
		return 1;
	}
	
	public Movable getRookMoveable(){
		return null;
	}

	@Override
	public void skill() {
		System.out.println("King skill 발동");
		System.out.println("턴 낭비");
	}
}