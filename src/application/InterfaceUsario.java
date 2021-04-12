package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.Partida;
import chess.PecaXadrez;
import chess.PosicaoXadrez;
import chess.Color;


public class InterfaceUsario {
	//� preciso que o terminal seja preto e colorido como o do GITBash (IR na pasta Bin do Projeto e abrir o GitBash apartir dali)
	
	//codigos retirados de https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	//cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
//cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez readPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		
		//gravarArq.printf("%c",coluna);
		//gravarArq.printf("%c",linha);
		//gravarArq.printf(" para ");
		
		return new PosicaoXadrez(coluna, linha);
				
		}
		catch (RuntimeException) {
			throw new InputMismatchException("Erro ao ler a posição. São válidos os valores de a1 a h8");
		}
		
	}
	
	// printar a partida com o jogador da vez e o turno, e as pecas que foram capturadas
	public static void printMatch(Partida Partida, List<PecaXadrez> capturado) {
		printTabuleiro(Partida.getPieces());
		System.out.println();
		printcapturadoPieces(capturado);
		System.out.println("");
		System.out.println("Turn : " + Partida.getTurn());
		System.out.println("Waiting player: " + Partida.getCurrentPlayer());
		
		//se estivermos em xeeque
		if (!Partida.getCheckMate()) {
			System.out.println("Waiting player: " + Partida.getCurrentPlayer());
			if (Partida.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + Partida.getCurrentPlayer());
		}
		
	}
	
	
	//Printa na tela o tabuleiro de xadrez onde as colunas sao mapeadas letras e as linhas por numeros
	public static void printTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPiece(pecas[i][j], false); //pecas nao tem o fundo colorido
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	//printa durante a moviemntacao de uma peca os possiveis destinos que essa peca pode ter.
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] MovPossivel) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPiece(pecas[i][j], MovPossivel[i][j]); 
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	//COLORE AS PE�AS E O TABULEIRO
	private static void printPiece(PecaXadrez peca, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);  //ANSI_YELLOW  //trocar para branca ser amarela e preta ser preta
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);  //ANSI_BLACK
            }
        }
        System.out.print(" ");
	}
	
	//imprimir as pecas capturadas
	private static void printcapturadoPieces(List<PecaXadrez> capturado) { // utilizacando filtro de lista para implementar 
		List<PecaXadrez> white = capturado.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList()); //filtrando brancas
		List<PecaXadrez> black = capturado.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList()); //filtrando pretas
		
		System.out.println("capturado pecas: ");
		
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Black");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}
	
	
}

