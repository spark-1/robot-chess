package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Endgame extends JFrame{

	private JPanel MainPane;
	private BlinkLabel Con;
	private JLabel Who;
	Ost ost = Ost.getInstance();
	
	public Endgame(String name)
	{
		ost.playMusic(ost.Win);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(name + " Wins!");
		this.setSize(1000, 678);
		MainPane = new JPanel();
		this.add(MainPane);
		this.setContentPane(MainPane);
		this.setVisible(true);
		MainPane.setLayout(null);
		
		Con = new BlinkLabel("축하합니다!!");
		Con.setBounds(350, 400, 400, 100);
		Con.setFont(new Font("Monospaced", Font.PLAIN, 40));
		MainPane.add(Con);
		
		Who = new JLabel(name + "이(가) 승리하였습니다!!");
		Who.setForeground(Color.MAGENTA);
		Who.setBounds(200, 100, 700, 300);
		Who.setFont(new Font("Monospaced", Font.PLAIN, 50));
		MainPane.add(Who);
	}
}
