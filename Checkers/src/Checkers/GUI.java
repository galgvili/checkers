package Checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;



public class GUI   {

	GameManager New_Game =new GameManager();
	GUI_Square JSquares[][]=new GUI_Square[8][8];
	Container Center=new Container();
	Container West=new Container();
	JFrame board=new JFrame();	
	 String BlackTurn="                  Black Turn",WhiteTurn="                  White Turn";
	JLabel Turn_Status=new JLabel(WhiteTurn);
	JLabel Stam=new JLabel("Stam");
int Eat_More=0;

	int Black_Pawns_Num=12;
	int White_Pawns_Num=12;





void GameStart ()
{
	 int rows=8, columns=8;

	New_Game.Game_Run();
	board.setLayout(new BorderLayout());
	board.setSize(700, 600);
	board.setBackground(Color.LIGHT_GRAY);
	board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	board.setVisible(true);
	Center.setLayout(new GridLayout(rows,columns));
	West.setBackground(Color.BLACK);
	board.add(Center,BorderLayout.CENTER);
	Center.setBackground(Color.GRAY);
	BoardBuilder(Center,New_Game.Game);
	GUI_Button Restart_Button=new GUI_Button(1);
	board.add(Restart_Button.Button,BorderLayout.SOUTH);
	board.add(Turn_Status,BorderLayout.NORTH);
	GUI_Button GiveUp_Button=new GUI_Button(2);
	board.add(GiveUp_Button.Button,BorderLayout.SOUTH);
	board.setExtendedState(JFrame.MAXIMIZED_BOTH);//JFrame Bug Fix 
	board.setExtendedState(JFrame.NORMAL); //JFrame Bug Fix
			
		
		
			}
	


	




void ResetBoard(Container pane,Board board) 
{
	int x=0,y=0;
	Color temp;
	for(y=0;y<8;y++) {
		for(x=0;x<8;x++) {
			if(board.Squares[y][x]!=null) {

				JSquares[y][x].JSquare.removeAll();
				JSquares[y][x].JSquare.revalidate();
				JSquares[y][x].JSquare.repaint();
				}

		}}
	board.InitiateBoard(board);

	for(y=0;y<8;y++) {
		if(y%2==0) 
			temp=Color.WHITE;
		else
			temp=Color.BLACK;
		for(x=0;x<8;x++) {
			JSquares[y][x].JSquare.setBackground(temp);
			if(temp==Color.BLACK) 
			{
				if(board.Squares[y][x]!=null) {
					if(board.Squares[y][x].Color==1) 
					{
						JSquares[y][x].Set_Pawn(Color.WHITE);
					}
					if(board.Squares[y][x].Color==2) 

						{
						JSquares[y][x].Set_Pawn(Color.BLACK);
						JSquares[y][x].JPawn.Pawn.setEnabled(false);
						}
				}
					}
			if(temp.equals(Color.BLACK))
				temp=Color.WHITE;
				else
					temp=Color.BLACK;

			}}
	

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
				JSquares[y][x].Set_Pawn(Color.WHITE);

			}
			else 
				{
				JSquares[y][x].Set_Pawn(Color.BLACK);
				JSquares[y][x].JPawn.Pawn.setEnabled(false);
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

void ReplacePawn(int LocationX,int LocationY,int PrevX,int PrevY,Color PrevColor,boolean Eat,boolean IsKing) 
{
	int y,x;
	int NextColor=1;


	JSquares[LocationY][LocationX].Set_Pawn(PrevColor);
	ClearMarks(New_Game.Game);
	JSquares[PrevY][PrevX].JSquare.removeAll();
	if(Eat==true)
	{
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.removeAll();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.revalidate();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.repaint();
		if(PrevColor==Color.black)
			White_Pawns_Num--;
		else
			Black_Pawns_Num--;

	}
	JSquares[PrevY][PrevX].JSquare.repaint();
	JSquares[LocationY][LocationX].JSquare.revalidate();
	JSquares[LocationY][LocationX].JSquare.repaint();
	New_Game.MovePawn(PrevX, PrevY, LocationX, LocationY,Eat,IsKing);

	if(IsKing==true)
			JSquares[LocationY][LocationX].Is_King=true;

	else if(New_Game.KingCheck(LocationY,LocationX)==true) 
		JSquares[LocationY][LocationX].Is_King=true;
	

	
	if(PrevColor==Color.black||IsKing==true) 
	{
		if((Eat==true) &&(New_Game.BlackRightEatChecker(LocationY, LocationX)==1||New_Game.BlackLeftEatChecker(LocationY, LocationX)==1)) {
			{
				Turn_Status.setText(BlackTurn);
				NextColor=2;
				Eat_More=1;
			}
			if(IsKing==true) {
				if((Eat==true) &&(New_Game.WhiteRightEatChecker(LocationY, LocationX)==1||New_Game.WhiteLeftEatChecker(LocationY, LocationX)==1)) {
					{
						Turn_Status.setText(WhiteTurn);	
						NextColor=1;
						Eat_More=1;
					}

			}}
		}
		else 
		{
			NextColor=1;
			Turn_Status.setText(WhiteTurn);	
		}

	}
	if(PrevColor==Color.white||IsKing==true)
	{
		if((Eat==true) &&(New_Game.WhiteRightEatChecker(LocationY, LocationX)==1||New_Game.WhiteLeftEatChecker(LocationY, LocationX)==1)) {
			{
				Turn_Status.setText(WhiteTurn);	
				NextColor=1;
				Eat_More=1;
			}
			if(IsKing==true) {
				if((Eat==true) &&(New_Game.BlackRightEatChecker(LocationY, LocationX)==1||New_Game.BlackLeftEatChecker(LocationY, LocationX)==1)) {
					{
						Turn_Status.setText(WhiteTurn);	
						NextColor=1;
						Eat_More=1;
					}

			}}

		}
		else 
		{
			NextColor=2;
			Turn_Status.setText(BlackTurn);
		}
	}
		for( y=0;y<8;y++) {
			for( x=0;x<8;x++) {
				if(New_Game.Game.Squares[y][x]!=null) {
					if(New_Game.Game.Squares[y][x].Color==NextColor&&Eat_More==0) 
						{
						JSquares[y][x].JPawn.Pawn.setEnabled(true);
						JSquares[y][x].JPawn.Pawn.revalidate();
						JSquares[y][x].JPawn.Pawn.repaint();
						}

					else
						{
						JSquares[y][x].JPawn.Pawn.setEnabled(false);
						JSquares[y][x].JPawn.Pawn.revalidate();
						JSquares[y][x].JPawn.Pawn.repaint();
						}

				}
			}
			}
			if(Eat_More==1) 
			{
				JSquares[LocationY][LocationX].JPawn.Pawn.setEnabled(true);
				JSquares[LocationY][LocationX].JPawn.Pawn.revalidate();
				JSquares[LocationY][LocationX].JPawn.Pawn.repaint();
			}
			
			

		}

void FinishGame() {
	int x,y;
	for( y=0;y<8;y++) {
		for( x=0;x<8;x++) {
			if(New_Game.Game.Squares[y][x]!=null) {
					JSquares[y][x].JPawn.Pawn.setEnabled(false);
					JSquares[y][x].JPawn.Pawn.revalidate();
					JSquares[y][x].JPawn.Pawn.repaint();}

			}}

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
		JSquares[y][x].Eat=false;
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
	GUI_Pawn JPawn;


	GUI_Square(int x,int y,Color COLOR)
	{
		LocationX=x;
		LocationY=y;
		Color=COLOR;
		JSquare.setBackground(COLOR);
		JSquare.setEnabled(false);
		JSquare.addActionListener(new Actions());

	}
	void Set_Pawn(Color COLOR) 
	{		
		JPawn=new GUI_Pawn(LocationX,LocationY,COLOR);
		JSquare.add(JPawn.Pawn);
	}
	
	private class Actions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent E) {
		boolean king;
		if(JSquare.getBackground()==Color.LIGHT_GRAY) {
			king=New_Game.Game.Squares[PrevY][PrevX].Is_King;
			Eat_More=0;
			ReplacePawn(LocationX,LocationY,PrevX,PrevY,PrevColor,Eat,king);
		}
		if(Black_Pawns_Num==0)		{	
			Turn_Status.setText("White Wins");
			FinishGame();}
		if(White_Pawns_Num==0) {
			Turn_Status.setText("Black Wins");
			FinishGame();	
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
		MoveOptionsMark(LocationY,LocationX,Color);	
	}
	
	

	
	}}



private class GUI_Button {
	int type=0;
	JButton Button=new JButton();

	GUI_Button(int TYPE)
	{
		type=TYPE;
		Button.addActionListener(new Actions());
		if(type==1) 
		{
			Button.setText("Restart");
			
		}
		if(type==2) 
		{
		Button.setText("Give Up");
			
		}
		
		
		
	}
	private class Actions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent E) {
		if(type==1) 
		{
			ResetBoard(Center,New_Game.Game);
		}
		if(type==2)
		{
			ResetBoard(Center,New_Game.Game);
			
		}
	}	}

			
			
		}
			

void MoveOptionsMark(int LocationY,int LocationX,Color Color) 
{
	if(Color==Color.WHITE||New_Game.KingCheck(LocationY, LocationX)==true) {

	if((New_Game.WhiteRightMoveChecker(LocationY,LocationX)==1)&&Eat_More==0) {
		JSquares[LocationY-1][LocationX+1].JSquare.setBackground(Color.LIGHT_GRAY);
		JSquares[LocationY-1][LocationX+1].JSquare.setEnabled(true);
		JSquares[LocationY-1][LocationX+1].PrevX=LocationX;
		JSquares[LocationY-1][LocationX+1].PrevY=LocationY;
		JSquares[LocationY-1][LocationX+1].PrevColor=Color;


}
	if(New_Game.WhiteLeftMoveChecker(LocationY,LocationX)==1&&Eat_More==0) {
		JSquares[LocationY-1][LocationX-1].JSquare.setBackground(Color.LIGHT_GRAY);
		JSquares[LocationY-1][LocationX-1].JSquare.setEnabled(true);
		JSquares[LocationY-1][LocationX-1].PrevX=LocationX;
		JSquares[LocationY-1][LocationX-1].PrevY=LocationY;
		JSquares[LocationY-1][LocationX-1].PrevColor=Color;
		


}
	if(New_Game.WhiteRightEatChecker(LocationY,LocationX)==1) {
		JSquares[LocationY-2][LocationX+2].JSquare.setBackground(Color.LIGHT_GRAY);
		JSquares[LocationY-2][LocationX+2].JSquare.setEnabled(true);
		JSquares[LocationY-2][LocationX+2].PrevX=LocationX;
		JSquares[LocationY-2][LocationX+2].PrevY=LocationY;
		JSquares[LocationY-2][LocationX+2].PrevColor=Color;
		JSquares[LocationY-2][LocationX+2].Eat=true; 

			
		
}

	
	if(New_Game.WhiteLeftEatChecker(LocationY,LocationX)==1){
		JSquares[LocationY-2][LocationX-2].JSquare.setBackground(Color.LIGHT_GRAY);
		JSquares[LocationY-2][LocationX-2].JSquare.setEnabled(true);
		JSquares[LocationY-2][LocationX-2].PrevX=LocationX;
		JSquares[LocationY-2][LocationX-2].PrevY=LocationY;
		JSquares[LocationY-2][LocationX-2].PrevColor=Color;
		JSquares[LocationY-2][LocationX-2].Eat=true; 					

		}

	




}
	
	if(Color==Color.BLACK||New_Game.KingCheck(LocationY, LocationX)==true)
	{
		if(New_Game.BlackRightMoveChecker(LocationY,LocationX)==1&&Eat_More==0) {
			JSquares[LocationY+1][LocationX-1].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY+1][LocationX-1].JSquare.setEnabled(true);
			JSquares[LocationY+1][LocationX-1].PrevX=LocationX;
			JSquares[LocationY+1][LocationX-1].PrevY=LocationY;
			JSquares[LocationY+1][LocationX-1].PrevColor=Color;
			

}
		if(New_Game.BlackLeftMoveChecker(LocationY,LocationX)==1&&Eat_More==0) {
			JSquares[LocationY+1][LocationX+1].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY+1][LocationX+1].JSquare.setEnabled(true);
			JSquares[LocationY+1][LocationX+1].PrevX=LocationX;
			JSquares[LocationY+1][LocationX+1].PrevY=LocationY;
			JSquares[LocationY+1][LocationX+1].PrevColor=Color;



	}
		if(New_Game.BlackRightEatChecker(LocationY,LocationX)==1) { 
			JSquares[LocationY+2][LocationX-2].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY+2][LocationX-2].JSquare.setEnabled(true);
			JSquares[LocationY+2][LocationX-2].PrevX=LocationX;
			JSquares[LocationY+2][LocationX-2].PrevY=LocationY;
			JSquares[LocationY+2][LocationX-2].PrevColor=Color;
			JSquares[LocationY+2][LocationX-2].Eat=true; 



}
		if(New_Game.BlackLeftEatChecker(LocationY,LocationX)==1) {
			JSquares[LocationY+2][LocationX+2].JSquare.setBackground(Color.LIGHT_GRAY);
			JSquares[LocationY+2][LocationX+2].JSquare.setEnabled(true);
			JSquares[LocationY+2][LocationX+2].PrevX=LocationX;
			JSquares[LocationY+2][LocationX+2].PrevY=LocationY;
			JSquares[LocationY+2][LocationX+2].PrevColor=Color;
			JSquares[LocationY+2][LocationX+2].Eat=true; 





		}
		
		
	}
}
void MarkGray(int LocationY,int LocationX,Color Color) 
{
	JSquares[LocationY][LocationX].JSquare.setBackground(Color.LIGHT_GRAY);
	JSquares[LocationY][LocationX].JSquare.setEnabled(true);
	JSquares[LocationY][LocationX].PrevX=LocationX;
	JSquares[LocationY][LocationX].PrevY=LocationY;
	JSquares[LocationY][LocationX].PrevColor=Color;
	JSquares[LocationY][LocationX].Eat=true; 

}

}






