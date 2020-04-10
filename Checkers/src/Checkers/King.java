package Checkers;

public class King extends Pawn{
	
	void MoveRightBackward()
	{
		if(Color==1) //If color is White
		{
		LocationX++;
		LocationY--;
		}
		else //If color is Black
		{
			LocationX++;
			LocationY++;

		}
	}
	void MoveLeftBackward()
	{
		if(Color==1) //If color is White
		{
		LocationX--;
		LocationY--;
		}
		else //If color is Black
		{
			LocationX--;
			LocationY++;
		}
	}

}
