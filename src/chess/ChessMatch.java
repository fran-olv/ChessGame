package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//possui as regras do jogo 
public class ChessMatch {
	
	//variaveis que controlam o turno e qual jogador est� na vez
	private int turn; 
	private Color currentPlayer;

	
	//lista que controlam as pecas que estao no jogo e as que estao fora
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();


	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		
		turn = 1; // primeiro turno 
		currentPlayer = Color.WHITE; // primeiro a jogar peca branca
		
		initialSetup();
	}
	
	//gets das variaveis de turno
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer(){
		return  currentPlayer;
	}
	
	//retorna a matriz de pe�as correspondente a partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows();i++) {
			for(int j=0; j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat; 
	}
	
	//classe que retorna uma matriz de booleano com os possiveis movimentos da pe�a
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
		nextTurn(); //troca o turno e jogador
		return (ChessPiece)capturedPiece;
	}
		
	private Piece makeMove(Position source, Position target){
		Piece p = board.removePiece(source);  //pega a pega de acordo com a posicao
		Piece capturedPiece = board.removePiece(target); //retira uma possivel peca capturada que esta na posicao de destino
		board.placePiece(p, target); // coloca no destino a peca que estava na posicao de origem
		
		if(capturedPiece != null) { //existe peca capturada
			piecesOnTheBoard.remove(capturedPiece); //retira peca da lista do tabuleiro
			capturedPieces.add(capturedPiece); //coloca na lista de caputuradas
		}
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position){
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no pice on source position");
		}
		// verificar se o jogador atual tem a mesma cor das pecas que ele quer mover. 
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { //necessario fazer downCast porque getColo � proriedade do chessPiece nao da classe generica Piece
			throw new ChessException("The current chosen piece is not yours"); //se tentar mover a peca do advers�rio entra na excecao.
		}
		
		if(!board.piece(position).isThereAnyPossibleMove()) { //verificacao dos movimentos possiveis da peca
			throw new ChessException("There is no possibles moves for the chosen piecen");
		}
	}
	
	private void validateTargetPosition(Position source, Position target){
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The choosen piece can't move to target position");
		}
	}
	
	//chamado depois que tiver executado uma jogada 
	//classe responsavel por trocar o turno = incrementando-o e trocando o jogador atual
	private void nextTurn() {
		turn++;	
		currentPlayer = (currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;  //Se for o branco, entao recebe o Preto, se nao recebe Branco
	}
	
	
	
	// passando as posicoes de acordo com mapeamento do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece); // acrescenta a peca na lista de pecas no jogo
		
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
