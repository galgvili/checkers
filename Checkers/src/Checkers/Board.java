package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][8];
   
	Board InitiateBoard() //Reset board pawns positions 
    {
		//Black=1, White=2, Blank=0
        int x;
    	Board BOARD=new Board();
        for(x=0;x<7;x+=2) 
        	{
        	for(int y=0;y<8;y+=5) {
        	//y=0 - Black soldiers creation 
        	//y=5 White soldiers creation
        	BOARD.Squares[y][x+1]=new Soldier();
        	BOARD.Squares[y][x+1].Color=1;
        	BOARD.Squares[y+1][x]=new Soldier();
        	BOARD.Squares[y+1][x].Color=1;
        	BOARD.Squares[y+2][x+1]=new Soldier();
        	BOARD.Squares[y+2][x+1].Color=1;
        	}
        	}
        return BOARD;
        }
    

	
	
	
	
	
	
	void PrintSquare(int y,int x) //Print specific board square.
    {
    	System.out.print(Squares[y][x]);
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
    
    



}


