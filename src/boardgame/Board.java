//Boardgame === resposavel pelo nosso tabuleiro 
package boardgame;

public class Board {
	//quantidade de linhas e colunas 
	private int rows;
	private int columns;
	//matriz da nossas pecas 
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
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
			throw new BoardException("Position not on the board");
		}
		
		return pieces[row][column];
	}	
		
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}	
	
	//posiciona uma peça no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on the position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
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
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
	
}
