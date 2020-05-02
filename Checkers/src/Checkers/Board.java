package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][8];
   
	Board InitiateBoard() //Reset board pawns positions 
    {
		//Black=2, White=1, Blank=0
        int x;
    	Board BOARD=new Board();
        for(x=0;x<7;x+=2) 
        	{
    		int y=0;
        	BOARD.Squares[y][x+1]=new Soldier();
        	BOARD.Squares[y][x+1].Color=2;
        	BOARD.Squares[y+1][x]=new Soldier();
        	BOARD.Squares[y+1][x].Color=2;
        	BOARD.Squares[y+2][x+1]=new Soldier();
        	BOARD.Squares[y+2][x+1].Color=2;
        	BOARD.Squares[y+5][x]=new Soldier();
        	BOARD.Squares[y+5][x].Color=1;
        	BOARD.Squares[y+6][x+1]=new Soldier();
        	BOARD.Squares[y+6][x+1].Color=1;
        	BOARD.Squares[y+7][x]=new Soldier();
        	BOARD.Squares[y+7][x].Color=1;
        	
        	}
        return BOARD;
        }

	void PrintSquare(int y,int x) //Print specific board square.
    {
    	System.out.print(Squares[y][x].Color);
    }
    void PrintBoard() //Print current board
    {
    	int x,y;
    	for(y=0;y<8;y++) 
    	{
        	for(x=0;x<8;x++) 
        	{
        		if(Squares[y][x]==null)
                    	System.out.print("0 | ");
        		else
        		System.out.print(Squares[y][x].Color+" | ");
        	}
        	System.out.print("\n\n");
        	}

 }
       	
  	void MovePawn(int OldX,int OldY,int NewX,int NewY)
  	{
    	//if(Squares[OldX][OldY]==null)
    		//return;
		Squares[NewY][NewX]=new Soldier();
		Squares[OldY][OldX].Copy(Squares[NewY][NewX]);
		Squares[OldY][OldX]=null;
		KingCheck(NewY,NewX);//King Check

  		
  	}
    int WhiteRightMoveChecker(int y, int x)//Check if white can move right, 0=can't move, 1=can move 
    {
    	if(Squares[y][x]==null)
    		return 0;
    	if(x==7||y==0)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)
    		return 1;
    	return 0;	
    	
    }
    
    int BlackRightMoveChecker(int y, int x)//Check if black can move right, 0=can't move, 1=can move 
	{
    	if(Squares[y][x]==null)
    		return 0;
    	if(x==0||y==7)//Check if on board boundaries 
    		return 0;
    	if(Squares[y+1][x-1]==null)
    		return 1;
    	return 0;	

	
	}

    int WhiteLeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move
    {
    	if(Squares[y][x]==null)
    		return 0;
    	if(x==0||y==0)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 1;
    	return 0;	
    }

    int BlackLeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move
{
	if(Squares[y][x]==null)
		return 0;
	if(x==7||y==7)//Check if on board boundaries 
		return 0;
	if(Squares[y+1][x+1]==null)
		return 1;
	return 0;
	
}

    int WhiteRightEatChecker(int y, int x)//Check if white can eat right, 0=can't eat, 1=can eat;
    {
    	if(Squares[y][x]==null)
    		return 0;
    	if(x>5||y<2)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)//Check if right square is empty
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x+1].Color)//Check if right square is the same color
    		return 0;
    	if(Squares[y-2][x+2]!=null)//Check if the right of the right square is not empty
    		return 0;
    	return 1;  	
    }
    
    int BlackRightEatChecker(int y, int x)//Check if black can eat right, 0=can't eat, 1=can eat;
    {
    	if(Squares[y][x]==null)
    		return 0;
    	if(x<2||y>5)//Check if on board boundaries 
    		return 0;
    	if(Squares[y+1][x-1]==null)//Check if right square is empty
    		return 0;
    	if(Squares[y][x].Color==Squares[y+1][x-1].Color)//Check if right square is the same color
    		return 0;
    	if(Squares[y+2][x-2]!=null)//Check if the right of the right square is not empty
    		return 0; 		
	return 1;  	
    }
    int WhiteLeftEatChecker(int y, int x)//Check if white can eat right, 0=can't eat, 1=can eat;
    {
    	if(Squares[y][x]==null)//if square is empty
    		return 0;
    	if(x<2||y<2)
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x-1].Color)//Check if left square is the same color
    		return 0;
    	if(Squares[y-2][x-2]!=null)//Check if the left of the left square is not empty
    		return 0;
    	return 1;  	
    }
    
    int BlackLeftEatChecker(int y, int x)//Check if white can eat left, 0=can't eat, 1=can eat;
    
    {
    	if(Squares[y][x]==null)//if square is empty
    		return 0;
    	if(x>5||y>5)
    		return 0;
    	if(Squares[y+1][x+1]==null)
    		return 0;
    	if(Squares[y][x].Color==Squares[y+1][x+1].Color)//Check if left square is the same color
    		return 0;
    	if(Squares[y+2][x+2]!=null)//Check if the left of the left square is not empty
    		return 0;		
	return 1;  	

    	
    }

    
    
    
	void WhiteMoveRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y-1][x+1]=new Soldier();
		Squares[y][x].Copy(Squares[y-1][x+1]);
		Squares[y][x]=null;
		KingCheck(y-1,x+1);//King Check



	}
	void BlackMoveRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y+1][x-1]=new Soldier();
		Squares[y][x].Copy(Squares[y+1][x-1]);
		KingCheck(y+1,x-1);//King Check
		Squares[y][x]=null;
			}

	void WhiteMoveLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y-1][x-1]=new Soldier();
		Squares[y][x].Copy(Squares[y-1][x-1]);
		Squares[y][x]=null;
		KingCheck(y-1,x-1);//King Check
	}


	void BlackMoveLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y+1][x+1]=new Soldier();
		Squares[y][x].Copy(Squares[y+1][x+1]);
		Squares[y][x]=null;
		KingCheck(y+1,x+1);//King Check
	}


	void WhiteEatLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y-2][x-2]=new Soldier();
		Squares[y][x].Copy(Squares[y-2][x-2]);
		Squares[y][x]=null;
		Squares[y-1][x-1]=null;
		KingCheck(y-2,x-2);//King Check
		}

	

	
	void BlackEatLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y+2][x+2]=new Soldier();
		Squares[y][x].Copy(Squares[y+2][x+2]);
		Squares[y][x]=null;
		Squares[y+1][x+1]=null;
		KingCheck(y+2,x+2);//King Check


	}

	
	
	void WhiteEatRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y-2][x+2]=new Soldier();
		Squares[y][x].Copy(Squares[y-2][x+2]);
		Squares[y][x]=null;
		Squares[y-1][x+1]=null;
		KingCheck(y-2,x+2);//King Check
	}


	void BlackEatRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		Squares[y+2][x-2]=new Soldier();
		Squares[y][x].Copy(Squares[y+2][x-2]);
		Squares[y][x]=null;
		Squares[y+1][x-1]=null;
		KingCheck(y+2,x-2);//King Check

	}

	
void KingCheck(int y, int x) {
	if(Squares[y][x].Is_King==false&&y==0&&Squares[y][x].Color==1) {
		Squares[y][x]=new King();
		Squares[y][x].Color=1;
		Squares[y][x].LocationX=x;
		Squares[y][x].LocationY=y;
		Squares[y][x].Is_King=true;
		
	}
	
	if(Squares[y][x].Is_King==false&&y==7&&Squares[y][x].Color==2) 
	{
		Squares[y][x]=new King();
		Squares[y][x].Color=2;
		Squares[y][x].LocationX=x;
		Squares[y][x].LocationY=y;
		Squares[y][x].Is_King=true;

	}

	
	
	}
	


}

