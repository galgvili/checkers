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
        	System.out.println("\n");
        	}

 }
    
    int MoveOptions(int y,int x) //Check move options and return code
    {
    	int code=0;
    	if(RightMoveChecker(y,x)==1)
    		code++;
    	if(LeftMoveChecker(y,x)==1)
    		code+=2;
    	if(RightEatChecker(y,x)==1)
    		code+=10;
    	if(LeftEatChecker(y,x)==1)
    		code+=20;
    	return code;  				
    	//code values: 1=Only right|2=Only left|3=Right and Left|10=Only eat left|20=Only eat left|
    	//30=Eat right and left|12=Eat right and move left|21=Eat left and move right
	
    	
  	
    }
    int RightMoveChecker(int y, int x)//Check if can move right, 0=can't move, 1=can move, 2=can eat 
    {
    	if(Squares[y][x]==null)
    		return 0;
		if(Squares[y][x].Color==1) //If color is white
		{

    	if(x==7||y==0)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)
    		return 1;
		}
		else//If color is black
		{
	    	if(x==0||y==7)//Check if on board boundaries 
	    		return 0;
	    	if(Squares[y+1][x-1]==null)
	    		return 1;
		
		}
    	return 0;	
    	
    }
    int LeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move, 2=can eat 
    {
    	if(Squares[y][x]==null)
    		return 0;
		if(Squares[y][x].Color==1) //If color is white
		{
    	if(x==0||y==7)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 1;
		}
		else //If color is black
		{
	    	if(x==7||y==0)//Check if on board boundaries 
	    		return 0;
	    	if(Squares[y+1][x+1]==null)
	    		return 1;
		}
    	return 0;	
    }
    int RightEatChecker(int y, int x)
    {
    	if(Squares[y][x]==null)
    		return 0;
		if(Squares[y][x].Color==1) //If color is white
		{
    	if(x>5||y<2)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)//Check if right square is empty
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x+1].Color)//Check if right square is the same color
    		return 0;
    	if(Squares[y-2][x+2]!=null)//Check if the right of the right square is not empty
    		return 0;
		}
		else //If color is black
		{
	    	if(x<2||y>5)//Check if on board boundaries 
	    		return 0;
	    	if(Squares[y+1][x-1]==null)//Check if right square is empty
	    		return 0;
	    	if(Squares[y][x].Color==Squares[y+1][x-1].Color)//Check if right square is the same color
	    		return 0;
	    	if(Squares[y+2][x-2]!=null)//Check if the right of the right square is not empty
	    		return 0; 	
			
		}
    	return 1;  	
    }
    int LeftEatChecker(int y, int x)
    {
    	if(Squares[y][x]==null)//if square is empty
    		return 0;
		if(Squares[y][x].Color==1) //If color is white
		{
    	if(x<2||y<2)
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x-1].Color)//Check if left square is the same color
    		return 0;
    	if(Squares[y-2][x-2]!=null)//Check if the left of the left square is not empty
    		return 0;
		}
		else //If color is black
		{
	    	if(x>5||y>5)
	    		return 0;
	    	if(Squares[y+1][x+1]==null)
	    		return 0;
	    	if(Squares[y][x].Color==Squares[y+1][x+1].Color)//Check if left square is the same color
	    		return 0;
	    	if(Squares[y+2][x+2]!=null)//Check if the left of the left square is not empty
	    		return 0;		
		}
    	return 1;  	
    }

	void MoveLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		if(Squares[y][x].Color==1) //If color is white
		{
		Squares[y-1][x-1]=new Soldier();
		Squares[y-1][x-1].LocationX=x-1;
		Squares[y-1][x-1].LocationY=y-1;
		Squares[y-1][x-1].Color=1;
		Squares[y][x]=null;
		KingCheck(y-1,x-1);//King Check
			
		
		}
		else //if color is black
		{
		Squares[y+1][x+1]=new Soldier();
		Squares[y+1][x+1].LocationX=x+1;
		Squares[y+1][x+1].LocationY=y+1;
		Squares[y+1][x+1].Color=2;
		Squares[y][x]=null;
		KingCheck(y+1,x+1);//King Check

		}

	}

	void MoveRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
    	if(Squares[y][x].Color==1) //If color is white
		{
		Squares[y-1][x+1]=new Soldier();
		Squares[y-1][x+1].LocationX=x+1;
		Squares[y-1][x+1].LocationY=y-1;
		Squares[y-1][x+1].Color=1;
		Squares[y][x]=null;
		KingCheck(y-1,x+1);//King Check


		}
		else //if color is black
		{
		Squares[y+1][x-1]=new Soldier();
		Squares[y+1][x-1].LocationX=x+1;
		Squares[y+1][x-1].LocationY=y+1;
		Squares[y+1][x-1].Color=2;
		KingCheck(y+1,x-1);//King Check
		Squares[y][x]=null;
		}


	}

	void EatLeftForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		if(Squares[y][x].Color==1) //If color is white
		{
		Squares[y-2][x-2]=new Soldier();
		Squares[y-2][x-2].LocationX=x-2;
		Squares[y-2][x-2].LocationY=y-2;
		Squares[y-2][x-2].Color=1;
		Squares[y][x]=null;
		Squares[y-1][x-1]=null;
		KingCheck(y-2,x-2);//King Check

		
		}
		else //if color is black
		{
		Squares[y+2][x+2]=new Soldier();
		Squares[y+2][x+2].LocationX=x+2;
		Squares[y+2][x+2].LocationY=y-2;
		Squares[y+2][x+2].Color=2;
		Squares[y][x]=null;
		Squares[y+1][x+1]=null;
		KingCheck(y+2,x+2);//King Check

		}

	}

	void EatRightForward(int y, int x)
	{
    	if(Squares[y][x]==null)
    		return;
		if(Squares[y][x].Color==1) //If color is white
		{
		Squares[y-2][x+2]=new Soldier();
		Squares[y][x].Copy(Squares[y-2][x+2]);
		//Squares[y-2][x+2].LocationX=x+2;
		//Squares[y-2][x+2].LocationY=y-2;
		//Squares[y-2][x+2].Color=1;
		Squares[y][x]=null;
		Squares[y-1][x+1]=null;
		KingCheck(y-2,x+2);//King Check


		
		}
		else //if color is black
		{
		Squares[y+2][x-2]=new Soldier();
		Squares[y+2][x-2].LocationX=x-2;
		Squares[y+2][x-2].LocationY=y+2;
		Squares[y+2][x-2].Color=2;
		Squares[y][x]=null;
		Squares[y+1][x-1]=null;
		KingCheck(y+2,x-2);//King Check

		}

	}

	void KingCheck(int y, int x) {
	if(y==0&&Squares[y][x].Color==1) {
		Squares[y][x]=new King();
		Squares[y][x].Color=11;
		Squares[y][x].LocationX=x;
		Squares[y][x].LocationY=y;
		
	}
	
	if(y==7&&Squares[y][x].Color==2) 
	{
		Squares[y][x]=new King();
		Squares[y][x].Color=22;
		Squares[y][x].LocationX=x;
		Squares[y][x].LocationY=y;

	}

	
	
	}
	


}


