
public class Game {
	
	Player player[];
	Base board[][];
	int turn;
	
	public Game() {
		player = new Player[2];
		player[0] = new Player("박성준");
		player[1] = new Player("멍청이");
		board = new Base[8][8];
		turn = 0; // i일시 플레이어[i]의 턴
	}
	
	public static void main(String args[]){
		Game g = new Game();
		
		g.board[1][1] = new Pawn("폰", "인간형");
		g.board[2][2] = new Rook("룩", "기계형");
		
		g.turn = 0;
		g.move(1, 1, 3, 3);
		g.attack(3, 3, 2, 2);
		g.skill(3, 3, 2, 2);
	}
	
	public void win(){
		System.out.println(player[turn].name + "이(가) 승리했습니다.");
	}
	
	public void move(int x, int y, int toX, int toY){
		player[turn].move(toX, toY, board[x][y], board);
	}
	
	public void attack(int x, int y, int toX, int toY){
		player[turn].attack(toX, toY, board[x][y], board);
	}
	
	public void skill(int x, int y, int toX, int toY){	
		player[turn].skill(toX, toY, board[x][y], board);
	}
	
	//public void equip(int x, int y){
	//	player[turn].equip(board[x][y], board[x][y].e);
	//}
}
