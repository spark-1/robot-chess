package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Battle_Area extends JFrame{
	
	private JPanel mPane, bPane, sPane;
	private JButton[][] board = new JButton[8][8];
	private JButton attack, move, skill, cancel;
	private JLabel portrait, name, stat, turn;
	private JTextArea des;
	private JProgressBar hp;
	
	Color Brown = new Color(148, 87, 7);
	Color Beige = new Color(242, 184, 109);
	
	//이거 더 나눌수도 있음. Battle_Area는 생성자니까, 각 상황에 맞춰서, 응.
	public Battle_Area()
	{
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("로봇 체스");
		this.setSize(1000, 678);
		
		init_mPane();
		init_sPane();

	}
	
	public void init_sPane()
	{
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		sPane = new JPanel();
		mPane.add(sPane);
		sPane.setBounds(650, 0, 350, 678);
		sPane.setLayout(null);
		
		portrait = new JLabel();
		sPane.add(portrait);
		portrait.setBounds(670, 50, 100, 100);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
		
		name = new JLabel();
		sPane.add(name);
		name.setBounds(855, 50, 100, 30);
		name.setFont(new Font("Monospaced", Font.PLAIN, 20));
		name.setText("Test");
		
		hp = new JProgressBar(0, 50);
		hp.setBounds(800, 100, 150, 30);
		hp.setForeground(Color.green);
		hp.setValue(50);
		hp.setString("50/50");
		hp.setFont(new Font("Monospaced", Font.PLAIN, 15));
		hp.setStringPainted(true);
		sPane.add(hp);
		
		attack = new JButton("공격하기");
		attack.setBackground(Color.gray);
		sPane.add(attack);
		attack.setBounds(670, 200, 100, 70);
		attack.addMouseListener(new MListener());
		
		move = new JButton("움직이기");
		move.setBackground(Color.gray);
		sPane.add(move);
		move.setBounds(670, 300, 100, 70);
		move.addMouseListener(new MListener());
		
		skill = new JButton("스킬사용");
		skill.setBackground(Color.gray);
		sPane.add(skill);
		skill.setBounds(670, 400, 100, 70);
		skill.addMouseListener(new MListener());
		
		cancel = new JButton("취소");
		cancel.setBackground(Color.gray);
		sPane.add(cancel);
		cancel.setBounds(670, 500, 100, 70);
		cancel.setEnabled(false);
		//cancel.addActionListener(new AListener());
		
		stat = new JLabel("Atk: - , Def: - ");
		sPane.add(stat);
		stat.setFont(new Font("Monospaced", Font.PLAIN, 20));
		stat.setBounds(800, 130, 200, 100);
		
		des = new JTextArea();
		sPane.add(des);
		des.setBounds(800, 300, 150, 100);
		des.setEditable(false);
		des.setFont(new Font("Monospaced", Font.PLAIN, 15));
		des.setOpaque(false);
		
		turn = new JLabel();
		sPane.add(turn);
		turn.setBounds(670, 590, 100, 30);
		turn.setFont(new Font("Monospaced", Font.PLAIN, 20));
		turn.setText("-의 차례");
	}
	
	public void init_mPane()
	{
		mPane = new JPanel();
		mPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(mPane);
		
		bPane = new JPanel();
		mPane.add(bPane);
		bPane.setBounds(0,0,650,678);
		bPane.setLayout(null);
		
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				board[i][j] = new JButton();
				if((i+j)%2 == 0) {board[i][j].setBackground(Brown);}
				else{board[i][j].setBackground(Beige);}
				bPane.add(board[i][j]);
				board[i][j].setBounds(i*80, j*80, 80, 80);
				//board[i][j].addActionListener(new AListener());
			}
		}
	}
	
	//이 함수들은 control에서 정보를 받아와서 실행되게 됨.
	public void set_mPane()
	{
		//보드쪽을 갱신
	}
	
	public void set_sPane()
	{
		//status쪽을 갱신
	}
	
	public void set_turn()
	{
		//턴을 바꿀 때 사용할 것.
	}
	
	public void light_movable()
	{
		//움직일 수 있는 곳을 mPane의 체스판 위에 표시
		//danmaku bullets 쳐서 거기서 나오는 이미지를 쓰자
	}
	
	public void light_attackable()
	{
		//공격할 수 있는 곳을 mPane의 체스판 위에 표시
		//danmaku bullets 쳐서 거기서 나오는 이미지를 쓰자
	}
	
	private class AListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class MListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == attack)
			{
				des.append("범위내의 적을 --의 공격력으로 통상공격합니다.");
			}
			if(e.getSource() == move)
			{
				des.append("이동할 수 있는 범위 내의 칸으로 이동합니다.");
			}
			if(e.getSource() == skill)
			{
				des.append("말의 특수능력을 사용합니다.(이 부분은 말에서 받아올 예정)");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == attack || e.getSource() == move || e.getSource() == skill)
			{
				des.setText("");
			}
		}
	
	}
	
}
