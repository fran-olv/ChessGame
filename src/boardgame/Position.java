package boardgame;

public class Position {
	
	private int row ;
	private	int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	//atualiza valores da posicao e da coluna
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	// printando as nossas posicoes na tela
	@Override //funcao que subscreve ...
	public String toString() {
		//a linha contatenada com a coluna.
		return row + ", " + column;
	}
	


}
