package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][8];

   
	void InitiateBoard(Board BOARD) //Reset board pawns positions 
    {
		//Black=2, White=1, Blank=0
        int x,y;
 	   for(y=0;y<8;y+=1) 
 	   {
 		   for(x=0;x<8;x+=1) 
 		   {
 			   if(BOARD.Squares[y][x]!=null)
 				   BOARD.Squares[y][x]=null;
 		   }
 		   }
y=0;
        for(x=0;x<7;x+=2) 
        	{
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
		if (Squares[0][3]!=null)
		System.out.print(Squares[0][3].Is_King);
		if (Squares[1][2]!=null)
		System.out.print(Squares[1][2].Is_King);

    	System.out.print("\n\n");


 }
       	
    

    
    
	
	


}

