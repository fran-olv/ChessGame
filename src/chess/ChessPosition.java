package chess;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 | row > 8) {
			throw new ChessException(" Error instantiating ChessPosition. Valid values are from a1 from h8. ");
		}
		this.column = column;
		this.row = row;
	}


	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position( 8 - row, column - 'a');  //linha 8 corresponde a 0 na matriz, linha 7 corresponde a 1 na matriz
	}
	
		
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow()); //forma inversa do acima 
		
	}
	
	//imprimindo a posição 
	@Override
	public String toString() {
		return "" + column + row; // " " forçando o compilador a entender que é uma compilação de strings
	}
	
}

