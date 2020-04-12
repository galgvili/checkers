package Checkers;

import java.util.Scanner;

public class GameManager {
	
	
void Game_Run() 

{
	Board Game=new Board();
	Game=Game.InitiateBoard();
	int White_Score=12,Black_Score=12,x,y,Next_Move=1,code=0;
	Scanner myInput = new Scanner( System.in );
    while(White_Score!=0&&Black_Score!=0) 
	{
		Game.PrintBoard();
		System.out.print("\nWhites:"+White_Score+"\nBlacks:"+Black_Score+"\n");
    	System.out.print("White turn:\n Choose Pawn:");
    	y = myInput.nextInt();
    	x = myInput.nextInt();
		System.out.print("Choose your move:\n");
		code=Game.MoveOptions(y,x);		
		PrintMoveOptions(code);
		Next_Move=myInput.nextInt();
		switch(Next_Move)
		{
			case 1:
				Game.MoveRightForward(y,x);
				break;
			case 2:
				Game.MoveLeftForward(y,x);
				break;
			case 3:
				if(Game.Squares[y][x].Color==1)
					Black_Score--;
				else
					White_Score--;
				Game.EatRightForward(y,x);
				
				break;
			case 4:
				if(Game.Squares[y][x].Color==1)
					Black_Score--;
				else
					White_Score--;
				Game.EatLeftForward(y,x);
				break;
			case 5:
				break;
		}
		
			
		}
	if(White_Score==0)
		System.out.print("\nBlack Wins!n");
	else
		System.out.print("\nWhite Wins!\n");

}
		


void PrintMoveOptions(int code) {
	if(code==1) 
	{
		System.out.print("(1) Right\n");
		System.out.print("(5) Cancel\n");
		}
else if(code==2) 
{
	System.out.print("(2) Left\n");
	System.out.print("(5) Cancel\n");

}
else if(code==3) 
{
	System.out.print("(1) Right\n");
	System.out.print("(2) Left\n");
	System.out.print("(5) Cancel\n");

}
else if(code==10) 
{
	System.out.print("(3) Jump Right\n");
	System.out.print("(5) Cancel\n");

}
else if(code==20) 
{
	System.out.print("(4) Jump Left\n");
	System.out.print("(5) Cancel\n");

}
else if(code==20) 
{
	System.out.print("(3) Jump Right\n");
	System.out.print("(4) Jump Left\n");
	System.out.print("(5) Cancel\n");

}
else if(code==21) 
{

	System.out.print("(1) Right\n");
	System.out.print("(4) Jump Left\n");
	System.out.print("(5) Cancel\n");

}
else if(code==12) {
	System.out.print("(1) Left\n");
	System.out.print("(3) Jump Right\n");
	System.out.print("(5) Cancel\n");
	}
	else
		System.out.print("(5) Cancel\n");

}
}




