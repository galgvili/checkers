package Checkers;

public class Board {
   Soldier Squares[][]=new Soldier[8][4];
   
	Board InitiateBoard() //Reset board pawns positions 
    {
		//Black=2, White=1, Blank=0
        int x,y;
    	Board BOARD=new Board();
    	//Black soldiers creation
    	for(y=0;y<3;y++)
        {
        	for(x=0;x<4;x++) 
        	{
        	BOARD.Squares[y][x]=new Soldier();
        	BOARD.Squares[y][x].Color=2;
        	}
        	}
    	//White soldiers creation
        for(y=5;y<8;y++)
        {
        	for(x=0;x<4;x++) 
        	{
        	BOARD.Squares[y][x]=new Soldier();
        	BOARD.Squares[y][x].Color=1;
        	}}
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
        	for(x=0;x<4;x++) 
        	{
        		if(Squares[y][x]==null)
                    	System.out.print("|0|0");
        		else if(y%2==1)
        		System.out.print("|"+Squares[y][x].Color+"|0");
        		else
            		System.out.print("|0|"+Squares[y][x].Color);

        	}
        	System.out.println("\n");
        	}

 }
    
    

}


