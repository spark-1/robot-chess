package model;

public class User {
	int id;
	String name;
	int total;
	int win;
	int lose;
	
	public User() {
		
	}

	public User(int id, String name, int total, int win, int lose) {
		this.id =id;
		this.name = name;
		this.total = total;
		this.win = win;
		this.lose = lose;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}
}
