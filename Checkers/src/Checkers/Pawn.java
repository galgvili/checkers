package Checkers;
public abstract class Pawn 
{
	int LocationX;  //Pawn X location over board
	int LocationY; // Pawn Y location over board
	int Color; //Black=1, White=2
	
	void MoveRightForward()
	{
		if (Color==0) 
		{
		LocationX++;
		LocationY++;
		}
		
	}
	void MoveLeftForward()
	{
		LocationX--;
		LocationY++;

		
	}
	
}
