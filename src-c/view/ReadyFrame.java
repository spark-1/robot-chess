package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Base;
import model.Game;
import model.Horse;
import model.King;
import model.Pawn;
import model.Rook;


public class ReadyFrame extends JFrame implements ActionListener,MouseListener{
	private PlayerFrame[] PF = new PlayerFrame[2];
	private JPanel WF;
	private int RUReady = 0; 
	private int w = 1000;
	private int h = 678;
	private BaseList B[];
	private Game g;
	public String[] ss;
	public String[] s1;
	public String[] s2;
	public Overseer OS;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ReadyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, w+10, h);
		B = new BaseList[5];
		B[0] = new BaseList("폰","인간형");
		B[1] = new BaseList("폰","기계형");
		B[2] = new BaseList("룩","인간형");
		B[3] = new BaseList("룩","기계형");
		B[4] = new BaseList("킹","인간형");
		
		g = new game();
		g.board[0][4] = new King(tmp1,tmp2,g.player[0].name,1-j,k);
		g.board[7][3] = new King(tmp1,tmp2,g.player[0].name,1-j,k);
		
		ss = new String[1];
		s1 = new String[3];
		s2 = new String[3];
		
		ss[0] = B[4].NT;
		
		s1[0] = "선택";
		s1[1] = B[0].NT;
		s1[2] = B[1].NT;
		
		s2[0] = "선택";
		s2[1] = B[2].NT;
		s2[2] = B[3].NT;
		
		WF = new JPanel();
		WF.setBorder(new EmptyBorder(5, 5, 5, 5));
		WF.setLayout(null);
		WF.setBounds(0,0,this.w,this.h);;
		setContentPane(WF);
		PF[0] = new PlayerFrame(0,0,1,ss,s1,s2);
		PF[0].setBounds(0,0,this.w/2,h);
		PF[0].setVisible(true);
		PF[0].addActionListener(this);
		
		PF[1] = new PlayerFrame(w/2,0,2,ss,s1,s2);
		PF[1].setBounds(this.w/2,0,this.w/2,h);
		PF[1].setVisible(true);
		PF[1].addActionListener(this);
		
		WF.add(PF[0]);
		WF.add(PF[1]);
		WF.addActionListener(this);
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tmp1 = "";
		String tmp2 = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		if( arg0.getSource() == PF[0].getReady() || arg0.getSource() == PF[1].getReady()) {
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
					WF.removeMouseListener(this);
					PF[0].removeMouseListener(this);
					PF[1].removeMouseListener(this);
					OS = new Overseer(this.g);
					OS.setVisible(true);
					setVisible(false);
				
			}
		}
		else {
			// TODO Auto-generated method stub
			for(i = 0 ; i < 2 ; i++) {
				for(j = 0 ; j < 2; j++) {
					for(k = 0; k < 8 ; k++) {
						if(e.getSource() == PF[i].Choose[j][k]) {
							for(l = 0; l < 5 ; l++) {
								if(B[l].NT == PF[i].Choose[j][k].getName()) {
									tmp1 = B[l].name;
									tmp2 = B[l].type;
								}
							}
							if(i == 0) {
								if(tmp1 == "룩") {
									g.board[1-j][k] = new Rook(tmp1,tmp2,g.player[0].name,1-j,k); 
								}
								else if(tmp1 == "킹") {
									if(g.board[1-j][k] == null) {
										g.board[1-j][k] = new King(tmp1,tmp2,g.player[0].name,1-j,k);
									}
								}
								else {
									g.board[1-j][k] = new Pawn(tmp1,tmp2,g.player[0].name,1-j,k);
								}
							}
							else {
								if(tmp1 == "룩") {
									g.board[6+j][7-k] = new Rook(tmp1,tmp2,g.player[0].name,6+j,7-k); 
								}
								else if(tmp1 == "킹") {
									if(g.board[6+j][7-k] == null) {
										g.board[6+j][7-k] = new King(tmp1,tmp2,g.player[0].name,6+j,7-k);
									}
								}
								else {
									g.board[6+j][7-k] = new Pawn(tmp1,tmp2,g.player[0].name,6+j,7-k);
								}
							}
						}
					}
				}
			}
		}
	}

}
