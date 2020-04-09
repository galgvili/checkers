package Checkers;

public class Main {

	public static void main(String[] args) {
		Board BOARD=new Board();
		BOARD=BOARD.InitiateBoard();
    	//BOARD.Squares[4][2]=new Soldier();
    	//BOARD.Squares[4][2].Color=2;
		BOARD.PrintBoard();

		System.out.print(BOARD.WhiteMoveOptions(5, 1));
	}

}
