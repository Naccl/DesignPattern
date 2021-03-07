package com.naccl.gobang.client.model.chess;

public interface ChessFactory {
	public abstract Chess create(int x, int y, boolean isBlack);
}
