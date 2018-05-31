package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import view.Battle_Area;

public class Overseer implements ActionListener, MouseListener{
	//이 아이는 컨트롤러에요. Game과 Battle_Area를 둘 다 가지고 있어서 중계자 역할을 한답니다.
	//ActionListener랑 MouseListener를 implement한 아이에요.
	Game game = null;
	Battle_Area ba = null;
	int state;
	//지금 무얼 할 것인지를 말해주는 변수에요. 0이면 정보를 보고 있는 것, 1이면 공격, 2면 움직임, 3이면 스킬을 쓰고 있는거에요.
	public Overseer(Game g)
	{//Game을 받아서...
		ba = new Battle_Area();//우선 battle_area생성해주고
		for(int i = 0;i<8; i++)
		{
			for(int j = 0;j<8;j++)
			{
				ba.getBoard()[i][j].addActionListener(this);
			}
		}//board들에게 actionlistener 넣어주고
		ba.getAttack().addMouseListener(this);
		ba.getAttack().addActionListener(this);
		ba.getMove().addMouseListener(this);
		ba.getMove().addActionListener(this);
		ba.getSkill().addMouseListener(this);
		ba.getSkill().addActionListener(this);
		ba.getCancel().addMouseListener(this);
		ba.getCancel().addActionListener(this);
		//battle_area의 컴포넌트들에게 리스너들을 다 넣어줘요.
	
		this.game = g;
		//게임 set해주고.
		
		ba.set_bPane(g);
		ba.set_turn(g);
		state = 0;
		//그에 맞춰서 set해주고.
	}
	//애들은 아직 안써요.
	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		if(game.turn == 1) //마우스가 들어가면 print_deses를 해줘요. 뒤에 나온답니다.
		{
			print_deses(e, 0);
		}
		else
		{
			print_deses(e, 1);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == ba.getAttack() || e.getSource() == ba.getMove() || e.getSource() == ba.getSkill() || e.getSource() == ba.getCancel())
		{//마우스가 밖으로 나가면 des를 다시 깨끗하게 해줘요.
			ba.getDes().setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//이건 보드의 버튼들의 액션을 받는거에요.
		for(int i =0; i<8;i++)
		{
			for(int j =0;j<8;j++)
			{
				if(e.getSource() == ba.getBoard()[i][j] && this.state == 0)
				{
					if(game.board[i][j] != null)
					{
						if(game.turn == 1)
						{
							if(game.board[i][j].master == game.player[0].name)
							{
								game.player[0].control(game.board[i][j]);
								ba.set_sPane(game.player[0].get_Base());
							}
							else
							{
								ba.set_sPane_guest(game.board[i][j]);
							}
						}
						else
						{
							if(game.board[i][j].master == game.player[1].name)
							{
								game.player[1].control(game.board[i][j]);
								ba.set_sPane(game.player[1].get_Base());
							}
							else
							{
								ba.set_sPane_guest(game.board[i][j]);
							}
						}
					}
					else
					{
						game.player[0].set_Base(null);
						ba.reset_sPane();
						//아무것도 없는곳을 클릭하면 sPane을 리셋해줘요.
					}
				}
				//행동을 취하려고 할 때 보드버튼을 누르면 발생하는 이벤트들이에요.
				//잘 보면 알겠지만, game의 함수들을 쓰고 있어요.
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 1)
				{
					if(game.turn == 1)
					{
						game.attack(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);
					}
					else
					{
						game.attack(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
					}
					//함수들이 끝나면 정리용 함수를 써줘요. 이것도 뒤에서 나와요.
					ready_for_input();
				}
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 2)
				{
					if(game.turn == 1)
					{
						game.move(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);
					}
					else
					{
						game.move(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
					}
					ready_for_input();
				}
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 3)
				{
					if(game.turn == 1)
					{
						game.skill(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);
					}
					else
					{
						game.skill(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
					}
					ready_for_input();
				}
			}
		}
		///////////////////////////////////
		//여기서부터는 공격, 움직임, 스킬 버튼이에요.
		int point[][] = new int[8][8];
		//이걸로 game에 추가한 attackpoint movepoint함수의 값을 받아서 어디다가 light_attackable, light_moveable을 할 지 결정해요.
		if(e.getSource() == ba.getAttack())
		{
			shift_state(1);
			//shitf_state는 현재 무엇을 할 지를 바꿔주는 함수에요. 뒤에 나와요.
			if(game.turn == 1)
			{
				point = game.attackpoint(game.player[0].get_Base());
			}
			else
			{
				point = game.attackpoint(game.player[1].get_Base());
			}
			
			for(int i = 0; i< 8; i++)
			{
				for(int j = 0; j<8; j++)
				{
					if(point[i][j] == 1)
					{
						ba.light_attackable(i, j);
					}
					
				}
			}
		}
		else if(e.getSource() ==ba.getMove())
		{
			shift_state(2);
			
			if(game.turn == 1)
			{
				point = game.movepoint(game.player[0].get_Base());
			}
			else
			{
				point = game.movepoint(game.player[1].get_Base());
			}
			
			for(int i = 0; i< 8; i++)
			{
				for(int j = 0; j<8; j++)
				{
					if(point[i][j] == 1 && game.board[i][j] == null)
					{
						ba.light_moveable(i, j);
					}
					
				}
			}
		}
		else if(e.getSource() ==ba.getSkill())
		{
			shift_state(3);

			if(game.turn == 1)
			{
				point = game.attackpoint(game.player[0].get_Base());
			}
			else
			{
				point = game.attackpoint(game.player[1].get_Base());
			}
			
			for(int i = 0; i< 8; i++)
			{
				for(int j = 0; j<8; j++)
				{
					if(point[i][j] == 1)
					{
						ba.light_attackable(i, j);
					}
					
				}
			}
		}
		else if(e.getSource() ==ba.getCancel())
		{
			shift_state(0);
		}
		
	}

	public void print_deses(MouseEvent e, int num) //플레이어가 아무 베이스도 가지고 있지 않는 경우를 제외하면 des를 append해줘요.
	{
		if(game.player[num].get_Base() != null)
		{
			if(e.getSource() == ba.getAttack())
			{
				ba.getDes().append("범위내의 적을 "+game.player[num].get_Base().atk + "의 공격력으로 통상공격합니다.");
			}
			if(e.getSource() == ba.getMove())
			{
				ba.getDes().append("이동할 수 있는 범위 내의 칸으로 이동합니다.");
			}
			if(e.getSource() == ba.getSkill())
			{
				ba.getDes().append("말의 특수능력을 사용합니다: " +game.player[num].get_Base().des);
			}
			if(e.getSource() == ba.getCancel())
			{
				ba.getDes().append("현재 하려던 행동을 취소합니다.");
			}
		}
	}
	
	public void ready_for_input()
	{//bPane바꿔주고, 불 다 끄고, state 0으로 만들고, 턴 바꿔주고, sPane 리셋해줘요.
		ba.set_bPane(this.game);
		ba.dislight();
		this.state = 0;
		ba.set_turn(this.game);
		ba.reset_sPane();
	}
	
	public void shift_state(int num)
	{
		if(num == 0)
		{//cancel일 때는 불 다 끄고, state 0으로 만들고, 버튼들을 cancel제외하고 enable해줘요.
			ba.dislight();
			ba.getAttack().setEnabled(true);
			ba.getMove().setEnabled(true);
			ba.getSkill().setEnabled(true);
			ba.getCancel().setEnabled(false);	
		}
		else
		{//0이 아니면 state를 num으로 바꾸고 cancel빼고 enable false로 해줘요.
			ba.getAttack().setEnabled(false);
			ba.getMove().setEnabled(false);
			ba.getSkill().setEnabled(false);
			ba.getCancel().setEnabled(true);
		}
		state = num;
	}
	
}
