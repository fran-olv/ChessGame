package chess.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import chess.PecaXadrez;
import chess.Color;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro Tabuleiro, Color color) {
		super(Tabuleiro, color);
		
	}

	@Override
	public boolean[][] MovPossivel() {
		
		boolean [][] mat = new boolean[getTabuleiro().getlinhas()][getTabuleiro().getcolunas()];
		
		Posicao p = new Posicao(0,0);
		
		//regraDoPeaoBranco = se move linha para cima ou seja na mtriz � -1 
		if(getColor() == Color.WHITE) {
			 //1 posicao para frente
			p.setValues(posicao.getLinha()- 1, posicao.getColumn());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para frente
			p.setValues(posicao.getLinha()- 2, posicao.getColumn()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()- 1, posicao.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas pra frente 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValues(posicao.getLinha() - 1, posicao.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValues(posicao.getLinha() - 1, posicao.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColumn()] = true;
			}
			
		}
		else { //se nao � branca � preta. OBS: o posicao ROW vai ser + 1 
			
			//1 posicao para baixo
			p.setValues(posicao.getLinha() + 1, posicao.getColumn());
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColumn()] = true;
			}
			//peao se movendo 2 linhas para baixo
			p.setValues(posicao.getLinha() + 2, posicao.getColumn()); //2 posicao acima
			Posicao p2 = new Posicao(posicao.getLinha()- 1, posicao.getColumn());
			
			
			//testa para ver se o peao pode mover 2 casas parra baixo
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) { //existe a posicao e esta vazia
				mat[p.getLinha()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal esquerda quando tiver pe�a adversaria
			p.setValues(posicao.getLinha() + 1, posicao.getColumn() - 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColumn()] = true;
			}
			
			//teste do mov diagonal direita quando tiver pe�a adversaria
			p.setValues(posicao.getLinha() + 1, posicao.getColumn() + 1 );
			//testa para ver se o peao pode mover 
			if(getTabuleiro().positionExists(p) && IsThereOpponentPiece(p)) { //existe a posicao e tem uma peca do oponente ali
				mat[p.getLinha()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "p";
	}
	

}
