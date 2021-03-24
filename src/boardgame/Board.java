package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}	
		
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}	
	
	//posiciona uma peça no tabuleiro
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
		
	public boolean positionExists(int row, int column) { 
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position){
		return positionExists(position.getRow(), position.getColumn());
	}
	
}
