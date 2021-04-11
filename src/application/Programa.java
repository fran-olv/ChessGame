package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ExcecaoXadrez;
import chess.Partida;
import chess.PecaXadrez;
import chess.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) throws IOException {
				////testes////
		//Position pos = new Position(3, 5);
		//System.out.println(pos);
		
		//Board board = new Board(8,8);
		
		//Scanner sc  = new Scanner(System.in);
		//Partida Partida = new Partida();
		//List<PecaXadrez> capturado = new ArrayList<>();
		
				

		while (!Partida.getCheckMate()) {
			try {
				
				FileWriter arq = new FileWriter("LOG_jogadas.txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("Relatorio da partida%n");
				
				//usuario escolhe apeca que ele quer mover, baseado na posicao em que ela est�
				InterfaceUsario.clearScreen();
				InterfaceUsario.printMatch(Partida, capturado);
				System.out.println();
				System.out.println("Source: ");
				PosicaoXadrez source = InterfaceUsario.readPosicaoXadrez(sc);
				
				
				
				//posicoes validas sao printadas na tela
				boolean [][] MovPossivel = Partida.MovPossivel(source);
				InterfaceUsario.clearScreen();
				InterfaceUsario.printBoard(Partida.getPieces(), MovPossivel);
				
				// usuario escolhe 
				System.out.println();
				System.out.println("Target: ");
				PosicaoXadrez target = InterfaceUsario.readPosicaoXadrez(sc);
				
			
				
				PecaXadrez capturadoPiece = Partida.performChessMove(source, target);
				
				// se o moviemento resulta numa captura, a peca capturada � adicionada na lista
				if(capturadoPiece != null) {
					capturado.add(capturadoPiece);
				
				}
				
				arq.close();
			}
			
			
			
			catch(ExcecaoXadrez e){
				System.out.println(e.getMessage());
				sc.nextLine();			
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();			
			}
		}
		InterfaceUsario.clearScreen();
		InterfaceUsario.printMatch(Partida, capturado);
	}	
}
