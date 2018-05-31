package model;
public class King extends Base {

    public King(String name, String type, String master, int x, int y){
    	super(name, type, master, x, y);
    	super.des = "킹이 스킬을 씁니다! 멋있습니다...";
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
	public void skill(Base a, Base b) {
		System.out.println("King skill 발동");
		System.out.println("턴 낭비");
	}
	
	public Movable getRookMoveable(){
		return null;
	}

	@Override
	public void skill() {
		// TODO Auto-generated method stub
		
	}
}