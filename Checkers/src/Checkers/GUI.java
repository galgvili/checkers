package Checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;



public class GUI   {

	GameManager New_Game =new GameManager();
	GUI_Square JSquares[][]=new GUI_Square[8][8];


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
	JSquares[y][x]=new GUI_Square(x,y,temp);
	
	
	if(temp==Color.BLACK) 
	{
		if(board.Squares[y][x]!=null) {
			if(board.Squares[y][x].Color==1) 
			{
				GUI_Pawn Soldier=new GUI_Pawn(x,y,Color.WHITE);
				JSquares[y][x].JSquare.add(Soldier.Pawn);

			}
			else 
				{
					GUI_Pawn Soldier=new GUI_Pawn(x,y,Color.BLACK);
					JSquares[y][x].JSquare.add(Soldier.Pawn);
				}

			}
	}
		pane.add(JSquares[y][x].JSquare);

		
		if(temp.equals(Color.BLACK))
			temp=Color.WHITE;
			else
				temp=Color.BLACK;

		}
					
		}
	}
void ReplacePawn(int LocationX,int LocationY,int PrevX,int PrevY,Color PrevColor,boolean Eat) 
{
	GUI_Pawn Soldier=new GUI_Pawn(LocationX,LocationY,PrevColor);
	JSquares[LocationY][LocationX].JSquare.add(Soldier.Pawn);
	ClearMarks(New_Game.Game);
	JSquares[PrevY][PrevX].JSquare.removeAll();
	if(Eat==true)
	{
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.removeAll();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.revalidate();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.repaint();
	}
	JSquares[PrevY][PrevX].JSquare.repaint();
	JSquares[LocationY][LocationX].JSquare.revalidate();
	JSquares[PrevY][PrevX].JSquare.repaint();
	JSquares[LocationY][LocationX].JSquare.repaint();
	New_Game.Game.MovePawn(PrevX, PrevY, LocationX, LocationY,Eat);
	New_Game.Game.PrintBoard();
	if(New_Game.Game.KingCheck(LocationY,LocationX)==true) 
	{
		JSquares[LocationY][LocationX].Is_King=true;
	}
	if(JSquares[PrevY][PrevX].Is_King==true)
		JSquares[LocationY][LocationX].Is_King=true;

			
	
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
		JSquares[y][x].JSquare.setEnabled(false);
		JSquares[y][x].JSquare.setBackground(Color.BLACK);
		x++;
		}
		}
		
	
}
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


private class GUI_Square  {
	JButton JSquare=new JButton();
	int PrevX;
	int PrevY;
	Color PrevColor;
	boolean Eat=false;
	boolean Is_King=false;
	int LocationX;
	int LocationY;
	Color Color;

	GUI_Square(int x,int y,Color COLOR)
	{
		LocationX=x;
		LocationY=y;
		Color=COLOR;
		JSquare.setBackground(COLOR);
		JSquare.setEnabled(false);
		JSquare.addActionListener(new Actions());

	}
	private class Actions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent E) {
		if(JSquare.getBackground()==Color.LIGHT_GRAY) 
		{
			
			ReplacePawn(LocationX,LocationY,PrevX,PrevY,PrevColor,Eat);
			



		}
		
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
		if(Color==Color.WHITE||JSquares[LocationY][LocationX].Is_King==true) {

		if(New_Game.Game.WhiteRightMoveChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-1][LocationX+1].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-1][LocationX+1].JSquare.setEnabled(true);
			JSquares[LocationY-1][LocationX+1].PrevX=LocationX;
			JSquares[LocationY-1][LocationX+1].PrevY=LocationY;
			JSquares[LocationY-1][LocationX+1].PrevColor=Color;


}
		if(New_Game.Game.WhiteLeftMoveChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-1][LocationX-1].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-1][LocationX-1].JSquare.setEnabled(true);
			JSquares[LocationY-1][LocationX-1].PrevX=LocationX;
			JSquares[LocationY-1][LocationX-1].PrevY=LocationY;
			JSquares[LocationY-1][LocationX-1].PrevColor=Color;
			


}
		if(New_Game.Game.WhiteRightEatChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-2][LocationX+2].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-2][LocationX+2].JSquare.setEnabled(true);
			JSquares[LocationY-2][LocationX+2].PrevX=LocationX;
			JSquares[LocationY-2][LocationX+2].PrevY=LocationY;
			JSquares[LocationY-2][LocationX+2].PrevColor=Color;
			JSquares[LocationY-2][LocationX+2].Eat=true; 




}
		if(New_Game.Game.WhiteLeftEatChecker(LocationY,LocationX)==1) {
			JSquares[LocationY-2][LocationX-2].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY-2][LocationX-2].JSquare.setEnabled(true);
			JSquares[LocationY-2][LocationX-2].PrevX=LocationX;
			JSquares[LocationY-2][LocationX-2].PrevY=LocationY;
			JSquares[LocationY-2][LocationX-2].PrevColor=Color;
			JSquares[LocationY-2][LocationX-2].Eat=true; 



}
		}
		if(Color==Color.BLACK||JSquares[LocationY][LocationX].Is_King==true)
		{
			if(New_Game.Game.BlackRightMoveChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+1][LocationX-1].JSquare.setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+1][LocationX-1].JSquare.setEnabled(true);
				JSquares[LocationY+1][LocationX-1].PrevX=LocationX;
				JSquares[LocationY+1][LocationX-1].PrevY=LocationY;
				JSquares[LocationY+1][LocationX-1].PrevColor=Color;
				

}
			if(New_Game.Game.BlackLeftMoveChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+1][LocationX+1].JSquare.setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+1][LocationX+1].JSquare.setEnabled(true);
				JSquares[LocationY+1][LocationX+1].PrevX=LocationX;
				JSquares[LocationY+1][LocationX+1].PrevY=LocationY;
				JSquares[LocationY+1][LocationX+1].PrevColor=Color;



		}
			if(New_Game.Game.BlackRightEatChecker(LocationY,LocationX)==1) { 
				JSquares[LocationY+2][LocationX-2].JSquare.setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+2][LocationX-2].JSquare.setEnabled(true);
				JSquares[LocationY+2][LocationX-2].PrevX=LocationX;
				JSquares[LocationY+2][LocationX-2].PrevY=LocationY;
				JSquares[LocationY+2][LocationX-2].PrevColor=Color;
				JSquares[LocationY+2][LocationX-2].Eat=true; 




}
			if(New_Game.Game.BlackLeftEatChecker(LocationY,LocationX)==1) {
				JSquares[LocationY+2][LocationX+2].JSquare.setBackground(Color.LIGHT_GRAY);
				JSquares[LocationY+2][LocationX+2].JSquare.setEnabled(true);
				JSquares[LocationY+2][LocationX+2].PrevX=LocationX;
				JSquares[LocationY+2][LocationX+2].PrevY=LocationY;
				JSquares[LocationY+2][LocationX+2].PrevColor=Color;
				JSquares[LocationY+2][LocationX+2].Eat=true; 





			}

			
			
		}
			
	}
	
	

	
	}}




}





