package Checkers;

public class GameManager {
	public Board Game;
	
void Game_Run() 

{
	Game=new Board();
	Game.InitiateBoard(Game);
}

	void MovePawn(int OldX,int OldY,int NewX,int NewY, boolean Eat)
	{
	//if(Squares[OldX][OldY]==null)
		//return;
	Game.Squares[NewY][NewX]=new Soldier();
	Game.Squares[OldY][OldX].Copy(Game.Squares[NewY][NewX]);
	Game.Squares[OldY][OldX]=null;
	if(Eat==true)
		Game.Squares[(NewY+OldY)/2][(NewX+OldX)/2]=null;

	Game.KingCheck(NewY,NewX);//King Check

		
	}//

	
	
}


