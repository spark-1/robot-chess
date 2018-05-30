import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class PlayerFrame extends JPanel implements MouseListener{
	private JPanel player;
	private JLabel Title;
	private JComboBox[][] Choose = new JComboBox[2][8];
	private JButton Ready;
	private JLabel RR;
	private int w = 1000;
	private int h = 678;
	private boolean RUReady = false;
	
	public PlayerFrame(int x, int y, int i, Game g){
		String tmp;
		int j = 0;
		
		setBounds(x, y, w/2, h);
		player = new JPanel();
		player.setBorder(new EmptyBorder(5, 5, 5, 5));
		player.setLayout(null);
		
		tmp = Integer.toString(i);
		Title = new JLabel("Player"+tmp);
		Title.setBounds(x, y, 40, 200);
		Title.setFont(new Font("돋움",Font.BOLD,20));
		player.add(Title);
		
		
		//콤보박스를 만들어 주는 부분 추후 <instanceof를 활용하여 폰 룩의 콤보박스를 구별해줄 필요 있음
		//Image ComboBox를 추가 구현 해보자
		for(j = 0; j<8 ; j++) {
			Choose[0][i] = new JComboBox<String>();
			Choose[0][i].setBounds(x+60*i,y+100,60,60);
			Choose[0][i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			player.add(Choose[0][i]);
		}
		for(j = 0; j<8 ; j++) {
			Choose[1][i] = new JComboBox<String>();
			Choose[1][i].setBounds(x+60*i,y+200,60,60);
			Choose[1][i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			player.add(Choose[0][i]);
		}
		
		Ready = new JButton("READY");
		Ready.setBounds(x,y,200,40);
		Ready.setFont(new Font("돋움",Font.BOLD,20));
		Ready.addMouseListener(this);
		player.add(Ready);
		
		RR = new JLabel("READY!!!");
		RR.setBounds(x, y, 40, 200);
		RR.setFont(new Font("돋움",Font.BOLD,20));
		player.add(RR);
		RR.setVisible(false);
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if((JButton)e.getSource() == Ready) {
			setReady();
			RR.setVisible(RUReady);
		}
	}
	
	public void setReady() {
		if(RUReady == true) {
			RUReady = false; 
		}
		else {
			RUReady = true;
		}
	}
	
	public boolean getRUReady() {
		return RUReady;
	}
	
	public JButton getReady() {
		return Ready;
	}
}
