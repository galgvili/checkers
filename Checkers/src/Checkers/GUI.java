package Checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.*;



public class GUI   {

	GameManager New_Game =new GameManager();
	GUI_Square JSquares[][]=new GUI_Square[8][8];
	Container GUI_BOARD_PANEL=new Container();
	Container West=new Container();
	JFrame board=new JFrame();	
	JPanel GUI_SOUTH_PANEL=new JPanel();
	JPanel GUI_NORTH_PANEL=new JPanel();
	GUI_Button Restart_Button=new GUI_Button(1);
	GUI_Button GiveUp_Button=new GUI_Button(2);

	String BlackTurn ="   Black Turn   ",WhiteTurn ="   White Turn   ";
	JLabel Turn_Status=new JLabel(WhiteTurn);
	JLabel Score_Status=new JLabel();
	int Eat_More=0, Black_Pawns_Num=12, White_Pawns_Num=12,Black_Score=0,White_Score=0,NextColor=1;




	  public static final Color DARK_SQUERES = new Color(169,120,55);
	  public static final Color LIGHT_SQUERE = new Color(250,246,189);
	  public static final Color BLACK_PAWN_COLOR = new Color(80,59,45);
	  public static final Color GAME_BG = new Color(166,111,75);
	  public static final Color GUI_BUTTON_BG = new Color(168,120,88);





void GameStart ()
{
	Turn_Status.setForeground(Color.white);
	New_Game.Game_Run();
	GUI_Builder();
		
		
			}
	


	
void GUI_Builder() 
{
	 int rows=8, columns=8;
	board.setLayout(new BorderLayout());
	board.setSize(600, 650);
	board.setResizable(false);
	board.setBackground(Color.LIGHT_GRAY);
	board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	board.setVisible(true);
	GUI_NORTH_PANEL.setLayout(new FlowLayout(3));
	GUI_NORTH_PANEL.setBackground(GAME_BG);
	GUI_SOUTH_PANEL.setLayout(new FlowLayout(3));
	GUI_SOUTH_PANEL.setBackground(GAME_BG);

	GUI_BOARD_PANEL.setLayout(new GridLayout(rows,columns));
	West.setBackground(Color.BLACK);
	board.add(GUI_BOARD_PANEL,BorderLayout.CENTER);
	GUI_BOARD_PANEL.setBackground(Color.GRAY);
	BoardBuilder(GUI_BOARD_PANEL,New_Game.Game);
	Score_Status.setText(	"                             Blacks: "+Black_Score+"   Whites: "+White_Score);
Turn_Status.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1) );
Turn_Status.setFont(new Font("Courier", Font.BOLD,22));
Score_Status.setFont(new Font("Courier", Font.BOLD,22));

GUI_NORTH_PANEL.add(Turn_Status);
	GUI_NORTH_PANEL.add(Score_Status);

	board.add(GUI_NORTH_PANEL,BorderLayout.NORTH);
	
	GUI_SOUTH_PANEL.add(GiveUp_Button.Button);
	GUI_SOUTH_PANEL.add(Restart_Button.Button);
	board.add(GUI_SOUTH_PANEL,BorderLayout.SOUTH);
	board.setExtendedState(JFrame.MAXIMIZED_BOTH);//JFrame Bug Fix 
	board.setExtendedState(JFrame.NORMAL); //JFrame Bug Fix



}



void ResetBoard(Container pane,Board board) 
{
	Turn_Status.setText(WhiteTurn);
	Turn_Status.setForeground(Color.white);
	
	int x=0,y=0;
	Color temp;
	for(y=0;y<8;y++) //Remove all pawns from the board 
	{ 
		for(x=0;x<8;x++) {
			if(board.Squares[y][x]!=null) {

				JSquares[y][x].JSquare.removeAll();
				JSquares[y][x].JSquare.revalidate();
				JSquares[y][x].JSquare.repaint();
				}

		}}
	board.InitiateBoard(board); // Build the backend board

	for(y=0;y<8;y++) //Build the GUI board
	{
		if(y%2==0) 
			temp=LIGHT_SQUERE;
		else
			temp=DARK_SQUERES;
		for(x=0;x<8;x++) {
			JSquares[y][x].JSquare.setBackground(temp);//Set the Square color
			if(temp==DARK_SQUERES) 
			{
				if(board.Squares[y][x]!=null) {
					if(board.Squares[y][x].Color==1) 
					{
						JSquares[y][x].Set_Pawn(Color.WHITE);//Set White Pawn
					}
					if(board.Squares[y][x].Color==2) 

						{
						JSquares[y][x].Set_Pawn(BLACK_PAWN_COLOR); //Set black pawn
						JSquares[y][x].JPawn.Pawn.setEnabled(false);
						}
				}
					}
			if(temp.equals(DARK_SQUERES))
				temp=LIGHT_SQUERE;
				else
					temp=DARK_SQUERES;

			}}
	

	}

	
void BoardBuilder(Container pane, Board board) 
{
int x=0,y=0;
Color temp;
for(y=0;y<8;y++) {
	if(y%2==0) 
		temp=LIGHT_SQUERE;
	else
		temp=DARK_SQUERES;

	for(x=0;x<8;x++) {
	JSquares[y][x]=new GUI_Square(x,y,temp);
	
	
	if(temp==DARK_SQUERES)
	{
		if(board.Squares[y][x]!=null) {
			if(board.Squares[y][x].Color==1) 
			{
				JSquares[y][x].Set_Pawn(Color.WHITE);

			}
			else 
				{
				JSquares[y][x].Set_Pawn(BLACK_PAWN_COLOR);
				JSquares[y][x].JPawn.Pawn.setEnabled(false);
				}

			}
	}
		pane.add(JSquares[y][x].JSquare);

		
		if(temp.equals(DARK_SQUERES))
			temp=LIGHT_SQUERE;
			else
				temp=DARK_SQUERES;

		}
					
		}
	}

void ReplacePawn(int LocationX,int LocationY,int PrevX,int PrevY,Color PrevColor,boolean Eat,boolean IsKing) 
{
	int y,x;


	JSquares[LocationY][LocationX].Set_Pawn(PrevColor);
	ClearMarks(New_Game.Game);
	JSquares[PrevY][PrevX].JSquare.removeAll();
	if(Eat==true)
	{
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.removeAll();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.revalidate();
		JSquares[((PrevY+LocationY)/2)][((PrevX+LocationX)/2)].JSquare.repaint();
		if(PrevColor==BLACK_PAWN_COLOR)
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
	

	
	if(PrevColor==BLACK_PAWN_COLOR) 
	{
		if((Eat==true) &&(New_Game.BlackRightEatChecker(LocationY, LocationX)==1||New_Game.BlackLeftEatChecker(LocationY, LocationX)==1)) 
		{
			   Turn_Status.setText(BlackTurn);
				Turn_Status.setForeground(Color.black);

				NextColor=2;
				Eat_More=1;
		}
		else 
				{
					NextColor=1;
					Turn_Status.setText(WhiteTurn);
					Turn_Status.setForeground(Color.white);
				}
		
	   if(IsKing==true) 
			{
				if((Eat==true) &&(New_Game.WhiteRightEatChecker(LocationY, LocationX)==1||New_Game.WhiteLeftEatChecker(LocationY, LocationX)==1))
					{
						Turn_Status.setText(BlackTurn);	
						Turn_Status.setForeground(Color.black);

						NextColor=2;
						Eat_More=1;
					}

			}
			}
		 


	
			
	else	
	{
		if((Eat==true) &&(New_Game.WhiteRightEatChecker(LocationY, LocationX)==1||New_Game.WhiteLeftEatChecker(LocationY, LocationX)==1)) 
			{
				Turn_Status.setText(WhiteTurn);	
				Turn_Status.setForeground(Color.white);

				NextColor=1;
				Eat_More=1;
			}
			
			else
			{
			NextColor=2;
			Turn_Status.setText(BlackTurn);
			Turn_Status.setForeground(Color.black);

			}
		    if(IsKing==true) 
			{
				if((Eat==true) &&(New_Game.BlackRightEatChecker(LocationY, LocationX)==1||New_Game.BlackLeftEatChecker(LocationY, LocationX)==1))
				    {
						Turn_Status.setText(WhiteTurn);	
						Turn_Status.setForeground(Color.white);
						NextColor=1;
						Eat_More=1;
					}

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
		JSquares[y][x].JSquare.setBackground(DARK_SQUERES);
		JSquares[y][x].Eat=false;
		x++;
		}
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
			JOptionPane.showMessageDialog(board,
				    "  White Wins!!!",
				    "A plain message",
				    JOptionPane.PLAIN_MESSAGE);

			White_Score++;
			ResetBoard(GUI_BOARD_PANEL,New_Game.Game);
}
		
		if(White_Pawns_Num==0) {
			JOptionPane.showMessageDialog(board,
				    "  Black Wins!!!",
				    "A plain message",
				    JOptionPane.PLAIN_MESSAGE);
			Black_Score++;
			ResetBoard(GUI_BOARD_PANEL,New_Game.Game);


		}
		Score_Status.setText(	"                             Blacks: "+Black_Score+"   Whites: "+White_Score);

		



			

	}
	
	}
		



}

private class GUI_Pawn  {

	JButton Pawn=new RoundButton("");
	int LocationX;
	int LocationY;
	Color Color;
	
	GUI_Pawn(int x,int y,Color COLOR)
	{
		LocationX=x;
		LocationY=y;
		Color=COLOR;
		Pawn.setBackground(COLOR);
		Pawn.setBorder(new RoundButton.PawnShaping(10));

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
		Button.setFocusPainted(false);

		if(type==1) 
		{
			Button.setText("Restart");
			Button.setFont(new Font("Courier", Font.CENTER_BASELINE,16));
			Button.setBackground(GUI_BUTTON_BG);



			
		}
		if(type==2) 
		{
		Button.setText("Give Up");
		Button.setFont(new Font("Courier", Font.CENTER_BASELINE,16));
		Button.setBackground(GUI_BUTTON_BG);



			
		}
		
		
		
		
	}
	private class Actions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent E) {
		if(type==1) 
		{
			ResetBoard(GUI_BOARD_PANEL,New_Game.Game);
		}
		
		if(type==2)
		{
			if(NextColor==1) {
				JOptionPane.showMessageDialog(board,
					    "  Black Wins!!!",
					    "A plain message",
					    JOptionPane.PLAIN_MESSAGE);
				Black_Score++;
				
				}
		else {
			JOptionPane.showMessageDialog(board,
				    "  White Wins!!!",
				    "A plain message",
				    JOptionPane.PLAIN_MESSAGE);

			White_Score++;
			}
			Score_Status.setText(	"                             Blacks: "+Black_Score+"   Whites: "+White_Score);
			ResetBoard(GUI_BOARD_PANEL,New_Game.Game);

			
		
	}	
	
	}


			
		}}
			

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
	
	if(Color==BLACK_PAWN_COLOR||New_Game.KingCheck(LocationY, LocationX)==true)
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






