package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][8];
   
	Board InitiateBoard() //Reset board pawns positions 
    {
		//Black=2, White=1, Blank=0
        int x,COLOR;
    	Board BOARD=new Board();
        for(x=0;x<7;x+=2) 
        	{
    		COLOR=2;
        	for(int y=0;y<8;y+=5) {
        	//y=0 - Black soldiers creation 
        	//y=5 White soldiers creation
        	BOARD.Squares[y][x+1]=new Soldier();
        	BOARD.Squares[y][x+1].Color=COLOR;
        	BOARD.Squares[y+1][x]=new Soldier();
        	BOARD.Squares[y+1][x].Color=COLOR;
        	BOARD.Squares[y+2][x+1]=new Soldier();
        	BOARD.Squares[y+2][x+1].Color=COLOR;
        	COLOR=1;
        	}
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
    
    int WhiteMoveOptions(int y,int x) //Check move options and return code
    {
    	int code=0;
    	if(WhiteRightMoveChecker(y,x)==1)
    		code++;
    	if(WhiteLeftMoveChecker(y,x)==1)
    		code+=2;
    	if(WhiteRightEatChecker(y,x)==1)
    		code+=10;
    	if(WhiteLeftEatChecker(y,x)==1)
    		code+=20;
    	return code;  				
    	//code values: 1=Only right|2=Only left|3=Right and Left|10=Only eat left|20=Only eat left|
    	//30=Eat right and left|12=Eat right and move left|21=Eat left and move right
	
    	
  	
    }
    int WhiteRightMoveChecker(int y, int x)//Check if can move right, 0=can't move, 1=can move, 2=can eat 
    {
    	if(x==7||y==7)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)
    		return 1;
    	if(WhiteRightEatChecker(x,y)==1)
    		return 2;
    	return 0;	
    }
    int WhiteLeftMoveChecker(int y, int x)//Check if can move left, 0=can't move, 1=can move, 2=can eat 
    {
    	if(x==0||y==7)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 1;
    	if(WhiteLeftEatChecker(x,y)==1)
    		return 2;
    	return 0;	
    }
    
    int WhiteRightEatChecker(int y, int x)
    {
    	if(x>5||y>5)//Check if on board boundaries 
    		return 0;
    	if(Squares[y-1][x+1]==null)//Check if right square is empty
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x+1].Color)//Check if right square is the same color
    		return 0;
    	if(Squares[y-2][x+2]!=null)//Check if the right of the right square is not empty
    		return 0;
    	return 1;  	
    }
    int WhiteLeftEatChecker(int y, int x)
    {
    	if(x<2||y>5)
    		return 0;
    	if(Squares[y-1][x-1]==null)
    		return 0;
    	if(Squares[y][x].Color==Squares[y-1][x-1].Color)//Check if left square is the same color
    		return 0;
    	if(Squares[y-2][x-2]!=null)//Check if the left of the left square is not empty
    		return 0;
    	return 1;  	
    }

    

}


