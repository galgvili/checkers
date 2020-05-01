package Checkers;

public class GameManager {
	public Board Game;
	
void Game_Run() 

{
	Game=new Board();
	Game=Game.InitiateBoard();
}
int MoveOptionsWhite (int y,int x,int Color)
{
	int code=0;
if(Color==1) 
{
if(Game.WhiteRightMoveChecker(y, x)==1) 
	code++;

if(Game.WhiteRightMoveChecker(y, x)==1) 
	code+=2;

if(Game.WhiteRightEatChecker(y, x)==1)
	code+=10;

if(Game.WhiteLeftEatChecker(y, x)==1)
	code+=20;
}
return code;


}
	
	
	
}


