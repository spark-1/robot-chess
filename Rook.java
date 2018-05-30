public class Rook extends Base implements Rookable {

	Rookable rk = null; // Rookable은 왜 있는지 의문입니다... 만 일단 교수님이 넣어놓으셨어서 넣었습니다.
	public Siegeable se = null; // Siegeable도
	public Movable mv = null; // Movable도 null 지금은 그 어떤것도 아닙니다.

	public Rook(String name, String type, String master, int x, int y) {
		super(name, type, master, x, y);
		super.des = "룩이 스킬을 씁니다! 모드를 변환합니다!";
		System.out.println("Rook 생성");
		mv = (Movable) this; // 생성시 Movable을 Rook으로 바꿔줍니다.
		se = null; // Siegeable은 null이고요. 이러면 지금 Rook은 Movable입니다.
		rk = (Rookable) mv;
	}

	public void move() {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 움직이질 못합니다.
		{
			System.out.println("못 움직여요! Siegeable이에오!");
		} else {
			System.out.println("Rook 움직여요!");
		}
	}

	@Override
	public void change_form(Rookable rk) {
		this.rk = rk;
	}

	@Override
	public void attack() {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 공격이 가능합니다.
		{
			System.out.println("Rook 공격해요!");
		} else {
			System.out.println("못 공격해요! Movable이에요!");
		}
	}

	@Override
	public void skill() {
		if (this.mv == null) // mv가 null, 즉 se가 null이 아니라 Skill을 쓰면 Movable이
								// 됩니다.
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

}
