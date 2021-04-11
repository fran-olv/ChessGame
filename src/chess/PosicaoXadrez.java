package chess;

import boardgame.Position;

public class PosicaoXadrez {
	private char column;
	private int row;
	
	
	public PosicaoXadrez(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 | row > 8) {
			throw new ExcecaoXadrez(" Error instantiating PosicaoXadrez. Valid values are from a1 from h8. ");
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
		return new Position( 8 - row, column - 'a');
	}
	
		//converte a posicao da matrix pra posicao do xadrez
	protected static PosicaoXadrez fromPosition(Position position) {
		return new PosicaoXadrez((char)('a' + position.getColumn()), 8 - position.getRow());
		
	}
	
	
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}

