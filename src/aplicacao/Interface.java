package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrex;


public class Interface {
	//é preciso que o terminal seja preto e colorido como o do GITBash (IR na pasta Bin do Projeto e abrir o GitBash apartir dali)
	
	//codigos retirados de https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	//fica com a cor neutra, no caso de espaco em branco
	public static final String ANSI_RESET = "\u001B[0m";
	
	//cores do texto
	public static final String ANSI_PRETO = "\u001B[30m";
	public static final String ANSI_AMARELO = "\u001B[33m";
	public static final String ANSI_AZUL = "\u001B[34m";
	public static final String ANSI_BRANCO = "\u001B[37m";
	
	//cor do fundo que marca os movimentos possiveis
	public static final String ANSI_FUNDO_AZUL = "\u001B[44m";

	public static void limpaTela() {
		System.out.print("\033[H\033[2J"); ///move a ultima linha para se tornar a primeira
		System.out.flush();
	}
	
	public static PosicaoXadrex leituraPosicao(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrex(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posicao. Entre com um valor de a1 a h8");
		}
	}
	
	// printar a partida com o jogador da vez e o turno, e as pecas que foram capturadas
	public static void ImprimePartida(Partida partida, List<PecaXadrez> capturada) {
		
		imprimeTabuleiro(partida.getPieces());
		System.out.println();
		
		imprimePecasCapturadas(capturada);
		
		System.out.println("");
		System.out.println("Turno : " + partida.getTurno());
		System.out.println("Esperando Jogador: " + partida.getJogadorAtual());
		
		//se estivermos em xeeque
		if (!partida.getXequeMate()) {
			System.out.println("Esperando Jogador: " + partida.getJogadorAtual());
			if (partida.getXeque()) {
				System.out.println("XEQUE!");
				
			}
		}
		else {
			System.out.println("XEQUE MATE!");
			System.out.println("VENCEDOR: " + partida.getJogadorAtual());
		}
		
	}
	
	
	//Printa na tela o tabuleiro de xadrez onde as colunas sao mapeadas letras e as linhas por numeros
	public static void imprimeTabuleiro(PecaXadrez[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				imprimePeca(pieces[i][j], false); //pecas nao tem o fundo colorido
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	//printa durante a moviemntacao de uma peca os possiveis destinos que essa peca pode ter.
	public static void imprimeTabuleiro(PecaXadrez[][] pieces, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				imprimePeca(pieces[i][j], movimentosPossiveis[i][j]); 
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	//COLORE AS PEÇAS E O TABULEIRO
	private static void imprimePeca(PecaXadrez piece, boolean fundo) {
		if(fundo) {
			System.out.print(ANSI_FUNDO_AZUL);
		}
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_BRANCO + piece + ANSI_RESET);  //ANSI_AMARELO  
            }
            else {
                System.out.print(ANSI_AMARELO + piece + ANSI_RESET);  //ANSI_PRETO
            }
        }
        System.out.print(" ");
	}
	
	//imprimir as pecas capturadas
	private static void imprimePecasCapturadas(List<PecaXadrez> capturada) { // utilizacando filtro de lista para implementar 
		List<PecaXadrez> branco = capturada.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList()); //filtrando brancas
		List<PecaXadrez> preto = capturada.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList()); //filtrando pretas
		
		System.out.println("Pecas Capturadas: ");
		
		System.out.print("Branco: ");
		System.out.print(ANSI_BRANCO);
		System.out.println(Arrays.toString(branco.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Preto/Amarelo: ");
		System.out.print(ANSI_AMARELO);
		System.out.println(Arrays.toString(preto.toArray()));
		System.out.print(ANSI_RESET);
		
	}
	
	
}

