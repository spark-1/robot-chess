import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.Game;


public class Test3 extends JFrame implements MouseListener{
	private PlayerFrame[] PF = new PlayerFrame[2];
	private JPanel WF;
	private int RUReady = 0; 
	private int w = 1000;
	private int h = 678;
	private Game g;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Test3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, w, h);
		WF = new JPanel();
		WF.setBorder(new EmptyBorder(5, 5, 5, 5));
		WF.setLayout(null);
		WF.setBounds(0,0,this.w,this.h);;
		setContentPane(WF);
		PF[0] = new PlayerFrame(0,0,1,g);
		PF[0].setBounds(0,0,this.w/2,h);
		PF[0].setVisible(true);
		
		PF[1] = new PlayerFrame(w/2,0,2,g);
		PF[1].setBounds(this.w/2,0,this.w/2,h);
		PF[1].setVisible(true);
		
		WF.add(PF[0]);
		WF.add(PF[1]);
		
		
		this.setVisible(true);
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
		if( e.getSource() == PF[0].getReady() || e.getSource() == PF[1].getReady()) {
			boolean tmp = false;
			RUReady = 0;
			tmp = PF[0].getRUReady();
			if(tmp == true) {
				RUReady++;
			}
			tmp  = PF[1].getRUReady();
			if(tmp == true) {
				RUReady++;
			}
			if(RUReady == 2) {
				
					Battle_Area BA = new Battle_Area(this.g);
					BA.setVisible(true);
					setVisible(false);
				
			}
		}
	}

}
