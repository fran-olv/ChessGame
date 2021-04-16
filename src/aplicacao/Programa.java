package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ExcecaoDeXadrez;
import chess.Partida;
import chess.PecaXadrez;
import chess.PosicaoXadrex;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Partida partidaXadrez = new Partida();
		List<PecaXadrez> capturadas = new ArrayList<>();
		
		while (!partidaXadrez.getXequeMate()) {
			try {
			//usuario escolhe apeca que ele quer mover, baseado na posicao em que ela esta				
				Interface.limpaTela();
				Interface.ImprimePartida(partidaXadrez, capturadas);
				System.out.println();
				System.out.print("Escolha uma peca de acordo com a posicao dela: ");
				PosicaoXadrex origem = Interface.leituraPosicao(sc);
				
				boolean[][] movimentosPossiveis = partidaXadrez.MovimentosPossiveis(origem);
				
				//posicoes validas sao printadas na tela
				Interface.limpaTela();
				Interface.imprimeTabuleiro(partidaXadrez.getPieces(), movimentosPossiveis);
				
				// usuario escolhe 
				System.out.println();
				System.out.print("Destino da peca Escolhida: ");
				PosicaoXadrex destino = Interface.leituraPosicao(sc);
				
				PecaXadrez pecaCapturada = partidaXadrez.facaMovimento(origem, destino);
				// se o moviemento resulta numa captura, a peca capturada e adicionada na lista
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
			}
			catch (ExcecaoDeXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		Interface.limpaTela();
		Interface.ImprimePartida(partidaXadrez, capturadas);
	}
}