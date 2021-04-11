//nossa partida de xadrez, aqui estao as nossas regras.


package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Tabuleiro;
import tabuleiro.Piece;
import tabuleiro.Position;
import chess.pieces.Bispo;
import chess.pieces.Rei;
import chess.pieces.Cavalo;
import chess.pieces.Peao;
import chess.pieces.Rainha;
import chess.pieces.Torre;

//nessa classe iremos encontrar a nossa dimensao do tabuleiro
public class Partida {
	
	//variaveis que controlam o turno e qual jogador est� na vez
	private int turn; 
	private Color currentPlayer;
	//propriedade do xeque e xeque mate
	private boolean check;
	private boolean checkMate;
	
	
	//lista que controlam as pecas que estao no jogo e as que estao fora
	private List<Piece> piecesOnTheTabuleiro = new ArrayList<>();
	private List<Piece> capturadoPieces = new ArrayList<>();

		
	private Tabuleiro Tabuleiro;
	
	public Partida() {
		Tabuleiro = new Tabuleiro(8,8);
		
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
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	//nosso tabuleiro sendo printado 
	public PecaXadrez[][] getPieces() {
		PecaXadrez[][] mat = new PecaXadrez[Tabuleiro.getRows()][Tabuleiro.getColumns()];
		for (int i=0; i<Tabuleiro.getRows();i++) {
			for(int j=0; j<Tabuleiro.getColumns();j++) {
				mat[i][j] = (PecaXadrez) Tabuleiro.piece(i, j);
			}
		}
		return mat; 
	}
	
	//classe que retorna uma matriz de booleano com os possiveis movimentos da pe�a
	public boolean[][] MovPossivel(PosicaoXadrez sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return Tabuleiro.piece(position).MovPossivel();
	}
	
	
	// movimentando as pecas
		public PecaXadrez performChessMove(PosicaoXadrez sourcePosition, PosicaoXadrez targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturadoPiece = makeMove(source, target);
	
		
		if ( testCheck(currentPlayer) ) {  //se o jogador entrar em xeque
			undoMove(source, target, capturadoPiece);
			throw new ExcecaoXadrez("You can't put yourself in check");
		}
		//se o oponente entrou em xeque		
		check = (testCheck(opponent(currentPlayer))) ? true : false; //verdadeiro senao falso
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		nextTurn(); //troca o turno e jogador
		return (PecaXadrez)capturadoPiece;
		
		}
	
	
	private Piece makeMove(Position source, Position target){
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePiece(source);  //pega a peca de acordo com a posicao
		p.increaseMoveCount(); 
		Piece capturadoPiece = Tabuleiro.removePiece(target); //retira uma possivel peca capturada que esta na posicao de destino
		Tabuleiro.placePiece(p, target); // coloca no destino a peca que estava na posicao de origem
		
		if(capturadoPiece != null) { //existe peca capturada
			piecesOnTheTabuleiro.remove(capturadoPiece); //retira peca da lista do tabuleiro
			capturadoPieces.add(capturadoPiece); //coloca na lista de caputuradas
		}
		
		return capturadoPiece;
	}
	
	//desfaz a jogado. Caso entre em cheque sem querer
	private void undoMove(Position source, Position target, Piece capturadoPiece) {
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePiece(target);
		p.decreaseMoveCount();

		Tabuleiro.placePiece(p, source);
		
		if (capturadoPiece != null) {
			Tabuleiro.placePiece(capturadoPiece, target);
			capturadoPieces.remove(capturadoPiece);
			piecesOnTheTabuleiro.add(capturadoPiece);
			}		
		}
	
	
	private void validateSourcePosition(Position position){
		if(!Tabuleiro.thereIsAPiece(position)) {
			throw new ExcecaoXadrez("There is no pice on source position");
		}
		// verificar se o jogador atual tem a mesma cor das pecas que ele quer mover. 
		if(currentPlayer != ((PecaXadrez)Tabuleiro.piece(position)).getColor()) { //necessario fazer downCast porque getColo � proriedade do PecaXadrez nao da classe generica Piece
			throw new ExcecaoXadrez("The current chosen piece is not yours"); //se tentar mover a peca do advers�rio entra na excecao.
		}
		
		if(!Tabuleiro.piece(position).isThereAnyPossibleMove()) { //verificacao dos movimentos possiveis da peca
			throw new ExcecaoXadrez("There is no possibles moves for the chosen piecen");
		}
	}
	
	private void validateTargetPosition(Position source, Position target){
		if(!Tabuleiro.piece(source).possibleMove(target)) {
			throw new ExcecaoXadrez("The choosen piece can't move to target position");
		}
	}
	
	//classe que retorna o oponente de uma cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//localiza o rei de uma dada cor
	private PecaXadrez Rei(Color color) {
		List<Piece> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList()); //procura na lista das pecas em jogo 
		for (Piece p : list) { 
			if (p instanceof Rei) { // se a peca for uma instancia de rei. 
				return (PecaXadrez)p; //retorna a peca
			}
		}
		throw new IllegalStateException("There is no " + color + " Rei on the Tabuleiro. Jogo acabou!");
	}
	
	// testar se o jogo esta em xeque (se o rei daquela cor esta em xeque)
	private boolean testCheck(Color color) {
		Position ReiPosition = Rei(color).getPosicaoXadrez().toPosition(); //posicao do rei
		List<Piece> opponentPieces = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == opponent(color)).collect(Collectors.toList()); // filtrar a pecas com a cor das pecas do oponente do rei
		for (Piece p : opponentPieces) { //varre todas as pecas adversarias 
			boolean[][] mat = p.MovPossivel(); // se alguma delas tiver suas possibilidades de movimento passando pela casa do REI, esta em xeque
			if (mat[ReiPosition.getRow()][ReiPosition.getColumn()]) {
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
	
	//teste do cheque mate
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList()); //lista de pecas 
		for (Piece p : list) {
			boolean[][] mat = p.MovPossivel(); //faz uma matriz com os movimentos possiveis 
			for (int i=0; i<Tabuleiro.getRows(); i++) {
				for (int j=0; j<Tabuleiro.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((PecaXadrez)p).getPosicaoXadrez().toPosition();
						Position target = new Position(i, j);
						Piece capturadoPiece = makeMove(source, target);
						boolean testCheck = testCheck(color); //testa se ainda esta em xeque
						undoMove(source, target, capturadoPiece);
						if (!testCheck) { 
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	
	
	// passando as posicioes de acordo com mapeamento do xadrez
	private void placeNewPiece(char column, int row, PecaXadrez piece) {
		Tabuleiro.placePiece(piece, new PosicaoXadrez(column, row).toPosition());
		piecesOnTheTabuleiro.add(piece); // acrescenta a peca na lista de pecas no jogo
		
	}
	
	
	
	private void initialSetup() { //posicionamento para fazer um xeque mais rapido
		placeNewPiece('a', 1, new Torre(Tabuleiro, Color.WHITE));
        placeNewPiece('b', 1, new Cavalo(Tabuleiro, Color.WHITE));
        placeNewPiece('c', 1, new Bispo(Tabuleiro, Color.WHITE));
        placeNewPiece('d', 1, new Rainha(Tabuleiro, Color.WHITE));
        placeNewPiece('e', 1, new Rei(Tabuleiro, Color.WHITE));
        placeNewPiece('f', 1, new Bispo(Tabuleiro, Color.WHITE));
        placeNewPiece('g', 1, new Cavalo(Tabuleiro, Color.WHITE));
        placeNewPiece('h', 1, new Torre(Tabuleiro, Color.WHITE));
        placeNewPiece('a', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('b', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('c', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('d', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('e', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('f', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('g', 2, new Peao(Tabuleiro, Color.WHITE));
        placeNewPiece('h', 2, new Peao(Tabuleiro, Color.WHITE));

        placeNewPiece('a', 8, new Torre(Tabuleiro, Color.BLACK));
        placeNewPiece('b', 8, new Cavalo(Tabuleiro, Color.BLACK));
        placeNewPiece('c', 8, new Bispo(Tabuleiro, Color.BLACK));
        placeNewPiece('d', 8, new Rainha(Tabuleiro, Color.BLACK));
        placeNewPiece('e', 8, new Rei(Tabuleiro, Color.BLACK));
        placeNewPiece('f', 8, new Bispo(Tabuleiro, Color.BLACK));
        placeNewPiece('g', 8, new Cavalo(Tabuleiro, Color.BLACK));
        placeNewPiece('h', 8, new Torre(Tabuleiro, Color.BLACK));
        placeNewPiece('a', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('b', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('c', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('d', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('e', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('f', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('g', 7, new Peao(Tabuleiro, Color.BLACK));
        placeNewPiece('h', 7, new Peao(Tabuleiro, Color.BLACK));
	}
	
	/*private void InitialSetup() { 
		placeNewPiece('b', 6,new Torre(Tabuleiro,Color.WHITE));
		placeNewPiece('e', 8,new Rei(Tabuleiro,Color.BLACK));
		placeNewPiece('e', 1,new Rei(Tabuleiro,Color.WHITE));
		
		
		//posicoes no sistema de matriz
		Tabuleiro.placePiece(new Torre(Tabuleiro,Color.WHITE),new Position(2,1));
		Tabuleiro.placePiece(new Rei(Tabuleiro,Color.BLACK),new Position(0,4));
		Tabuleiro.placePiece(new Rei(Tabuleiro,Color.WHITE),new Position(7,4)); */
	}
