//nossa partida de xadrez, aqui estao as nossas regras.


package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import chess.pecas.Bishop;
import chess.pecas.King;
import chess.pecas.Knight;
import chess.pecas.Pawn;
import chess.pecas.Queen;
import chess.pecas.Rook;

//nessa classe iremos encontrar a nossa dimensao do tabuleiro
public class Partida {
	
	//variaveis que controlam o turno e qual jogador est� na vez
	private int turn; 
	private Color currentPlayer;
	//propriedade do xeque e xeque mate
	private boolean check;
	private boolean checkMate;
	
	
	//lista que controlam as pecas que estao no jogo e as que estao fora
	private List<Peca> piecesOnTheTabuleiro = new ArrayList<>();
	private List<Peca> capturadoPieces = new ArrayList<>();

		
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
		PecaXadrez[][] mat = new PecaXadrez[Tabuleiro.getlinhas()][Tabuleiro.getcolunas()];
		for (int i=0; i<Tabuleiro.getlinhas();i++) {
			for(int j=0; j<Tabuleiro.getcolunas();j++) {
				mat[i][j] = (PecaXadrez) Tabuleiro.peca(i, j);
			}
		}
		return mat; 
	}
	
	//classe que retorna uma matriz de booleano com os possiveis movimentos da pe�a
	public boolean[][] MovPossivel(PosicaoXadrez sourcePosition){
		Posicao posicao = sourcePosition.toPosition();
		validateSourcePosition(posicao);
		return Tabuleiro.peca(posicao).MovPossivel();
	}
	
	
	// movimentando as pecas
		public PecaXadrez performChessMove(PosicaoXadrez sourcePosition, PosicaoXadrez targetPosition) {
		Posicao source = sourcePosition.toPosition();
		Posicao target = targetPosition.toPosition();
		validateSourcePosition(source);
		Peca capturadoPiece = makeMove(source, target);
	
		
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
	
	
	private Peca makeMove(Posicao source, Posicao target){
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePiece(source);  //pega a peca de acordo com a posicao
		p.increaseMoveCount(); 
		Peca capturadoPiece = Tabuleiro.removePiece(target); //retira uma possivel peca capturada que esta na posicao de destino
		Tabuleiro.placePiece(p, target); // coloca no destino a peca que estava na posicao de origem
		
		if(capturadoPiece != null) { //existe peca capturada
			piecesOnTheTabuleiro.remove(capturadoPiece); //retira peca da lista do tabuleiro
			capturadoPieces.add(capturadoPiece); //coloca na lista de caputuradas
		}
		
		return capturadoPiece;
	}
	
	//desfaz a jogado. Caso entre em cheque sem querer
	private void undoMove(Posicao source, Posicao target, Peca capturadoPiece) {
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePiece(target);
		p.decreaseMoveCount();

		Tabuleiro.placePiece(p, source);
		
		if (capturadoPiece != null) {
			Tabuleiro.placePiece(capturadoPiece, target);
			capturadoPieces.remove(capturadoPiece);
			piecesOnTheTabuleiro.add(capturadoPiece);
			}		
		}
	
	
	private void validateSourcePosition(Posicao posicao){
		if(!Tabuleiro.thereIsAPiece(posicao)) {
			throw new ExcecaoXadrez("There is no pice on source posicao");
		}
		// verificar se o jogador atual tem a mesma cor das pecas que ele quer mover. 
		if(currentPlayer != ((PecaXadrez)Tabuleiro.peca(posicao)).getColor()) { //necessario fazer downCast porque getColo � proriedade do PecaXadrez nao da classe generica Peca
			throw new ExcecaoXadrez("The current chosen peca is not yours"); //se tentar mover a peca do advers�rio entra na excecao.
		}
		
		if(!Tabuleiro.peca(posicao).isThereAnyPossibleMove()) { //verificacao dos movimentos possiveis da peca
			throw new ExcecaoXadrez("There is no possibles moves for the chosen piecen");
		}
	}
	
	private void validateTargetPosition(Posicao source, Posicao target){
		if(!Tabuleiro.peca(source).possibleMove(target)) {
			throw new ExcecaoXadrez("The choosen peca can't move to target posicao");
		}
	}
	
	//classe que retorna o oponente de uma cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//localiza o rei de uma dada cor
	private PecaXadrez king(Color color) {
		List<Peca> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList()); //procura na lista das pecas em jogo 
		for (Peca p : list) { 
			if (p instanceof King) { // se a peca for uma instancia de rei. 
				return (PecaXadrez)p; //retorna a peca
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the Tabuleiro. Jogo acabou!");
	}
	
	// testar se o jogo esta em xeque (se o rei daquela cor esta em xeque)
	private boolean testCheck(Color color) {
		Posicao kingPosition = king(color).getPosicaoXadrez().toPosition(); //posicao do rei
		List<Peca> opponentPieces = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == opponent(color)).collect(Collectors.toList()); // filtrar a pecas com a cor das pecas do oponente do rei
		for (Peca p : opponentPieces) { //varre todas as pecas adversarias 
			boolean[][] mat = p.MovPossivel(); // se alguma delas tiver suas possibilidades de movimento passando pela casa do REI, esta em xeque
			if (mat[kingPosition.getLinha()][kingPosition.getColumn()]) {
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
		List<Peca> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList()); //lista de pecas 
		for (Peca p : list) {
			boolean[][] mat = p.MovPossivel(); //faz uma matriz com os movimentos possiveis 
			for (int i=0; i<Tabuleiro.getlinhas(); i++) {
				for (int j=0; j<Tabuleiro.getcolunas(); j++) {
					if (mat[i][j]) {
						Posicao source = ((PecaXadrez)p).getPosicaoXadrez().toPosition();
						Posicao target = new Posicao(i, j);
						Peca capturadoPiece = makeMove(source, target);
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
	private void placeNewPiece(char coluna, int linha, PecaXadrez peca) {
		Tabuleiro.placePiece(peca, new PosicaoXadrez(coluna, linha).toPosition());
		piecesOnTheTabuleiro.add(peca); // acrescenta a peca na lista de pecas no jogo
		
	}
	
	
	
	private void initialSetup() { //posicionamento para fazer um xeque mais rapido
		placeNewPiece('a', 1, new Rook(Tabuleiro, Color.WHITE));
        placeNewPiece('b', 1, new Knight(Tabuleiro, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(Tabuleiro, Color.WHITE));
        placeNewPiece('d', 1, new Queen(Tabuleiro, Color.WHITE));
        placeNewPiece('e', 1, new King(Tabuleiro, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(Tabuleiro, Color.WHITE));
        placeNewPiece('g', 1, new Knight(Tabuleiro, Color.WHITE));
        placeNewPiece('h', 1, new Rook(Tabuleiro, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(Tabuleiro, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(Tabuleiro, Color.WHITE));

        placeNewPiece('a', 8, new Rook(Tabuleiro, Color.BLACK));
        placeNewPiece('b', 8, new Knight(Tabuleiro, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(Tabuleiro, Color.BLACK));
        placeNewPiece('d', 8, new Queen(Tabuleiro, Color.BLACK));
        placeNewPiece('e', 8, new King(Tabuleiro, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(Tabuleiro, Color.BLACK));
        placeNewPiece('g', 8, new Knight(Tabuleiro, Color.BLACK));
        placeNewPiece('h', 8, new Rook(Tabuleiro, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(Tabuleiro, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(Tabuleiro, Color.BLACK));
	}
	
	/*private void InitialSetup() { 
		placeNewPiece('b', 6,new Rook(Tabuleiro,Color.WHITE));
		placeNewPiece('e', 8,new King(Tabuleiro,Color.BLACK));
		placeNewPiece('e', 1,new King(Tabuleiro,Color.WHITE));
		
		
		//posicoes no sistema de matriz
		Tabuleiro.placePiece(new Rook(Tabuleiro,Color.WHITE),new Posicao(2,1));
		Tabuleiro.placePiece(new King(Tabuleiro,Color.BLACK),new Posicao(0,4));
		Tabuleiro.placePiece(new King(Tabuleiro,Color.WHITE),new Posicao(7,4)); */
	}
