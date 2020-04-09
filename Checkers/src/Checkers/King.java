package Checkers;

public class King extends Pawn{
	
	void MoveRightBackward()
	{
		LocationX++;
		LocationY--;
	}
	void MoveLeftBackward()
	{
		LocationX--;
		LocationY--;
		
	}
}
