public class Rook extends Base implements Rookable {

	Rookable rk = null; 
	public Siegeable se = null; 
	public Movable mv = null;

	public Rook(String name, String type, String master, int x, int y) {
		super(name, type, master, x, y);
		super.des = "룩이 스킬을 씁니다! 모드를 변환합니다!";
		System.out.println("Rook 생성");
		mv = (Movable) this; // 생성시 Movable을 Rook으로 바꿔줍니다.
		se = null; // Siegeable은 null이고요. 이러면 지금 Rook은 Movable입니다.
		rk = (Rookable) mv;
	}

	public int move() {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 움직이질 못합니다.
		{
			System.out.println("못 움직여요! Siegeable이에오!");
			return 0;
		} else {
			System.out.println("Rook 움직여요!");
			return 1;
		}
	}

	@Override
	public void change_form(Rookable rk) {
		this.rk = rk;
	}

	@Override
	public int attack() {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 공격이 가능합니다.
		{
			System.out.println("Rook 공격해요!");
			return 1;
		} else {
			System.out.println("못 공격해요! Movable이에요!");
			return 0;
		}
	}

	@Override
	public void skill(Base a, Base b) {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 Skill을 쓰면 Movable이됩니다.
		{
			this.se = null; // se가 null
			this.mv = this; // Rook이 mv로 바뀝니다.
			rk = (Rookable) mv;
			System.out.println("이젠 Movable이에요!");
		} else {
			this.mv = null;
			this.se = this; // 반대의 경우에는 반대로.
			rk = (Rookable) se;
			System.out.println("이젠 Siegeable이에요!");
		}
	}
	
	public Movable getRookMoveable(){
		return mv;
	}

}
