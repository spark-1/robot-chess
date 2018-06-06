package view;

public class Boom extends Thread{
	Battle_Area b = null;
	int i;
	int j;
	public Boom(Battle_Area b, int i, int j)
	{
		this.b = b;
		this.i = i;
		this.j = j;
	}
	
	public void start()
	{
		b.light_hit(i, j);
		try {
			sleep(500);
		} catch (InterruptedException e) {}
		b.dislight();
		System.out.println("Shall we Dance");
	}

}
