package Checkers;

import java.util.Scanner;

public class GameManager {
	
	
void Game_Run() 

{
	Board Game=new Board();
	Game=Game.InitiateBoard();
	int White_Score=12,Black_Score=12,x,y,Next_Move=1,turn=0;
	Scanner myInput = new Scanner( System.in );
    while(White_Score!=0&&Black_Score!=0) 
	{
		Game.PrintBoard();
		System.out.print("\nWhites:"+White_Score+"\nBlacks:"+Black_Score+"\n");
		if(turn%2==0) 
			System.out.print("White turn, choose pawn(y,x):\n");
		else
			System.out.print("Black turn, choose pawn(y,x):\n");
    	y = myInput.nextInt();
    	x = myInput.nextInt();
		System.out.print("Choose your move:\n");
		Game.MoveOptions(y,x);		
		Next_Move=myInput.nextInt();
		switch(Next_Move)
		{
			case 1:
				if (Game.Squares[y][x].Color==1)
					Game.WhiteMoveRightForward(y,x);
				else if (Game.Squares[y][x].Color==2)
					Game.BlackMoveRightForward(y,x);

				break;
			case 2:
				if (Game.Squares[y][x].Color==1)
					Game.WhiteMoveLeftForward(y,x);
				else if (Game.Squares[y][x].Color==2)
					Game.BlackMoveLeftForward(y,x);
				break;
			case 3:
				if(Game.Squares[y][x].Color==1)	{
					Black_Score--;
				Game.WhiteEatRightForward(y,x);
				}

				else {
					White_Score--;
				Game.BlackEatRightForward(y,x);
				}
				break;
			case 4:
				if(Game.Squares[y][x].Color==1) {
					Black_Score--;
				Game.WhiteEatLeftForward(y,x);}

				else {
					White_Score--;
					Game.BlackEatLeftForward(y,x);

				}
				break;
			case 5:
				if (Game.Squares[y][x].Color==2)
					Game.WhiteMoveRightForward(y,x);
				if (Game.Squares[y][x].Color==1)
					Game.BlackMoveRightForward(y,x);

				break;
			case 6:
				if (Game.Squares[y][x].Color==2)
					Game.WhiteMoveLeftForward(y,x);
				if (Game.Squares[y][x].Color==1)
				Game.BlackMoveLeftForward(y,x);
				break;
			case 7:
				if(Game.Squares[y][x].Color==2)	{
					White_Score--;
				Game.WhiteEatRightForward(y,x);
				}

				else {
					Black_Score--;
				Game.BlackEatRightForward(y,x);
				}
				break;
			case 8:
				if(Game.Squares[y][x].Color==2) {
					White_Score--;
				Game.WhiteEatLeftForward(y,x);}

				else {
					Black_Score--;
					Game.BlackEatLeftForward(y,x);

				}
				break;
		}
		turn++;
			
		}
	if(White_Score==0)
		System.out.print("\nBlack Wins!n");
	else
		System.out.print("\nWhite Wins!\n");

}
		


}



