
public class Delegator {

	public Delegator() {
		// TODO Auto-generated constructor stub
	}

	public void attack(int x, int y, Base b, Base[][] board) {
		System.out.println("중개자가 공격을 시도해줍니다.");
		b.attack();
		board[x][y].hp -= b.atk - board[x][y].def;
		System.out.println(b.name + "의 공격으로 " + board[x][y].name + "의 체력이 " + board[x][y].hp + "로 되었습니다.");
	}

	public void move(int x, int y, Base b, Base[][] board) {
		System.out.println("중개자가 움직여줍니다");
		b.move();
		board[x][y] = b;
		board[b.locX][b.locY] = null;
		b.locX = x;
		b.locY = y;		
	}

	public void skill(int x, int y, Base b, Base[][] board) {
		System.out.println("중개자가 스킬을 써줍니다.");
		b.skill();
	}
	
	public void equip(Base b, Equipment e) {
		System.out.println("중개자가 장비를 장착해줍니다.");
	}
}
