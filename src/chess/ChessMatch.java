//nossa partida de xadrez, aqui estao as nossas regras.


package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

//nessa classe iremos encontrar a nossa dimensao do tabuleiro
public class ChessMatch {
	
	//variaveis que controlam o turno e qual jogador est� na vez
	private int turn; 
	private Color currentPlayer;
	//propriedade do xeque e xeque mate
	private boolean check;
	private boolean checkMate;
	
	
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
	
	//retorna  apropriedade de xeque
	
	public boolean getCheck() {
		return check;
	}
	
	
	//nosso tabuleiro sendo printado 
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
	
		
		if ( testCheck(currentPlayer) ) {  //se o jogador entrar em xeque
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		//se o oponente entrou em xeque		
		check = (testCheck(opponent(currentPlayer))) ? true : false; //verdadeiro senao falso
		
		nextTurn(); //troca o turno e jogador
		return (ChessPiece)capturedPiece;
		
		}
	
	
	private Piece makeMove(Position source, Position target){
		ChessPiece p = (ChessPiece)board.removePiece(source);  //pega a peca de acordo com a posicao
		p.increaseMoveCount(); 
		Piece capturedPiece = board.removePiece(target); //retira uma possivel peca capturada que esta na posicao de destino
		board.placePiece(p, target); // coloca no destino a peca que estava na posicao de origem
		
		if(capturedPiece != null) { //existe peca capturada
			piecesOnTheBoard.remove(capturedPiece); //retira peca da lista do tabuleiro
			capturedPieces.add(capturedPiece); //coloca na lista de caputuradas
		}
		
		return capturedPiece;
	}
	
	//desfaz a jogado. Caso entre em cheque sem querer
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();

		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			}		
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
	
	//classe que retorna o oponente de uma cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//localiza o rei de uma dada cor
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); //procura na lista das pecas em jogo 
		for (Piece p : list) { 
			if (p instanceof King) { // se a peca for uma instancia de rei. 
				return (ChessPiece)p; //retorna a peca
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board. Jogo acabou!");
	}
	
	// testar se o jogo esta em xeque (se o rei daquela cor esta em xeque)
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition(); //posicao do rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); // filtrar a pecas com a cor das pecas do oponente do rei
		for (Piece p : opponentPieces) { //varre todas as pecas adversarias 
			boolean[][] mat = p.possibleMoves(); // se alguma delas tiver suas possibilidades de movimento passando pela casa do REI, esta em xeque
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	
	//chamado depois que tiver executado uma jogada 
	//classe responsavel por trocar o turno = incrementando-o e trocando o jogador atual
	private void nextTurn() {
		turn++;	
		currentPlayer = (currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;  //Se for o branco, entao recebe o Preto, se nao recebe Branco
	}
	
	
	
	// passando as posicioes de acordo com mapeamento do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece); // acrescenta a peca na lista de pecas no jogo
		
	}
	
	
	
	private void initialSetup() { //posicionamento para fazer um xeque mais rapido
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
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
