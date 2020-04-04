package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][8];
   
	void BoardReset(Board BOARD) //Reset board pawns positions 
    {
		//Black=1, White=2, Blank=0
        int x;
        for(x=0;x<7;x+=2) 
        	{
        	//Black soldiers creation
        	BOARD.Squares[0][x+1]=new Soldier();
        	BOARD.Squares[0][x+1].Color=1;
        	BOARD.Squares[1][x]=new Soldier();
        	BOARD.Squares[1][x].Color=1;
        	BOARD.Squares[2][x+1]=new Soldier();
        	BOARD.Squares[2][x+1].Color=1;
        	//White soldiers creation
        	BOARD.Squares[5][x]=new Soldier();
        	BOARD.Squares[5][x].Color=2;
        	BOARD.Squares[6][x+1]=new Soldier();
        	BOARD.Squares[6][x+1].Color=2;
        	BOARD.Squares[7][x]=new Soldier();
        	BOARD.Squares[7][x].Color=2;
        	}
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


