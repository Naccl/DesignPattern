package com.naccl.gobang.client.view;

import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.client.control.ConnectManager;
import com.naccl.gobang.client.model.chess.*;
import com.naccl.gobang.util.MsgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class ChessBoard extends JComponent implements MouseListener {
	public String role;
	public boolean gameStart = false;// 游戏是否开始
	public boolean isMe = false; // 是否为我的回合
	public boolean win = false; // 当前是否分出胜负

	private JFrame owner;
	private static final int MARGIN = 30;// 边距
	private static final int GRID_SPACING = 36;// 网格间距
	private static final int ROWS = 14;// 15行
	private static final int COLS = 14;// 15列

	private ChessFactory commonChessFactory = new CommonChessFactory();
	private ChessFactory superChessFactory = new SuperChessFactory();
	private Chess[] ChessArray = new Chess[(ROWS + 1) * (COLS + 1)];// 总225个棋子
	private int[][] matrixChessBoard = new int[ROWS + 1][COLS + 1];// 记录棋盘使用情况
	private int chessCount = 0;// 当前已下棋子个数
	private boolean blackNow = true;// 当前棋子黑白

	private int sameColorCount = 0;// 深搜记录连珠个数

	// 当前鼠标在棋盘上对应的格子，-1为不在棋盘上
	private int mouseX = -1;
	private int mouseY = -1;

	// 深搜判断胜负用到的八个搜索方向
	private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	private static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
	private static final int DEFAULT_DIRECTION = 8;

	public ChessBoard(JFrame owner, String role) {
		this.owner = owner;
		this.role = role;
		ConnectManager.getConnectManager().setChessBoard(this);
		// 监听鼠标的点击情况
		this.addMouseListener(this);
		// 监听鼠标在棋盘上的移动
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// 不是我的回合 或 观战模式 或 游戏尚未开始 或 胜负已分后 不再显示落点
				if (win || !gameStart || "观战".equals(role) || !isMe) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				// 获取当前鼠标在棋盘上对应的落点
				int currentMouseX = getX(e);
				int currentMouseY = getY(e);
				if (isClick(currentMouseX, currentMouseY)) {
					// 可以落子时设置鼠标样式
					setCursor(new Cursor(Cursor.HAND_CURSOR));
					// 记录鼠标坐标
					mouseX = currentMouseX;
					mouseY = currentMouseY;
				} else {
					// 不可以落子时恢复鼠标样式
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					mouseX = -1;
					mouseY = -1;
				}
				repaint();
			}
		});
	}

	private void drawBoard(Graphics2D g2) {
		// 设置棋盘背景颜色
		g2.setColor(new Color(255, 213, 119));
		g2.fillRect(0, 0, getWidth(), getHeight());

		// 画棋盘
		g2.setColor(Color.BLACK);
		for (int i = 0; i <= ROWS; i++) //画横线
			g2.drawLine(MARGIN, MARGIN + i * GRID_SPACING, MARGIN + COLS * GRID_SPACING, MARGIN + i * GRID_SPACING);
		for (int i = 0; i <= COLS; i++) //画竖线
			g2.drawLine(MARGIN + i * GRID_SPACING, MARGIN, MARGIN + i * GRID_SPACING, MARGIN + ROWS * GRID_SPACING);

		// 画五个点
		final int dotRadius = GRID_SPACING / 9;
		final int dotCenterX = toChess(COLS / 2), dotCenterY = toChess(ROWS / 2);
		final int dotTopLeftX = toChess(3), dotTopLeftY = toChess(3);
		final int dotTopRightX = toChess(COLS - 3), dotTopRightY = toChess(3);
		final int dotLowerLeftX = toChess(3), dotLowerLeftY = toChess(ROWS - 3);
		final int dotLowerRightX = toChess(COLS - 3), dotLowerRightY = toChess(ROWS - 3);

		g2.setColor(Color.BLACK);
		Ellipse2D dot = new Ellipse2D.Double();
		setDot(dot, dotCenterX, dotCenterY, dotRadius);
		g2.fill(dot);
		setDot(dot, dotTopLeftX, dotTopLeftY, dotRadius);
		g2.fill(dot);
		setDot(dot, dotTopRightX, dotTopRightY, dotRadius);
		g2.fill(dot);
		setDot(dot, dotLowerLeftX, dotLowerLeftY, dotRadius);
		g2.fill(dot);
		setDot(dot, dotLowerRightX, dotLowerRightY, dotRadius);
		g2.fill(dot);
	}

	// 画棋子
	private void drawChess(Graphics2D g2) {
		for (int i = 0; i < chessCount; i++) {
			int centerX = toChess(ChessArray[i].getX());
			int centerY = toChess(ChessArray[i].getY());
			boolean chessColor = ChessArray[i].isBlack();

			if (MainFrame.currentUser.isVip()) {//3D棋子
				SuperChess.draw(g2, chessColor, centerX, centerY, GRID_SPACING);
			} else {//2D棋子
				CommonChess.draw(g2, chessColor);
			}
			Ellipse2D circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(centerX, centerY, centerX + GRID_SPACING / 2, centerY + GRID_SPACING / 2);
			g2.fill(circle);

			//最后一个棋子用红框标出
			if (i == chessCount - 1) {
				g2.setColor(Color.red);
				g2.drawRect(centerX - GRID_SPACING / 2, centerY - GRID_SPACING / 2, GRID_SPACING, GRID_SPACING);
			}
		}
	}

	//画预落棋子红框
	private void PreloadingRedFrame(Graphics2D g2) {
		final int length = GRID_SPACING / 4;
		final int frameRadius = GRID_SPACING / 2;

		if (mouseX != -1 && mouseY != -1) {// 默认-1不显示
			int absMouseX = toChess(mouseX), absMouseY = toChess(mouseY);
			int frameTopLeftX = absMouseX - frameRadius, frameTopLeftY = absMouseY - frameRadius;
			int frameTopRightX = absMouseX + frameRadius, frameTopRightY = absMouseY - frameRadius;
			int frameLowerLeftX = absMouseX - frameRadius, frameLowerLeftY = absMouseY + frameRadius;
			int frameLowerRightX = absMouseX + frameRadius, frameLowerRightY = absMouseY + frameRadius;

			g2.setColor(Color.red);
			//左上角
			g2.drawLine(frameTopLeftX, frameTopLeftY, frameTopLeftX, frameTopLeftY + length);
			g2.drawLine(frameTopLeftX, frameTopLeftY, frameTopLeftX + length, frameTopLeftY);
			//右上角
			g2.drawLine(frameTopRightX, frameTopRightY, frameTopRightX, frameTopRightY + length);
			g2.drawLine(frameTopRightX, frameTopRightY, frameTopRightX - length, frameTopRightY);
			//左下角
			g2.drawLine(frameLowerLeftX, frameLowerLeftY, frameLowerLeftX, frameLowerLeftY - length);
			g2.drawLine(frameLowerLeftX, frameLowerLeftY, frameLowerLeftX + length, frameLowerLeftY);
			//右下角
			g2.drawLine(frameLowerRightX, frameLowerRightY, frameLowerRightX, frameLowerRightY - length);
			g2.drawLine(frameLowerRightX, frameLowerRightY, frameLowerRightX - length, frameLowerRightY);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//画棋盘
		drawBoard(g2);
		//画棋子
		drawChess(g2);
		//画预落棋子红框
		PreloadingRedFrame(g2);
	}


	private Chess createChess(boolean isCommonChess, int x, int y) {
		return isCommonChess ? commonChessFactory.create(x, y, blackNow) : superChessFactory.create(x, y, blackNow);
	}

	// 落子
	@Override
	public void mousePressed(MouseEvent e) {
		// 不是我的回合 或 游戏尚未开始 或 观战模式 或 胜负已分 不可落子
		if (win || !gameStart || "观战".equals(role) || !isMe) {
//			JOptionPane.showMessageDialog(this, "对局已经结束，请重新开始！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// 获取落下的棋子在棋盘上对应的坐标
		int chessX = getX(e);
		int chessY = getY(e);
		System.out.println(chessX + "," + chessY);

		if (!isClick(chessX, chessY)) return;// 判断是否可以落子

		//向服务器发送棋子消息
		JSONObject msg = new JSONObject();
		msg.put("role", role);
		msg.put("color", blackNow ? "black" : "white");
		msg.put("x", chessX);
		msg.put("y", chessY);
		MsgUtil.sendMsgToServer(owner, "placeChess", msg.toJSONString());

		// 可以落子时，记录棋子
		Chess ch = createChess(MainFrame.currentUser.isVip(), chessX, chessY);
		ChessArray[chessCount++] = ch;

		if (blackNow) matrixChessBoard[chessX][chessY] = 1;// 记录棋子，0为空，1为黑棋，2为白棋
		else matrixChessBoard[chessX][chessY] = 2;

		isMe = !isMe;

		repaint();

		isWin(chessX, chessY, DEFAULT_DIRECTION);
		if (win) {
			//向服务器发送获胜信息
			JSONObject msg2 = new JSONObject();
			msg2.put("role", role);
			MsgUtil.sendMsgToServer(owner, "win", msg2.toJSONString());

			//恢复准备按钮
			GameFrame gameFrame = (GameFrame) owner;
			gameFrame.readyButton.setText("准备");
			gameFrame.readyButton.setEnabled(true);

			gameStart = false;

			JOptionPane.showMessageDialog(this, blackNow ? "黑棋获胜！" : "白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(blackNow ? "black win!" : "white win!");
		}

		blackNow = !blackNow;// 设置下一个棋子颜色
	}

	public void receiveChess(boolean isBlack, int x, int y) {//接收服务端传来的棋子信息
		Chess ch = createChess(MainFrame.currentUser.isVip(), x, y);
		ChessArray[chessCount++] = ch;

		if (isBlack) matrixChessBoard[x][y] = 1;
		else matrixChessBoard[x][y] = 2;

		isMe = !isMe;

		repaint();

		blackNow = !blackNow;// 设置下一个棋子颜色
	}

	// 深搜判断是否五连珠
	private void isWin(int x, int y, int d) {
		// 起始四个方向
		if (d == DEFAULT_DIRECTION) {
			for (int i = 0; i < 4; i++) {
				sameColorCount = 1;
				int nx1 = x + dx[i], ny1 = y + dy[i];
				int nx2 = x + dx[i + 4], ny2 = y + dy[i + 4];
				if (nx1 >= 0 && ny1 >= 0 && nx1 <= ROWS && ny1 <= COLS) isWin(nx1, ny1, i);
				if (nx2 >= 0 && ny2 >= 0 && nx2 <= ROWS && ny2 <= COLS) isWin(nx2, ny2, i + 4);
			}
		} else if ((blackNow && matrixChessBoard[x][y] == 1) || (!blackNow && matrixChessBoard[x][y] == 2)) {
			// 当前方向下一个位置是否连珠
			sameColorCount++;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx >= 0 && ny >= 0 && nx <= ROWS && ny <= COLS) isWin(nx, ny, d);
		}
		if (sameColorCount >= 5) {
			win = true;
		}
		return;
	}

	private void setDot(Ellipse2D dot, int dotX, int dotY, int dotRadius) {
		dot.setFrameFromCenter(dotX, dotY, dotX + dotRadius, dotY + dotRadius);
	}

	// 将鼠标坐标转换成棋盘上对应的坐标
	private int getX(MouseEvent e) {
		return (e.getX() - MARGIN + GRID_SPACING / 2) / GRID_SPACING;
	}

	private int getY(MouseEvent e) {
		return (e.getY() - MARGIN + GRID_SPACING / 2) / GRID_SPACING;
	}

	// 获取在面板上对应的坐标
	private int toChess(int i) {
		return i * GRID_SPACING + MARGIN;
	}

	// x,y坐标是否存在棋子
	private boolean isExistChess(int x, int y) {
//		for (int i = 0; i < chessCount; i++) {
//			if (ChessArray[i].getX() == x && ChessArray[i].getY() == y) return true;
//		}
		if (matrixChessBoard[x][y] != 0) return true;
		return false;
	}

	// x,y坐标是否可点击
	private boolean isClick(int x, int y) {
		if (x >= 0 && y >= 0 && x <= ROWS && y <= COLS && !isExistChess(x, y)) return true;
		return false;
	}

	// 重新开始
	public void reStart() {
		for (int i = 0; i < chessCount; i++) ChessArray[i] = null;
		matrixChessBoard = new int[ROWS + 1][COLS + 1];
		chessCount = 0;

		gameStart = true;
		win = false;
		blackNow = true;
		repaint();
	}

	// 悔棋
	protected void goBack() {
		if (win || !gameStart || chessCount == 0) {// 如果游戏未开始 或 棋盘上未落子，不可悔棋
			return;
		} else if (JOptionPane.showConfirmDialog(this, "确认要悔棋吗？", "悔棋", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			JSONObject msg = new JSONObject();
			msg.put("role", role);
			msg.put("count", isMe && chessCount >= 2 ? 2 : 1);//如果在申请悔棋者的回合，要退回两个棋子
			MsgUtil.sendMsgToServer(owner, "goBack", msg.toJSONString());
			JOptionPane.showMessageDialog(this, "等待对方同意悔棋...", "悔棋", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	//执行悔棋操作
	public void executeGoBack() {
		isMe = !isMe;
		matrixChessBoard[ChessArray[chessCount - 1].getX()][ChessArray[chessCount - 1].getY()] = 0;
		ChessArray[chessCount--] = null;
		blackNow = !blackNow;
		repaint();
	}

	// 认输
	protected void giveUp() {
		// 游戏未开始，不可认输
		if (!gameStart && win) {
			JOptionPane.showMessageDialog(this, "游戏未开始！");
		} else if (JOptionPane.showConfirmDialog(this, "确认要认输吗？", "认输", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			JSONObject msg = new JSONObject();
			msg.put("role", role);
			MsgUtil.sendMsgToServer(owner, "giveUp", msg.toJSONString());
			win = true;
			gameStart = false;
			GameFrame gameFrame = (GameFrame) owner;
			gameFrame.readyButton.setText("准备");
			gameFrame.readyButton.setEnabled(true);
			JOptionPane.showMessageDialog(this, "房主".equals(role) ? "黑棋方认输，白棋获胜！" : "白棋方认输，黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void heQi() {
		// 游戏未开始，不可和棋
		if (!gameStart && win) {
			JOptionPane.showMessageDialog(this, "游戏未开始！");
		} else if (JOptionPane.showConfirmDialog(this, "确认要和棋吗？", "和棋", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			JSONObject msg = new JSONObject();
			msg.put("role", role);
			MsgUtil.sendMsgToServer(owner, "heQi", msg.toJSONString());
			JOptionPane.showMessageDialog(this, "等待对方同意和棋...", "和棋", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}