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
import model.Pawn;
import model.Rook;

public class Battle_Area extends JFrame{
	//배틀 에리어는 하나의 프레임입니다.
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
			new ImageIcon("Siege_S.png")         //10
	}; //setIcon때 쓸 icon들을 전부 모아놓았어요. 이녀석은 프로젝트 파일 안에 저 파일명의 그림이 있으면 그 그림을 가지게 된답니다.
	
	private JLayeredPane pane = new JLayeredPane(); //pane은 레이어드페인이에요. 이 아이덕분에 패널 여러개를 겹쳐놓을 수 있답니다.
	private JPanel bPane, sPane, tPane;
	private JLabel[][] point = new JLabel[8][8];
	private JButton[][] board = new JButton[8][8];
	private JButton attack, move, skill, cancel;
	private JLabel portrait, name, stat, turn;
	private JTextArea des;
	private JProgressBar hp;
	//먼저 사용할 컴포넌트들을 맨 앞에 정의해 놓았어요. 잘 보시면 프라이빗 변수라 게터를 맨 아랫쪽에 만들어 놓았답니다.
	Color Brown = new Color(148, 87, 7);
	Color Beige = new Color(242, 184, 109);
	//컬러를 커스터마이징 해 놓았어요! RGB값으로 내가 원하는 색깔을 만들고 저장할 수 있답니다.
	public Battle_Area()
	{	
		this.setResizable(false); //setResizable은 유저가 창크기를 조절하지 못하게 한답니다.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창이 닫히면 컴파일러도 정지되어요.
		this.setTitle("로봇 체스"); //창의 이름을 결정해 주어요.
		this.setSize(1000, 678); //창의 크기를 결정해 주어요.
		this.add(pane);
		this.setContentPane(pane); //pane이라는 아이를 contentPane으로 선언해 주었어요.
		
		//Battle_area에는 세가지 패널이 있어요! 이 아이들을 초기화 해주는게 init_함수들이랍니다!
		//아래쪽 함수들에서 차근차근 설명해 드릴께요.
		init_bPane(); 
		init_sPane();
		init_tPane();
		//이제 Battle_Area창을 visible하게 해줍시다! 이걸로 생성자가 끝났어요!
		this.setVisible(true);
	}
	
	//sPane, statusPane의 약자에요. 이 아이는 오른쪽에 출력되는 상태를 담당하는 패널이랍니다.
	public void init_sPane()
	{
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		//progressBar의 텍스트색깔을 바꿔주기 위한 처리에요!
		
		sPane = new JPanel(); //먼제 sPane을 선언해주고,
		pane.add(sPane, new Integer(30)); //pane에 넣습니다. 뒤에 붙는 Integer값이 작으면 작을 수록 아래에 깔리게 되어요
		sPane.setBounds(650, 0, 350, 678);
		sPane.setLayout(null);
		
		portrait = new JLabel(); 
		sPane.add(portrait);
		portrait.setBounds(20, 50, 100, 100);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black)); //초상화용 label이에요. Borderline을
																		 //고의적으로 넣어주었어요. 보기좋게!
		name = new JLabel();
		sPane.add(name);
		name.setBounds(205, 50, 100, 30);
		name.setFont(new Font("Monospaced", Font.PLAIN, 20));  //이건 말의 이름을 표시하기위한 label
		
		hp = new JProgressBar(0, 50);
		hp.setBounds(150, 100, 150, 30);
		hp.setForeground(Color.green);
		hp.setValue(50);
		hp.setString("50/50");
		hp.setFont(new Font("Monospaced", Font.PLAIN, 15));
		hp.setStringPainted(true);
		sPane.add(hp); //hp용 progressbar
		
		setAttack(new JButton("공격하기"));
		getAttack().setBackground(Color.gray);
		sPane.add(getAttack());
		getAttack().setBounds(20, 200, 100, 70);
		getAttack().setEnabled(false);
		
		setMove(new JButton("움직이기"));
		getMove().setBackground(Color.gray);
		sPane.add(getMove());
		getMove().setBounds(20, 300, 100, 70);
		getMove().setEnabled(false);
		
		setSkill(new JButton("스킬사용"));
		getSkill().setBackground(Color.gray);
		sPane.add(getSkill());
		getSkill().setBounds(20, 400, 100, 70);
		getSkill().setEnabled(false);
		
		setCancel(new JButton("취소"));
		getCancel().setBackground(Color.gray);
		sPane.add(getCancel());
		getCancel().setBounds(20, 500, 100, 70);
		getCancel().setEnabled(false); //행동용 버튼들 추가시켜주고
		
		stat = new JLabel("Atk: - , Def: - ");
		sPane.add(stat);
		stat.setFont(new Font("Monospaced", Font.PLAIN, 20));
		stat.setBounds(150, 130, 200, 100); //label도 넣어봤어요.
		
		setDes(new JTextArea());
		sPane.add(getDes());
		getDes().setBounds(150, 300, 150, 100);
		getDes().setLineWrap(true);
		getDes().setEditable(false);
		getDes().setFont(new Font("Monospaced", Font.PLAIN, 15));
		getDes().setOpaque(false); //버튼에 마우스를 가져다 대면 설명이 출력되게 할 거에요.
		
		turn = new JLabel();
		sPane.add(turn);
		turn.setBounds(20, 590, 200, 30);
		turn.setFont(new Font("Monospaced", Font.PLAIN, 20));
		turn.setText("--------의 차례"); //누구 턴인지 확인시켜주는 label
	}
	
	//bPane, boardPane의 약자에요. 이 아이는 왼쪽에 출력되는 보드를 담당하는 패널이랍니다.
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
				else{getBoard()[i][j].setBackground(Beige);} //2의 나머지는 0과 1밖에 없다는 것을 이용해서 색을 바꿔줘요
				bPane.add(getBoard()[i][j]);
				getBoard()[i][j].setBounds(j*80, i*80, 80, 80); //i와 j에 따라서 좌표를 정해줘요.
				
			}
		}
	}
	
	//tPane은 testPane의 약자에요. 실험으로 해보다 성공했답니다.
	public void init_tPane()
	{
		tPane = new JPanel();
		pane.add(tPane, new Integer(20)); //숫자가 bPane보다 위쪽이라서 bPane보다 위쪽에 위치해요.
		tPane.setBounds(0,0,650,678);
		tPane.setOpaque(false); //Opaque가 false라 투명한 panel이에요.
		tPane.setLayout(null);
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				point[i][j] = new JLabel();
				point[i][j].setVisible(false);
				tPane.add(point[i][j]);

				point[i][j].setBounds(20 + j*80, 20 + i*80, 40, 40);
			} //attack, movepoint를 위한 label들이에요.
		}
	}
	
	//이 함수들은 control에서 정보를 받아와서 실행되게 됩니다.
	
	public void set_bPane(Game game)
	{//Game을 받아서 보드를 바꾸는 함수에요.
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				if(game.board[i][j] != null)
				{
					if(game.board[i][j].master == game.player[0].name) //플레이어가 누구냐에 따라서 pic이 달라져요
					{
						if(game.board[i][j] instanceof Pawn)
						{//board[i][j]에 있는게 pawn이면  pawn을
							getBoard()[i][j].setIcon(pic[2]);
						}
						else if(game.board[i][j] instanceof Rook)
						{//Rook이면 rook을
							getBoard()[i][j].setIcon(pic[5]);
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
							getBoard()[i][j].setIcon(pic[6]);
						}
					}
				}
				else
				{//아무도 없으면 지워줘요.
					getBoard()[i][j].setIcon(null);
				}
			}
		}
	}
	
	public void set_sPane(Base b)
	{//sPane을 바꿔보아요.
		if(b instanceof Pawn)
		{
			portrait.setIcon(pic[4]);
		}
		else if(b instanceof Rook)
		{
			portrait.setIcon(pic[7]);
		}
		
		name.setText(b.name);
		
		hp.setMaximum(500);
		hp.setMinimum(0);
		hp.setValue(b.hp);
		hp.setString(b.hp+"/500");

		getAttack().setEnabled(true);
		getMove().setEnabled(true);
		getSkill().setEnabled(true);
		getCancel().setEnabled(false);
		
		stat.setText("Atk: "+b.atk+" , Def: "+b.def);
	}
	
	public void reset_sPane()
	{//아무것도 선택되지 않았을때 쓰는 함수에요.
		portrait.setIcon(null);
		name.setText("정보없음");
		
		hp.setMaximum(0);
		hp.setMinimum(0);
		hp.setValue(0);
		hp.setString("");

		getAttack().setEnabled(false);
		getMove().setEnabled(false);
		getSkill().setEnabled(false);
		getCancel().setEnabled(false);
		
		stat.setText("정보없음");
	}
	
	public void set_turn(Game game)
	{ //턴을 알려줄때 써요.
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
	{//움직일 수 있는곳에 setIcon.
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[1]);
	}
	
	public void light_attackable(int i, int j)
	{//공격할 수 있는 곳에 setIcon
		point[i][j].setVisible(true);
		point[i][j].setIcon(pic[0]);
	}
	
	public void dislight()
	{//위의 tPane위에 있을 녀석들을 다 지워주기
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				point[i][j].setVisible(false);
			}
		}
	}
	//여기서부터는 getter setter에요.
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

	public JButton getSkill() {
		return skill;
	}

	public void setSkill(JButton skill) {
		this.skill = skill;
	}

	public void set_sPane_guest(Base b) {
		set_sPane(b);
		getAttack().setEnabled(false);
		getMove().setEnabled(false);
		getSkill().setEnabled(false);
		getCancel().setEnabled(false);
	}
	

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
}
