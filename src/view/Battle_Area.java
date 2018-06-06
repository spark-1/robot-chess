package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import model.Base;
import model.Game;
import model.King;
import model.Pawn;
import model.Rook;
import model.Base;
import model.Equipment;

public class Battle_Area extends JFrame{
	
	public ImageIcon[] pic = {
			new ImageIcon("attack_point_o.png"), //0
			new ImageIcon("move_point_o.png"),   //1
			new ImageIcon("Pawn_R.png"),         //2
			new ImageIcon("Pawn_L.png"),         //3 
			new ImageIcon("Pawn_S.png"),         //4
			new ImageIcon("Move_R.png"),         //5
			new ImageIcon("Move_L.png"),         //6
			new ImageIcon("Move_S.png"),         //7
			new ImageIcon("Siege_L.png"),        //8
			new ImageIcon("Siege_R.png"),        //9
			new ImageIcon("Siege_S.png"),        //10
			new ImageIcon("King.png"),           //11
			new ImageIcon("King_S.png"),         //12
			new ImageIcon("skill_point.png"),    //13
			new ImageIcon("Boom.gif")            //14 
	}; 
	
	private JLayeredPane pane = new JLayeredPane(); 
	private JPanel bPane, sPane, tPane;
	private JLabel[][] point = new JLabel[8][8];
	private JButton[][] board = new JButton[8][8];
	private JButton attack, move, passkill, actskill, cancel;
	private JLabel portrait, name, stat,turn;
	private JTextArea equip, des;
	private JProgressBar hp;
	
	Color Brown = new Color(148, 87, 7);
	Color Beige = new Color(242, 184, 109);
	
	public Battle_Area()
	{	
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("로봇 체스");
		this.setSize(1000, 678);
		this.add(pane);
		this.setContentPane(pane); 
		
		init_bPane(); 
		init_sPane();
		init_tPane();
		
		this.setVisible(true);
	}
	
	public void init_sPane()
	{
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		
		sPane = new JPanel();
		pane.add(sPane, new Integer(30));
		sPane.setBounds(650, 0, 350, 678);
		sPane.setLayout(null);
		
		portrait = new JLabel(); 
		sPane.add(portrait);
		portrait.setBounds(20, 30, 100, 100);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
																		
		name = new JLabel();
		sPane.add(name);
		name.setBounds(175, 30, 100, 30);
		name.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		hp = new JProgressBar(0, 50);
		hp.setBounds(150, 75, 150, 30);
		hp.setForeground(Color.green);
		hp.setValue(50);
		hp.setString("50/50");
		hp.setFont(new Font("Monospaced", Font.PLAIN, 15));
		hp.setStringPainted(true);
		sPane.add(hp);
		
		setAttack(new JButton("공격하기"));
		getAttack().setBackground(Color.gray);
		sPane.add(getAttack());
		getAttack().setBounds(20, 200, 100, 60);
		getAttack().setEnabled(false);
		
		setMove(new JButton("움직이기"));
		getMove().setBackground(Color.gray);
		sPane.add(getMove());
		getMove().setBounds(20, 280, 100, 60);
		getMove().setEnabled(false);
		
		setPasSkill(new JButton("패시브스킬"));
		getPasSkill().setBackground(Color.gray);
		sPane.add(getPasSkill());
		getPasSkill().setBounds(20, 360, 100, 60);
		getPasSkill().setEnabled(false);
		
		setActSkill(new JButton("액티브스킬"));
		getActSkill().setBackground(Color.gray);
		sPane.add(getActSkill());
		getActSkill().setBounds(20, 440, 100, 60);
		getActSkill().setEnabled(false);
		
		setCancel(new JButton("취소"));
		getCancel().setBackground(Color.gray);
		sPane.add(getCancel());
		getCancel().setBounds(20, 520, 100, 60);
		getCancel().setEnabled(false);
		
		stat = new JLabel("Atk: -, Def: - ");
		sPane.add(stat);
		stat.setFont(new Font("Monospaced", Font.PLAIN, 20));
		stat.setBounds(20, 120, 300, 100);
		
		equip = new JTextArea("장비가 들어갈 곳.");
		sPane.add(equip);
		equip.setFont(new Font("Monospaced", Font.PLAIN, 17));
		equip.setBounds(150, 220, 150, 150);
		equip.setLineWrap(true);
		equip.setEditable(false);
		equip.setOpaque(false);
		
		setDes(new JTextArea());
		sPane.add(getDes());
		getDes().setBounds(150, 350, 150, 250);
		getDes().setLineWrap(true);
		getDes().setEditable(false);
		getDes().setFont(new Font("Monospaced", Font.PLAIN, 18));
		getDes().setOpaque(false);
		
		turn = new JLabel();
		sPane.add(turn);
		turn.setBounds(20, 595, 200, 30);
		turn.setFont(new Font("Monospaced", Font.PLAIN, 20));
		turn.setText("--------의 차례");
	}
	
	public void init_bPane()
	{
		bPane = new JPanel();
		pane.add(bPane, new Integer(10));
		bPane.setBounds(0,0,650,678);
		bPane.setLayout(null);
		
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				getBoard()[i][j] = new JButton();
				if((i+j)%2 == 0) {getBoard()[i][j].setBackground(Brown);}
				else{getBoard()[i][j].setBackground(Beige);}
				bPane.add(getBoard()[i][j]);
				getBoard()[i][j].setBounds(j*80, i*80, 80, 80);
				
			}
		}
	}
	
	public void init_tPane()
	{
		tPane = new JPanel();
		pane.add(tPane, new Integer(20));
		tPane.setBounds(0,0,650,678);
		tPane.setOpaque(false);
		tPane.setLayout(null);
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				point[i][j] = new JLabel();
				point[i][j].setVisible(false);
				tPane.add(point[i][j]);

				point[i][j].setBounds(20 + j*80, 20 + i*80, 40, 40);
			}
		}
	}
	
	public void set_bPane(Game game)
	{
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				if(game.board[i][j] != null)
				{
					if(game.board[i][j].master == game.player[0].name)
					{
						if(game.board[i][j] instanceof Pawn)
						{
							getBoard()[i][j].setIcon(pic[2]);
						}
						else if(game.board[i][j] instanceof Rook)
						{
							if(((Rook)game.board[i][j]).mv == null)
							{
								getBoard()[i][j].setIcon(pic[9]);
							}
							else
							{
								getBoard()[i][j].setIcon(pic[5]);
							}
							
						}
						else if(game.board[i][j] instanceof King)
						{
							getBoard()[i][j].setIcon(pic[11]);
						}
					}
					else
					{
						if(game.board[i][j] instanceof Pawn)
						{
							getBoard()[i][j].setIcon(pic[3]);
						}
						else if(game.board[i][j] instanceof Rook)
						{
							if(((Rook)game.board[i][j]).mv == null)
							{
								getBoard()[i][j].setIcon(pic[8]);
							}
							else
							{
								getBoard()[i][j].setIcon(pic[6]);
							}
						}
						else if(game.board[i][j] instanceof King)
						{
							getBoard()[i][j].setIcon(pic[11]);
						}
					}
				}
				else
				{
					getBoard()[i][j].setIcon(null);
				}
			}
		}
	}
	
	public void set_sPane(Base b)
	{
		if(b instanceof Pawn)
		{
			portrait.setIcon(pic[4]);
		}
		else if(b instanceof Rook)
		{
			if(((Rook)b).mv == null)
			{
				portrait.setIcon(pic[10]);
			}
			else
			{
				portrait.setIcon(pic[7]);
			}
		}
		else if(b instanceof King)
		{
			portrait.setIcon(pic[12]);
		}
		
		name.setText(b.type +" "+ b.name);
		
		hp.setMaximum(b.maxHp);
		hp.setMinimum(0);
		hp.setValue(b.hp);
		hp.setString(b.hp+"/"+b.maxHp);

		getAttack().setEnabled(true);
		getMove().setEnabled(true);
		getPasSkill().setEnabled(true);
		getActSkill().setEnabled(true);
		getCancel().setEnabled(false);
		
		stat.setText("Atk: "+b.atk+" , Def: "+b.def);
		equip.setText("");
		if(b.e[0] == null)
		{
			equip.append("Armor: 없음\n");
		}
		else
		{
			equip.append("Armor:"+b.e[0].equip_type+"\n");
		}
		
		if(b.e[1] == null)
		{
			equip.append("Weapon: 없음\n");
		}
		else
		{
			equip.append("Weapon:"+b.e[1].equip_type+"\n");
		}
		
		if(b.e[2] == null)
		{
			equip.append("Leg: 없음");
		}
		else
		{
			equip.append("Leg:" +b.e[2].equip_type);
		}
		
	}
	
	public void reset_sPane()
	{
		portrait.setIcon(null);
		name.setText("정보없음");
		
		hp.setMaximum(0);
		hp.setMinimum(0);
		hp.setValue(0);
		hp.setString("");

		getAttack().setEnabled(false);
		getMove().setEnabled(false);
		getPasSkill().setEnabled(false);
		getActSkill().setEnabled(false);
		getCancel().setEnabled(false);
		
		stat.setText("정보없음");
		equip.setText("정보없음\n정보없음\n정보없음");
	}
	
	public void set_sPane_guest(Base b) {
		set_sPane(b);
		getAttack().setEnabled(false);
		getMove().setEnabled(false);
		getPasSkill().setEnabled(false);
		getActSkill().setEnabled(false);
		getCancel().setEnabled(false);
	}
	
	
	public void set_turn(Game game)
	{
		if(Game.turn == -1)
		{
			turn.setText(game.player[1].name + "의 턴");
		}
		else
		{
			turn.setText(game.player[0].name + "의 턴");
		}
	}
	
	public void light_moveable(int i, int j)
	{
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[1]);
	}
	
	public void light_attackable(int i, int j)
	{
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[0]);
	}
	
	public void light_skillable(int i, int j)
	{
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[13]);
	}
	
	public void light_hit(int i, int j)
	{
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[14]);
	}
	
	public void dislight()
	{
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				point[i][j].setVisible(false);
			}
		}
	}
	
	public JButton getAttack() {
		return attack;
	}

	public void setAttack(JButton attack) {
		this.attack = attack;
	}

	public JButton[][] getBoard() {
		return board;
	}

	public void setBoard(JButton[][] board) {
		this.board = board;
	}

	public JTextArea getDes() {
		return des;
	}

	public void setDes(JTextArea des) {
		this.des = des;
	}

	public JButton getMove() {
		return move;
	}

	public void setMove(JButton move) {
		this.move = move;
	}

	public JButton getPasSkill() {
		return passkill;
	}

	public void setPasSkill(JButton skill) {
		this.passkill = skill;
	}

	public JButton getActSkill() {
		return this.actskill;
	}
	
	public void setActSkill(JButton skill){
		this.actskill = skill;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
}
