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
	//if(Game.Squares[OldX][OldY]==null)
		//return;
	Game.Squares[NewY][NewX]=new Soldier();
	Game.Squares[OldY][OldX].Copy(Game.Squares[NewY][NewX]);
	Game.Squares[OldY][OldX]=null;
	if(Eat==true)
		Game.Squares[(NewY+OldY)/2][(NewX+OldX)/2]=null;

	KingCheck(NewY,NewX);//King Check

		
	}//

    int WhiteRightMoveChecker(int y, int x)//Check if white can move right, 0=can't move, 1=can move 
    {
    	if(Game.Squares[y][x]==null)
    		return 0;
    	if(x==7||y==0)//Check if on board boundaries 
    		return 0;
    	if(Game.Squares[y-1][x+1]==null)
    		return 1;
    	return 0;	
    	
    }
    
    int BlackRightMoveChecker(int y, int x)//Check if black can move right, 0=can't move, 1=can move 
	{
    	if(Game.Squares[y][x]==null)
    		return 0;
    	if(x==0||y==7)//Check if on board boundaries 
    		return 0;
    	if(Game.Squares[y+1][x-1]==null)
    		return 1;
    	return 0;	

	
	}

    int WhiteLeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move
    {
    	if(Game.Squares[y][x]==null)
    		return 0;
    	if(x==0||y==0)//Check if on board boundaries 
    		return 0;
    	if(Game.Squares[y-1][x-1]==null)
    		return 1;
    	return 0;	
    }

    int BlackLeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move
{
	if(Game.Squares[y][x]==null)
		return 0;
	if(x==7||y==7)//Check if on board boundaries 
		return 0;
	if(Game.Squares[y+1][x+1]==null)
		return 1;
	return 0;
	
}

    int WhiteRightEatChecker(int y, int x)//Check if white can eat right, 0=can't eat, 1=can eat;
    {
    	if(x>5||y<2)//Check if on board boundaries 
    		return 0;
    	if(Game.Squares[y-1][x+1]==null)//Check if right square is empty
    		return 0;
    	if(Game.Squares[y-1][x+1].Color==1)//Check if right square is the same color
    		return 0;
    	if(Game.Squares[y-2][x+2]!=null)//Check if the right of the right square is not empty
    		return 0;
    	return 1;  	
    }
    
    int BlackRightEatChecker(int y, int x)//Check if black can eat right, 0=can't eat, 1=can eat;
    {
    	if(x<2||y>5)//Check if on board boundaries 
    		return 0;
    	if(Game.Squares[y+1][x-1]==null)//Check if right square is empty
    		return 0;
    	if(Game.Squares[y+1][x-1].Color==2)//Check if right square is the same color
    		return 0;
    	if(Game.Squares[y+2][x-2]!=null)//Check if the right of the right square is not empty
    		return 0; 		
	return 1;  	
    }
    int WhiteLeftEatChecker(int y, int x)//Check if white can eat right, 0=can't eat, 1=can eat;
    {
    	if(x<2||y<2)
    		return 0;
    	if(Game.Squares[y-1][x-1]==null)
    		return 0;
    	if(Game.Squares[y-1][x-1].Color==1)//Check if left square is the same color
    		return 0;
    	if(Game.Squares[y-2][x-2]!=null)//Check if the left of the left square is not empty
    		return 0;
    	return 1;  	
    }
    
    int BlackLeftEatChecker(int y, int x)//Check if white can eat left, 0=can't eat, 1=can eat;
    
    {
    	if(x>5||y>5)
    		return 0;
    	if(Game.Squares[y+1][x+1]==null)
    		return 0;
    	if(Game.Squares[y+1][x+1].Color==2)//Check if left square is the same color
    		return 0;
    	if(Game.Squares[y+2][x+2]!=null)//Check if the left of the left square is not empty
    		return 0;		
	return 1;  	

    	
    }
    boolean KingCheck(int y, int x) {
    	if(Game.Squares[y][x].Is_King==true)
    		return true;
    	if(y==0&&Game.Squares[y][x].Color==1) {
    		Game.Squares[y][x]=new King();
    		Game.Squares[y][x].Color=1;
    		Game.Squares[y][x].LocationX=x;
    		Game.Squares[y][x].LocationY=y;
    		Game.Squares[y][x].Is_King=true;
    		return true;
    		
    	}
    	
    	if(Game.Squares[y][x].Is_King==false&&y==7&&Game.Squares[y][x].Color==2) 
    	{
    		Game.Squares[y][x]=new King();
    		Game.Squares[y][x].Color=2;
    		Game.Squares[y][x].LocationX=x;
    		Game.Squares[y][x].LocationY=y;
    		Game.Squares[y][x].Is_King=true;
    		return true;
    	}
    	return false;

    	
    	
    	}

	
	
}


