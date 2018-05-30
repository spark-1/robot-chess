package view;

public class Test{
	public static void main(String[] args){
		Battle_Area b = new Battle_Area();
		b.setVisible(true);
		b.light_moveable(3, 4);
		b.light_attackable(3, 5);
	}
}
