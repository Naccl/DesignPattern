package com.naccl.gobang.client.model.chess;

public class CommonChessFactory implements ChessFactory {
	@Override
	public CommonChess create(int x, int y, boolean isBlack) {
		return new CommonChess(x, y, isBlack);
	}
}
