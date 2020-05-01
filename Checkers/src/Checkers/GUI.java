package Checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;



public class GUI   {

	GameManager New_Game =new GameManager();
	JButton JSquares[][]=new JButton[8][8];


void GameStart ()
{
	 int rows=8, columns=8;

	New_Game.Game_Run();


	JFrame board=new JFrame();	
	board.setLayout(new BorderLayout());
	board.setSize(600, 600);
	board.setBackground(Color.LIGHT_GRAY);
	board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	board.setVisible(true);
	Container Center=new Container();
	Center.setLayout(new GridLayout(rows,columns));
	board.add(Center,BorderLayout.CENTER);
	Center.setBackground(Color.GRAY);
	BoardBuilder(Center,New_Game.Game);
	JButton Start_Button=new JButton("Start Game");
	board.add(Start_Button,BorderLayout.SOUTH);
	board.setExtendedState(JFrame.MAXIMIZED_BOTH);//JFrame Bug Fix 
	board.setExtendedState(JFrame.NORMAL); //JFrame Bug Fix
	


	


}


	
void BoardBuilder(Container pane, Board board) 
{
	int x=0,y=0;
Color temp;
for(y=0;y<8;y++) {
	if(y%2==0) 
		temp=Color.WHITE;
	else
		temp=Color.BLACK;

	for(x=0;x<8;x++) {
	JSquares[y][x]=new JButton();
	JSquares[y][x].setBackground(temp);
	JSquares[y][x].setEnabled(false);
	
	
	if(temp==Color.BLACK) 
	{
		if(board.Squares[y][x]!=null) {
			if(board.Squares[y][x].Color==1) 
			{
				GUI_Pawn Soldier=new GUI_Pawn(x,y,Color.WHITE);
				JSquares[y][x].add(Soldier.Pawn);

			}
			else 
				{
					GUI_Pawn Soldier=new GUI_Pawn(x,y,Color.BLACK);
					JSquares[y][x].add(Soldier.Pawn);
				}

			}
	}
		pane.add(JSquares[y][x]);

		
		if(temp.equals(Color.BLACK))
			temp=Color.WHITE;
			else
				temp=Color.BLACK;

		}
					
		}
	}




private class GUI_Pawn  {

	JButton Pawn=new JButton();
	int LocationX;
	int LocationY;
	Color Color;
	GUI_Pawn(int x,int y,Color COLOR)
	{
		LocationX=x;
		LocationY=y;
		Color=COLOR;
		Pawn.setBackground(COLOR);
		Pawn.setBorder(new PawnShaping(10));
		Pawn.addActionListener(new Actions());

	}
	private class Actions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent E) {
		
		ClearMarks(New_Game.Game);
		if(Color==Color.WHITE) {
		if(New_Game.Game.WhiteRightMoveChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-1][LocationX+1].setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-1][LocationX+1].setEnabled(true);
}
		if(New_Game.Game.WhiteLeftMoveChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-1][LocationX-1].setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-1][LocationX-1].setEnabled(true);
}
		if(New_Game.Game.WhiteRightEatChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-2][LocationX+2].setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-2][LocationX+2].setEnabled(true);
}
		if(New_Game.Game.WhiteLeftEatChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-2][LocationX-2].setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-2][LocationX-2].setEnabled(true);
}
		}
		else
		{
			if(New_Game.Game.BlackRightMoveChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+1][LocationX-1].setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+1][LocationX-1].setEnabled(true);
}
			if(New_Game.Game.BlackLeftMoveChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+1][LocationX+1].setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+1][LocationX+1].setEnabled(true);
		}
			if(New_Game.Game.BlackRightEatChecker(LocationY,LocationX)==1) { 
				JSquares[LocationY+2][LocationX-2].setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+2][LocationX-2].setEnabled(true);
}
			if(New_Game.Game.BlackLeftEatChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+2][LocationX+2].setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+2][LocationX+2].setEnabled(true);

			}

			
			
		}
			
	}
	
	void ClearMarks(Board BOARD) 
	{
		int x=0,y=0;
		for(y=0;y<8;y++) {
			if(y%2==0) 
				x=1;
			else
				x=0;
			for(;x<8;x++) 
			{
			JSquares[y][x].setEnabled(false);
			JSquares[y][x].setBackground(Color.BLACK);
			x++;
			}
			}
			
		
	}
	

	
	}}

private static class PawnShaping implements Border {

    private int radius;


    PawnShaping(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+20, this.radius+20, this.radius, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius+20, radius+20);
    }
}

}








