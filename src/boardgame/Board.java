package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pe�as 
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column"); //lan�ando exce��o personalizada
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}

	
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
	
	//posiciona uma pe�a no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on the position " + position); //verificando se j� existe uma pe�a na posi��o escolhida
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; //pe�a n�o est� mais na posi��o nula e a posi��o da pe�a � acessada livremente 
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board"); //defensiva 
		}
		if (piece(position) == null){
			return null;
		}
		Piece aux = piece(position);
			aux.position = null; //peca retirada do tabuleiro
			pieces[position.getRow()][position.getColumn()] = null; //onde remover a peca, sera nulo na matriz de pecas
			return aux;
	}
	
	//teste se a posi��o existe pela linha e pela coluna 	
	public boolean positionExists(int row, int column) { 
		return row >= 0 && row < rows && column >= 0 && column < columns;
	} 
	
	public boolean positionExists(Position position){
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//testando se tem uma pe�a na posi��o escolhida
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board"); //antes de testar se tem uma pe�a na posi��o, testa se a posi��o existe 
		}
		return piece(position) != null;
	}
	
}
