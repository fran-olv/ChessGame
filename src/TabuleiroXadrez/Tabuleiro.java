//tabuleiro === resposavel pelo nosso tabuleiro 
package tabuleiro;

public class Tabuleiro {
	//quantidade de linhas e colunas 
	private int rows;
	private int columns;
	//matriz da nossas pecas 
	private Piece[][] pieces;
	
	public Tabuleiro(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new TabuleiroException("Error creating Tabuleiro: there must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;
		//quantidade de linhas e colunas da nossa matriz de pecas.
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}

	//printando nosso tabuleiro para o usuario visualizar...
	public Piece piece(int row, int column) {
		if(!positionExists(row,column)) {
			throw new TabuleiroException("Position not on the Tabuleiro");
		}
		
		return pieces[row][column];
	}	
		
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new TabuleiroException("Position not on the Tabuleiro");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}	
	
	//posiciona uma pe�a no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new TabuleiroException("There is already a piece on the position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new TabuleiroException("Position not on the Tabuleiro");
		}
		if (piece(position) == null){
			return null;
		}
		Piece aux = piece(position);
			aux.position = null;
			pieces[position.getRow()][position.getColumn()] = null;
			return aux;
	}
		
	public boolean positionExists(int row, int column) { 
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position){
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new TabuleiroException("Position not on the Tabuleiro");
		}
		return piece(position) != null;
	}
	
}
