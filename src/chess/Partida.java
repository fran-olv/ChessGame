//nossa partida de xadrez, aqui estao as nossas regras.


package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import chess.pecas.Bispo;
import chess.pecas.Rei;
import chess.pecas.Cavalo;
import chess.pecas.Peao;
import chess.pecas.Rainha;
import chess.pecas.Torre;

//nessa classe iremos encontrar a nossa dimensao do tabuleiro
public class Partida {
	
	//variaveis que controlam o turno e qual jogador est� na vez
	private int turn; 
	private Cor currentPlayer;
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
		currentPlayer = Cor.WHITE; // primeiro a jogar peca branca
		
		initialSetup();
	}
	
	//gets das variaveis de turno
	public int getTurn() {
		return turn;
	}
	
	public Cor getCurrentPlayer(){
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
			throw new ExceptionXadrez("You can't put yourself in check");
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
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePeca(source);  //pega a peca de acordo com a posicao
		p.increaseMoveCount(); 
		Peca capturadoPiece = Tabuleiro.removePeca(target); //retira uma possivel peca capturada que esta na posicao de destino
		Tabuleiro.posicaoPeca(p, target); // coloca no destino a peca que estava na posicao de origem
		
		if(capturadoPiece != null) { //existe peca capturada
			piecesOnTheTabuleiro.remove(capturadoPiece); //retira peca da lista do tabuleiro
			capturadoPieces.add(capturadoPiece); //coloca na lista de caputuradas
		}
		
		return capturadoPiece;
	}
	
	//desfaz a jogado. Caso entre em cheque sem querer
	private void undoMove(Posicao source, Posicao target, Peca capturadoPiece) {
		PecaXadrez p = (PecaXadrez)Tabuleiro.removePeca(target);
		p.decreaseMoveCount();

		Tabuleiro.posicaoPeca(p, source);
		
		if (capturadoPiece != null) {
			Tabuleiro.posicaoPeca(capturadoPiece, target);
			capturadoPieces.remove(capturadoPiece);
			piecesOnTheTabuleiro.add(capturadoPiece);
			}		
		}
	
	
	private void validateSourcePosition(Posicao posicao){
		if(!Tabuleiro.temPeca(posicao)) {
			throw new ExceptionXadrez("There is no pice on source posicao");
		}
		// verificar se o jogador atual tem a mesma cor das pecas que ele quer mover. 
		if(currentPlayer != ((PecaXadrez)Tabuleiro.peca(posicao)).getCor()) { //necessario fazer downCast porque getColo � proriedade do PecaXadrez nao da classe generica Peca
			throw new ExceptionXadrez("The current chosen peca is not yours"); //se tentar mover a peca do advers�rio entra na excecao.
		}
		
		if(!Tabuleiro.peca(posicao).MovimentoPossivel()) { //verificacao dos movimentos possiveis da peca
			throw new ExceptionXadrez("There is no possibles moves for the chosen piecen");
		}
	}
	
	private void validateTargetPosition(Posicao source, Posicao target){
		if(!Tabuleiro.peca(source).possibleMove(target)) {
			throw new ExceptionXadrez("The choosen peca can't move to target posicao");
		}
	}
	
	//classe que retorna o oponente de uma cor
	private Cor opponent(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	//localiza o rei de uma dada cor
	private PecaXadrez Rei(Cor cor) {
		List<Piece> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList()); //procura na lista das pecas em jogo 
		for (Piece p : list) { 
			if (p instanceof Rei) { // se a peca for uma instancia de rei. 
				return (PecaXadrez)p; //retorna a peca
			}
		}
		throw new IllegalStateException("There is no " + cor + " Rei on the Tabuleiro. Jogo acabou!");
	}
	
	// testar se o jogo esta em xeque (se o rei daquela cor esta em xeque)
	private boolean testCheck(Cor cor) {
		Posicao kingPosition = Rei(cor).getPosicaoXadrez().toPosition(); //posicao do rei
		List<Peca> opponentPieces = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == opponent(cor)).collect(Collectors.toList()); // filtrar a pecas com a cor das pecas do oponente do rei
		for (Peca p : opponentPieces) { //varre todas as pecas adversarias 
			boolean[][] mat = p.MovPossivel(); // se alguma delas tiver suas possibilidades de movimento passando pela casa do REI, esta em xeque
			if (mat[kingPosition.getLinha()][kingPosition.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	
	//chamado depois que tiver executado uma jogada 
	//classe responsavel por trocar o turno = incrementando-o e trocando o jogador atual
	private void nextTurn() {
		turn++;	
		currentPlayer = (currentPlayer==Cor.WHITE) ? Cor.BLACK : Cor.WHITE;  //Se for o branco, entao recebe o Preto, se nao recebe Branco
	}
	
	//teste do cheque mate
	private boolean testCheckMate(Cor cor) {
		if (!testCheck(cor)) {
			return false;
		}
		List<Peca> list = piecesOnTheTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList()); //lista de pecas 
		for (Peca p : list) {
			boolean[][] mat = p.MovPossivel(); //faz uma matriz com os movimentos possiveis 
			for (int i=0; i<Tabuleiro.getlinhas(); i++) {
				for (int j=0; j<Tabuleiro.getcolunas(); j++) {
					if (mat[i][j]) {
						Posicao source = ((PecaXadrez)p).getPosicaoXadrez().toPosition();
						Posicao target = new Posicao(i, j);
						Peca capturadoPiece = makeMove(source, target);
						boolean testCheck = testCheck(cor); //testa se ainda esta em xeque
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
		Tabuleiro.posicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		piecesOnTheTabuleiro.add(peca); // acrescenta a peca na lista de pecas no jogo
		
	}
	
	
	
	private void initialSetup() { //posicionamento para fazer um xeque mais rapido
		placeNewPiece('a', 1, new Torre(Tabuleiro, Cor.WHITE));
        placeNewPiece('b', 1, new Cavalo(Tabuleiro, Cor.WHITE));
        placeNewPiece('c', 1, new Bispo(Tabuleiro, Cor.WHITE));
        placeNewPiece('d', 1, new Rainha(Tabuleiro, Cor.WHITE));
        placeNewPiece('e', 1, new Rei(Tabuleiro, Cor.WHITE));
        placeNewPiece('f', 1, new Bispo(Tabuleiro, Cor.WHITE));
        placeNewPiece('g', 1, new Cavalo(Tabuleiro, Cor.WHITE));
        placeNewPiece('h', 1, new Torre(Tabuleiro, Cor.WHITE));
        placeNewPiece('a', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('b', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('c', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('d', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('e', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('f', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('g', 2, new Peao(Tabuleiro, Cor.WHITE));
        placeNewPiece('h', 2, new Peao(Tabuleiro, Cor.WHITE));

        placeNewPiece('a', 8, new Torre(Tabuleiro, Cor.BLACK));
        placeNewPiece('b', 8, new Cavalo(Tabuleiro, Cor.BLACK));
        placeNewPiece('c', 8, new Bispo(Tabuleiro, Cor.BLACK));
        placeNewPiece('d', 8, new Rainha(Tabuleiro, Cor.BLACK));
        placeNewPiece('e', 8, new Rei(Tabuleiro, Cor.BLACK));
        placeNewPiece('f', 8, new Bispo(Tabuleiro, Cor.BLACK));
        placeNewPiece('g', 8, new Cavalo(Tabuleiro, Cor.BLACK));
        placeNewPiece('h', 8, new Torre(Tabuleiro, Cor.BLACK));
        placeNewPiece('a', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('b', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('c', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('d', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('e', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('f', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('g', 7, new Peao(Tabuleiro, Cor.BLACK));
        placeNewPiece('h', 7, new Peao(Tabuleiro, Cor.BLACK));
	}
	
	/*private void InitialSetup() { 
		placeNewPiece('b', 6,new Torre(Tabuleiro,Cor.WHITE));
		placeNewPiece('e', 8,new Rei(Tabuleiro,Cor.BLACK));
		placeNewPiece('e', 1,new Rei(Tabuleiro,Cor.WHITE));
		
		
		//posicoes no sistema de matriz
		Tabuleiro.placePiece(new Torre(Tabuleiro,Cor.WHITE),new Posicao(2,1));
		Tabuleiro.placePiece(new Rei(Tabuleiro,Cor.BLACK),new Posicao(0,4));
		Tabuleiro.placePiece(new Rei(Tabuleiro,Cor.WHITE),new Posicao(7,4)); */
	}
