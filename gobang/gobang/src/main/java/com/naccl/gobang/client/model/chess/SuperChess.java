package com.naccl.gobang.client.model.chess;

import java.awt.*;

public class SuperChess implements Chess{
	private int x;
	private int y;
	private boolean isBlack;// 棋子颜色
	private boolean commonChess = false;

	public SuperChess(int x, int y, boolean isBlack) {
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

	public static void draw(Graphics2D g2,boolean chessColor,int centerX,int centerY,int GRID_SPACING){
		int rgpRadius;
		if (chessColor) rgpRadius = 20;
		else rgpRadius = 70;
		RadialGradientPaint paint = new RadialGradientPaint(centerX + GRID_SPACING / 4, centerY - GRID_SPACING / 4, rgpRadius, new float[]{0f, 1f}, new Color[]{Color.WHITE, Color.BLACK});
		g2.setPaint(paint);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	}
}
