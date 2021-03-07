package com.naccl.gobang.client.model.chess;

public class SuperChessFactory implements ChessFactory {
	@Override
	public SuperChess create(int x, int y, boolean isBlack) {
		return new SuperChess(x, y, isBlack);
	}
}
