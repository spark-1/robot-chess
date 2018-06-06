import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class Test2 extends JPanel implements MouseListener{
	
	private JLabel Title;
	private JComboBox[][] Choose = new JComboBox[2][8];
	private JButton Ready;
	private JLabel RR;
	private int w = 1000;
	private int h = 678;
	private boolean RUReady = false;
	public String[] s = {"Test1","Test2"};
	
	public Test2(int x, int y, int i, Game g){
		String tmp;
		int j = 0;
		
		
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		tmp = Integer.toString(i);
		Title = new JLabel("Player"+tmp);
		Title.setBounds(x+((this.w/2)-180)/2, y+100, 180, 48);
		Title.setFont(new Font("나눔스퀘어라운드 EXTRABOLD",Font.BOLD,48));
		this.add(Title);
		
		
		//콤보박스를 만들어 주는 부분 추후 <instanceof를 활용하여 폰 룩의 콤보박스를 구별해줄 필요 있음
		//Image ComboBox를 추가 구현 해보자
		for(j = 0; j<8 ; j++) {
			Choose[0][j] = new JComboBox<String>(this.s);
			Choose[0][j].setBounds(x+60*(j-1)+10,y+300,60,60);
			Choose[0][j].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			this.add(Choose[0][j]);
		}
		for(j = 0; j<8 ; j++) {
			Choose[1][j] = new JComboBox<String>(this.s);
			Choose[1][j].setBounds(x+60*(j-1)+10,y+400,60,60);
			Choose[1][j].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			this.add(Choose[1][j]);
		}
		
		Ready = new JButton("READY");
		Ready.setBounds(x+((this.w/2)-200)/2,y+this.h - 140,200,40);
		Ready.setFont(new Font("돋움",Font.BOLD,20));
		Ready.addMouseListener(this);
		this.add(Ready);
		
		RR = new JLabel("READY!!!");
		RR.setBounds(x+((this.w/2)-90)/2, y+this.h - 200, 100, 40);
		RR.setFont(new Font("돋움",Font.BOLD,20));
		RR.setVisible(false);
		this.add(RR);
		
		
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

