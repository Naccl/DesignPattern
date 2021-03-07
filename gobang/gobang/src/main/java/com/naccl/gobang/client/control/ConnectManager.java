package com.naccl.gobang.client.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.client.view.ChessBoard;
import com.naccl.gobang.client.view.GameFrame;
import com.naccl.gobang.client.view.LoginDialog;
import com.naccl.gobang.client.view.MainFrame;
import com.naccl.gobang.util.MsgUtil;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description: 客户端处理服务端的消息数据
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class ConnectManager {
	private static final ConnectManager connectManager = new ConnectManager();
	private static MainFrame mainFrame;
	private static LoginDialog loginDialog;
	private static GameFrame gameFrame;
	private static ChessBoard chessBoard;
	private static final String ip = "127.0.0.1";
	private static Socket socket;
	public static BufferedReader bfReader;
	public static BufferedWriter bfWriter;

	private ConnectManager() {
	}

	public static ConnectManager getConnectManager() {
		return connectManager;
	}

	public static void setMainFrame(MainFrame mainFrame) {
		ConnectManager.mainFrame = mainFrame;
	}

	public static void setLoginDialog(LoginDialog loginDialog) {
		ConnectManager.loginDialog = loginDialog;
	}

	public static void setGameFrame(GameFrame gameFrame) {
		ConnectManager.gameFrame = gameFrame;
	}

	public static void setChessBoard(ChessBoard chessBoard) {
		ConnectManager.chessBoard = chessBoard;
	}

	public void connect() {
		new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket(ip, 9999);//创建客户端，连接的端口是ServerSocket的端口
					bfReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));//接收服务器发回的数据
					bfWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));//发送给服务器的数据
					while (true) {
						try {
							String resMsg = bfReader.readLine();
							JSONObject msgJSON;
							try {
								msgJSON = JSONObject.parseObject(resMsg);
								System.out.println("收到：" + msgJSON);
								if (msgJSON == null || msgJSON.equals("{}")) {
									break;
								}
							} catch (Exception e) {
								break;
							}
							//消息类型
							String msgType = msgJSON.getString("msgType");
							//消息数据
							String msg = msgJSON.getString("msg");

							handleMsg(msgType, msg);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					bfReader.close();
					bfWriter.close();
					bfWriter = null;
					bfReader = null;
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void handleMsg(String msgType, String msg) {
		if ("placeChess".equals(msgType)) {
			JSONObject msgData = JSON.parseObject(msg);
			String color = msgData.getString("color");
			String x = msgData.getString("x");
			String y = msgData.getString("y");
			chessBoard.receiveChess("black".equals(color) ? true : false, Integer.parseInt(x), Integer.parseInt(y));
		} else if ("win".equals(msgType)) {
			JSONObject msgData = JSON.parseObject(msg);
			String role = msgData.getString("role");
			chessBoard.win = true;
			chessBoard.gameStart = false;
			if (!"观战".equals(gameFrame.role)) {//把房主和对手的准备按钮恢复
				gameFrame.readyButton.setText("准备");
				gameFrame.readyButton.setEnabled(true);
			}
			if ("房主".equals(role)) {//房主为黑棋
				JOptionPane.showMessageDialog(gameFrame, "黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			} else if ("对手".equals(role)) {//对手为白棋
				JOptionPane.showMessageDialog(gameFrame, "白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if ("login".equals(msgType)) {
			if ("true".equals(msg)) {
				loginDialog.login(true);
			} else {
				loginDialog.login(false);
			}
		} else if ("createRoom".equals(msgType)) {
			if ("true".equals(msg)) {
				mainFrame.createRoom("房主");
			} else {
				JOptionPane.showMessageDialog(mainFrame, "已有一个房间存在，不可创建！");
			}
		} else if ("enterRoom".equals(msgType)) {
			if ("false".equals(msg)) {
				JOptionPane.showMessageDialog(mainFrame, "当前没有房间，请创建！");
			} else if ("player".equals(msg)) {
				mainFrame.enterRoom("对手");
			} else if ("observer".equals(msg)) {
				mainFrame.enterRoom("观战");
			}
		} else if ("becomeRoomOwner".equals(msgType)) {
			gameFrame.role = "房主";
			chessBoard.role = "房主";
			chessBoard.gameStart = false;
			chessBoard.isMe = false;
			gameFrame.setRoomTitle();
			gameFrame.readyButton.setText("准备");
			gameFrame.readyButton.setEnabled(true);
			JOptionPane.showMessageDialog(gameFrame, "房主退出了对局，您已成为房主！");
		} else if ("ready".equals(msgType)) {
			JOptionPane.showMessageDialog(gameFrame, "对手已准备！");
		} else if ("gameStart".equals(msgType)) {
			chessBoard.reStart();//再次开局时重置棋盘，还可以优化
			if ("房主".equals(gameFrame.role)) {
				chessBoard.isMe = true;
				JOptionPane.showMessageDialog(gameFrame, "对局开始，您为先手！");
			} else if ("对手".equals(gameFrame.role)) {
				chessBoard.isMe = false;
				JOptionPane.showMessageDialog(gameFrame, "对局开始，对方先手！");
			} else if ("观战".equals(gameFrame.role)) {
				JOptionPane.showMessageDialog(gameFrame, "对局开始！");
			}
		} else if ("exitRoom".equals(msgType)) {
			if ("观战".equals(gameFrame.role)) {
				gameFrame.dispose();
				mainFrame.setVisible(true);//gameFrame.owner.setVisible(true);
				mainFrame.game = null;
				JOptionPane.showMessageDialog(mainFrame, "玩家已退出，房间关闭！");
			}
		} else if ("flee".equals(msgType)) {//玩家逃跑
			//更新状态
			chessBoard.win = true;
			chessBoard.gameStart = false;
			if (!"观战".equals(gameFrame.role)) {//恢复准备按钮
				gameFrame.readyButton.setText("准备");
				gameFrame.readyButton.setEnabled(true);
			}
			if ("房主".equals(gameFrame.role)) {//房主收到对手逃跑的消息
				JOptionPane.showMessageDialog(gameFrame, "白棋方逃跑，黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			} else if ("对手".equals(gameFrame.role)) {//对手收到房主逃跑的消息
				JOptionPane.showMessageDialog(gameFrame, "黑棋方逃跑，白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			} else if ("观战".equals(gameFrame.role)) {//观战者收到有人逃跑的消息
				if ("房主".equals(msg)) {
					JOptionPane.showMessageDialog(gameFrame, "黑棋方逃跑，白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
				} else if ("对手".equals(msg)) {
					JOptionPane.showMessageDialog(gameFrame, "白棋方逃跑，黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if ("giveUp".equals(msgType)) {
			//更新状态
			chessBoard.win = true;
			chessBoard.gameStart = false;
			if (!"观战".equals(gameFrame.role)) {//恢复准备按钮
				gameFrame.readyButton.setText("准备");
				gameFrame.readyButton.setEnabled(true);
			}
			if ("房主".equals(gameFrame.role)) {
				JOptionPane.showMessageDialog(gameFrame, "白棋方认输，黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			} else if ("对手".equals(gameFrame.role)) {
				JOptionPane.showMessageDialog(gameFrame, "黑棋方认输，白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
			} else if ("观战".equals(gameFrame.role)) {
				JSONObject msgData = JSON.parseObject(msg);
				String role = msgData.getString("role");
				if ("房主".equals(role)) {
					JOptionPane.showMessageDialog(gameFrame, "黑棋方认输，白棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
				} else if ("对手".equals(role)) {
					JOptionPane.showMessageDialog(gameFrame, "白棋方认输，黑棋获胜！", "Game over !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if ("heQi".equals(msgType)) {
			if (JOptionPane.showConfirmDialog(gameFrame, "对方要求和棋，是否同意？", "和棋", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				//更新状态
				chessBoard.win = true;
				chessBoard.gameStart = false;
				gameFrame.readyButton.setText("准备");
				gameFrame.readyButton.setEnabled(true);
				JSONObject msg2 = new JSONObject();
				msg2.put("role", gameFrame.role);
				MsgUtil.sendMsgToServer(gameFrame, "agreeHeQi", msg2.toJSONString());
				JOptionPane.showMessageDialog(gameFrame, "双方和棋，平局！", "平局!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				MsgUtil.sendMsgToServer(gameFrame, "disagreeHeQi", msg);
			}
		} else if ("disagreeHeQi".equals(msgType)) {
			JOptionPane.showMessageDialog(gameFrame, "对方不同意和棋！", "和棋", JOptionPane.INFORMATION_MESSAGE);
		} else if ("agreeHeQi".equals(msgType)) {
			//更新状态
			chessBoard.win = true;
			chessBoard.gameStart = false;
			if (!"观战".equals(gameFrame.role)) {//恢复准备按钮
				gameFrame.readyButton.setText("准备");
				gameFrame.readyButton.setEnabled(true);
			}
			JOptionPane.showMessageDialog(gameFrame, "双方和棋，平局！", "平局!", JOptionPane.INFORMATION_MESSAGE);
		} else if ("goBack".equals(msgType)) {
			if (JOptionPane.showConfirmDialog(gameFrame, "对方要求悔棋，是否同意？", "悔棋", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				JSONObject msgData = JSON.parseObject(msg);
				String count = msgData.getString("count");
				for (int i = 0; i < Integer.parseInt(count); i++) {//如果在申请悔棋者的回合，要退回两个棋子
					chessBoard.executeGoBack();//执行悔棋操作
				}
				JSONObject msg2 = new JSONObject();
				msg2.put("role", gameFrame.role);
				msg2.put("count", count);
				MsgUtil.sendMsgToServer(gameFrame, "agreeGoBack", msg2.toJSONString());
			} else {
				MsgUtil.sendMsgToServer(gameFrame, "disagreeGoBack", msg);
			}
		} else if ("disagreeGoBack".equals(msgType)) {
			JOptionPane.showMessageDialog(gameFrame, "对方不同意悔棋！", "悔棋", JOptionPane.INFORMATION_MESSAGE);
		} else if ("agreeGoBack".equals(msgType)) {
			JSONObject msgData = JSON.parseObject(msg);
			String count = msgData.getString("count");
			for (int i = 0; i < Integer.parseInt(count); i++) {//如果在申请悔棋者的回合，要退回两个棋子
				chessBoard.executeGoBack();//执行悔棋操作
			}
		}
	}
}
