package Checkers;

public class Board {
   int Squares[][]=new int[8][8];
   
	void InitiateBoard(Board BOARD) 
    {
		//Black=1, White=2, Blank=0
        int i=0;
        for(i=0;i<7;i+=2) 
        	{
        	BOARD.Squares[0][i+1]=1;
        	BOARD.Squares[1][i]=1;
        	BOARD.Squares[2][i+1]=1;
        	BOARD.Squares[5][i]=2;
        	BOARD.Squares[6][i+1]=2;
        	BOARD.Squares[7][i]=2;
        	}
        }
        
    void PrintSquare(int i,int j) 
    {
    	System.out.print(Squares[i][j]);
    }
    void PrintBoard() 
    {
    	int i,j;
    	for(j=0;j<8;j++) 
    	{
        	for(i=0;i<8;i++) 
        	{
        		System.out.print(Squares[j][i]);
            	System.out.print(" | ");
        	}
        	System.out.println("\n");
        	}

 }
    
    



}


