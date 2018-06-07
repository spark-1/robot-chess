package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.Ost;
import model.Base;
import model.Game;
import model.King;
import model.Pawn;
import model.Rook;
import view.Battle_Area;
import view.Boom;
import view.Endgame;

public class Overseer implements ActionListener, MouseListener{
	Game game = null;
	Battle_Area ba = null;
	Endgame eg = null;
	Thread boom = null;
	Ost ost = Ost.getInstance();
	
	int state;
	public Overseer(Game g)
	{
		ba = new Battle_Area();
		for(int i = 0;i<8; i++)
		{
			for(int j = 0;j<8;j++)
			{
				ba.getBoard()[i][j].addActionListener(this);
			}
		}
		ba.getAttack().addMouseListener(this);
		ba.getAttack().addActionListener(this);
		ba.getMove().addMouseListener(this);
		ba.getMove().addActionListener(this);
		ba.getPasSkill().addMouseListener(this);
		ba.getPasSkill().addActionListener(this);
		ba.getActSkill().addMouseListener(this);
		ba.getActSkill().addActionListener(this);
		ba.getCancel().addMouseListener(this);
		ba.getCancel().addActionListener(this);

		this.game = g;

		ba.set_bPane(g);
		ba.set_turn(g);
		ba.reset_sPane();
		state = 0;
		ost.playMusic(ost.background);
	}

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
		if(game.turn == 1)
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
		if(e.getSource() == ba.getAttack() || e.getSource() == ba.getMove() || e.getSource() == ba.getPasSkill() || e.getSource() == ba.getActSkill() || e.getSource() == ba.getCancel())
		{
			ba.getDes().setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
								check_state(game.player[0].get_Base());	
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
								check_state(game.player[1].get_Base());	
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
					}
				}
				
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 1)
				{
					int t = game.turn;
					if(t == 1)
					{
						game.attack(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);
						play_sfx(game.player[0].get_Base(), t, game.turn, this.state);
					}
					else
					{
						game.attack(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
						play_sfx(game.player[1].get_Base(), t, game.turn, this.state);
					}
					
					ready_for_input();
				}
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 2)
				{
					int t = game.turn;
					if(t == 1)
					{
						game.move(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);
						play_sfx(game.player[0].get_Base(), t, game.turn, this.state);
					}
					else
					{
						game.move(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
						play_sfx(game.player[1].get_Base(), t, game.turn, this.state);
					}
					ready_for_input();
				}
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 3)
				{
					int t = game.turn;
					if(t == 1)
					{
						if(i == game.player[0].get_Base().locX && j == game.player[0].get_Base().locY)
						{
							game.skill(game.player[0].get_Base().locX, game.player[0].get_Base().locY);
							play_sfx(game.player[0].get_Base(), t, game.turn, this.state);
						}
						else
						{
							System.out.println("말을 잘못 선택하셨습니다.");
						}
					}
					else
					{
						if(game.board[i][j] == game.player[1].get_Base())
						{
							game.skill(game.player[1].get_Base().locX, game.player[1].get_Base().locY);
							play_sfx(game.player[1].get_Base(), t, game.turn, this.state);
						}
						else
						{
							System.out.println("말을 잘못 선택하셨습니다.");
						}		
					}
					ready_for_input();
				}
				else if(e.getSource() == ba.getBoard()[i][j] && this.state == 4)
				{
					int t = game.turn;
					if(t == 1)
					{	
						game.skill(game.player[0].get_Base().locX, game.player[0].get_Base().locY, i, j);	
						play_sfx(game.player[0].get_Base(), t, game.turn, this.state);
					}
					else
					{
						game.skill(game.player[1].get_Base().locX, game.player[1].get_Base().locY, i, j);
						play_sfx(game.player[1].get_Base(), t, game.turn, this.state);
					}
					
					ready_for_input();
				}
			}
		}
		///////////////////////////////////

		int point[][] = new int[8][8];

		if(e.getSource() == ba.getAttack())
		{
			shift_state(1);

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
		else if(e.getSource() == ba.getMove())
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
		else if(e.getSource() ==ba.getPasSkill())
		{
			shift_state(3);

			if(game.turn == 1)
			{
				point = game.passkillpoint(game.player[0].get_Base());
			}
			else
			{
				point = game.passkillpoint(game.player[1].get_Base());
			}
			
			for(int i = 0; i< 8; i++)
			{
				for(int j = 0; j<8; j++)
				{
					if(point[i][j] == 1)
					{
						ba.light_skillable(i, j);
					}
					
				}
			}
		}
		
		else if(e.getSource() == ba.getActSkill())
		{
			shift_state(4);

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
						ba.light_skillable(i, j);
					}
					
				}
			}
		}

		else if(e.getSource() ==ba.getCancel())
		{
			shift_state(0);
			if(game.turn == 1)
			{
				check_state(game.player[0].get_Base());
			}
			else
			{
				check_state(game.player[1].get_Base());
			}	
		}
	}

	public void print_deses(MouseEvent e, int num)
	{
		if(((JButton)e.getSource()).isEnabled() == true && game.player[num].get_Base() != null)
		{
			if(e.getSource() == ba.getAttack())
			{
				ba.getDes().append("범위내의 적을 "+game.player[num].get_Base().atk + "의 공격력으로 통상공격합니다.");
			}
			else if(e.getSource() == ba.getMove())
			{
				ba.getDes().append("이동할 수 있는 범위 내의 칸으로 이동합니다.");
			}
			else if(e.getSource() == ba.getPasSkill())
			{
				ba.getDes().append(game.player[num].get_Base().PassiveSkillDescription);
			}
			else if(e.getSource() == ba.getActSkill())
			{
				ba.getDes().append(game.player[num].get_Base().ActiveSkillDescription);
			}	
			else if(e.getSource() == ba.getCancel())
			{
				ba.getDes().append("현재 하려던 행동을 취소합니다.");
			}
		}
	}
	
	public void ready_for_input()
	{
		ba.set_bPane(this.game);
		ba.dislight();
		this.state = 0;
		ba.set_turn(this.game);
		ba.reset_sPane();
		if(game.end == -1)
		{
			ost.stopMusic();
			this.eg = new Endgame(game.player[1].name);
			ba.setVisible(false);
		}
		else if(game.end == 1)
		{
			ost.stopMusic();
			this.eg = new Endgame(game.player[1].name);
			ba.setVisible(false);
		}
	}
	
	public void shift_state(int num)
	{
		if(num == 0)
		{
			ba.dislight();
			ba.getAttack().setEnabled(true);
			ba.getMove().setEnabled(true);
			ba.getPasSkill().setEnabled(true);
			ba.getActSkill().setEnabled(true);
			ba.getCancel().setEnabled(false);	
		}
		else
		{
			ba.getAttack().setEnabled(false);
			ba.getMove().setEnabled(false);
			ba.getPasSkill().setEnabled(false);
			ba.getActSkill().setEnabled(false);
			ba.getCancel().setEnabled(true);
		}
		state = num;
	}
	
	public void check_state(Base b)
	{
		if(b.activeSkill == -1)
		{
			ba.getActSkill().setEnabled(false);
		}
		if(b instanceof Rook)
		{
			if(((Rook)b).mv == null) {ba.getMove().setEnabled(false);}
			else if(((Rook)b).mv != null)
			{
				ba.getAttack().setEnabled(false);
				ba.getActSkill().setEnabled(false);
			}
		}
		else if(b instanceof King)
		{
			ba.getPasSkill().setEnabled(false);
			ba.getActSkill().setEnabled(false);
		}
	}
	
	public void play_sfx(Base b, int ori, int now, int state)
	{
		if(ori == now) { return;}

		if(state == 1)
		{
			if(b instanceof Pawn)
			{
				ost.playSfx(ost.pawnhit);
			}
			else if(b instanceof Rook)
			{
				ost.playSfx(ost.rookhit);
			}
			else if(b instanceof King)
			{
				ost.playSfx(ost.kinghit);
			}
		}
		if(state == 2)
		{
			ost.playSfx(ost.move);
		}
		if(state == 3)
		{
			if(b instanceof Pawn)
			{
				ost.playSfx(ost.pawnpas);
			}
			else if(b instanceof Rook)
			{
				ost.playSfx(ost.rookpas);
			}
		}
		if(state == 4)
		{
			if(b instanceof Pawn)
			{
				ost.playSfx(ost.pawnact);
			}
			else if(b instanceof Rook)
			{
				ost.playSfx(ost.rookact);
			}
		}
	}


}
