package model;

import control.Overseer;
import view.Intro;

public class Game {

	public static Player player[];
	public Base board[][];
	public Equipment equipment;
	static public int turn;
	static public int end; // 1이면 0번 플레이어 승리 -1이면 1번플레이어 승리

	public Game() {
		int i = 0;
		int j = 0;
		
		player = new Player[2];
		player[0] = new Player("정다혜");
		player[1] = new Player("고양이");
		board = new Base[8][8];
		for(i = 0; i < 8 ;i++) {
			for(j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		turn = 1; // 1일시 플레이어[0]의 턴, -1일시 플레이어[1]의 턴
	}

	public static void main(String args[]) {
		Game g = new Game();
		Game.turn = 1;
		Intro I = new Intro(g);
		

		//Overseer control = new Overseer(g);

	}


	public static void win() {
		if (turn == 1) {
			System.out.println(player[0].name + "이(가) 승리했습니다.");
			end = 1;
		}
		if (turn == -1) {
			System.out.println(player[1].name + "이(가) 승리했습니다.");
			end = -1;
		}
	}

	public void move(int x, int y, int toX, int toY) {
		if (board[x][y] == null) {
			System.out.println("말을 선택 안하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name) && board[toX][toY] == null) {
				player[0].move(board[x][y], toX, toY, board);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name) && board[toX][toY] == null) {
				player[1].move(board[x][y], toX, toY, board);
			} else {
				System.out.println("말 또는 이동할곳을 잘못선택하셨습니다.");
			}
		}
	}

	public void attack(int x, int y, int toX, int toY) {
		if (board[x][y] == null || board[toX][toY] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)
					&& board[toX][toY].master.equals(player[1].name)) {
				player[0].attack(board[x][y], board[toX][toY], board);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)
					&& board[toX][toY].master.equals(player[0].name)) {
				player[1].attack(board[x][y], board[toX][toY], board);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void skill(int x, int y) {
		if (board[x][y] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)) {
				player[0].skill(board[x][y], board);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)) {
				player[1].skill(board[x][y], board);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void skill(int x, int y, int toX, int toY) {
		if (board[x][y] == null || board[toX][toY] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)
					&& board[toX][toY].master.equals(player[1].name)) {
				player[0].skill(board[x][y], board[toX][toY], board);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)
					&& board[toX][toY].master.equals(player[0].name)) {
				player[1].skill(board[x][y], board[toX][toY], board);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void equip(int x, int y, Equipment e) { // e는 0 헤드 1 무기 2 다리
		if (board[x][y] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)) {
				player[0].equip(board[x][y], e);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)) {
				player[1].equip(board[x][y], e);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	// point를 받기위한 함수들이에요
	public int[][] movepoint(Base b) {
		int mv[][] = new int[8][8];

		if (b.move() == 0) {
			return mv;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (((Math.abs(b.locX - i) <= b.spdX) && (b.locY == j))
						|| ((Math.abs(b.locY - j) <= b.spdY) && (b.locX == i))) {
					mv[i][j] = 1;
				}
			}
		}

		return mv;
	}

	public int[][] attackpoint(Base b) {
		int mv[][] = new int[8][8];

		if (b.attack() == 0) {
			return mv;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Math.abs(b.locX - i) + Math.abs(b.locY - j) <= b.range) {
					mv[i][j] = 1;
				}
			}
		}
		mv[b.locX][b.locY] = 0;

		return mv;
	}

	public int[][] passkillpoint(Base b) {
		int mv[][] = new int[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == b.locX && j == b.locY) {
					mv[i][j] = 1;
				}
			}
		}
		return mv;
	}

}
