package com.naccl.gobang.client.model.chess;

import java.awt.*;

public class CommonChess implements Chess {
	private int x;
	private int y;
	private boolean isBlack;// 棋子颜色
	private boolean commonChess = true;

	public CommonChess(int x, int y, boolean isBlack) {
		this.x = x;
		this.y = y;
		this.isBlack = isBlack;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean isBlack() {
		return isBlack;
	}

	@Override
	public boolean isCommonChess() {
		return commonChess;
	}

	public static void draw(Graphics2D g2, boolean chessColor) {
		if (chessColor) g2.setColor(Color.BLACK);
		else g2.setColor(Color.WHITE);
	}
}