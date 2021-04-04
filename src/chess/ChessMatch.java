package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows();i++) {
			for(int j=0; j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat; 
	}
	
	//classe que retorna uma matriz de booleano com os possiveis movimentos da peça
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
		
	}
	
	
	// movimentando as pecas
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
		
	private Piece makeMove(Position source, Position target){
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position){
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no pice on source position");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possibles moves for the chosen piecen");
		}
	}
	
	private void validateTargetPosition(Position source, Position target){
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The choosen piece can't move to target position");
		}
	}
	
	
	// passando as posicioes de acordo com xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
	
	
	
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
	}
	
	/*private void InitialSetup() { 
		placeNewPiece('b', 6,new Rook(board,Color.WHITE));
		placeNewPiece('e', 8,new King(board,Color.BLACK));
		placeNewPiece('e', 1,new King(board,Color.WHITE));
		
		
		//posicoes no sistema de matriz
		board.placePiece(new Rook(board,Color.WHITE),new Position(2,1));
		board.placePiece(new King(board,Color.BLACK),new Position(0,4));
		board.placePiece(new King(board,Color.WHITE),new Position(7,4)); */
	}
