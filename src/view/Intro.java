package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.Horse;

public class Intro extends JFrame implements MouseListener, KeyListener {

	public JPanel contentPane;
	private JLabel Title;
	private BlinkLabel Press;
	private int w = 1000;
	private int h = 678;
	public ReadyFrame PF;
	public Horse H;
	public Game G;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Intro(Game g) {
		this.G = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, w, h);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.addMouseListener(this);
		contentPane.addKeyListener(this);
		setContentPane(contentPane);
		
		Title = new JLabel("R o b o t   C h e s s");
		Title.setBounds(300, 200, 600,60);
		Title.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå EXTRABOLD",Font.BOLD,40));
		contentPane.add(Title);
		
		Press = new BlinkLabel("Press Enter");
		Press.setBounds(450, 400, 400, 40);
		//Press.setFont(new Font("µ¸¿ò",Font.BOLD,20));
		contentPane.add(Press);
		setVisible(true);
		H = null;
		PF = null;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			this.setVisible(false);
			PF = new ReadyFrame(G);
			PF.setVisible(true);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == contentPane) {
			this.setVisible(false);
			PF = new ReadyFrame(G);			
			PF.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
