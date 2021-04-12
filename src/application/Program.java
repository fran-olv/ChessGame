package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) throws IOException {
				////testes////
		//Position pos = new Position(3, 5);
		//System.out.println(pos);
		
		//Board board = new Board(8,8);
		
		Scanner sc  = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
	 

		while (!chessMatch.getCheckMate()) {
			try {
				//usuario escolhe apeca que ele quer mover, baseado na posicao em que ela est�
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.println("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				//posicoes validas sao printadas na tela
				boolean [][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				// usuario escolhe 
				System.out.println();
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				// se o moviemento resulta numa captura, a peca capturada � adicionada na lista
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				
				}
				
			}
			
			
			
			catch(ChessException e){
				System.out.println(e.getMessage());
				sc.nextLine();			
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();			
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}	
}
