package Checkers;

public class Main {

	public static void main(String[] args) {
		Board BOARD=new Board();
		BOARD=BOARD.InitiateBoard();
    	//BOARD.Squares[4][0]=new Soldier();
    	//BOARD.Squares[4][0].Color=1;
		//BOARD.Squares[5][0]=null;
		BOARD.PrintBoard();
	}

}
