package com.naccl.gobang.client.view;

import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.client.control.ConnectManager;
import com.naccl.gobang.util.MsgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame implements ActionListener {
	public String role;

	private static final int DEFAULT_WIDTH = 580;
	private static final int DEFAULT_HEIGHT = 640;

	public JFrame owner;
	private ChessBoard chessBoard;

	private JPanel panel = new JPanel();
	public JButton readyButton = new JButton("准备");
	private JButton goBackButton = new JButton("悔棋");
	private JButton giveUpButton = new JButton("认输");
	private JButton heQiButtion = new JButton("和棋");


	private void addButton() {
		if ("观战".equals(role)) {
			readyButton.setEnabled(false);
			goBackButton.setEnabled(false);
			giveUpButton.setEnabled(false);
			heQiButtion.setEnabled(false);
		}
		panel.add(readyButton);
		panel.add(goBackButton);
		panel.add(giveUpButton);
		panel.add(heQiButtion);

		this.getContentPane().add(panel, BorderLayout.SOUTH);

		readyButton.addActionListener(this);
		goBackButton.addActionListener(this);
		giveUpButton.addActionListener(this);
		heQiButtion.addActionListener(this);
	}

	public void setRoomTitle() {
		this.setTitle("对局  用户名:" + MainFrame.currentUser.getUsername() + "  身份:" + role);
	}

	public GameFrame(JFrame owner, String role) {
		ConnectManager.getConnectManager().setGameFrame(this);
		this.owner = owner;
		this.role = role;

		chessBoard = new ChessBoard(this, role);
		this.add(chessBoard);
		addButton();

		this.addWindowListener(new MyListener());//要放在前面
		setRoomTitle();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏当前窗口，并释放窗体占有的部分资源
		this.setResizable(false);//不可调整大小
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == readyButton) {
			readyButton.setText("已准备");
			readyButton.setEnabled(false);
			JSONObject msg = new JSONObject();
			msg.put("role", role);
			try {
				MsgUtil.sendMsgToServer(this, "ready", msg.toJSONString());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(owner, "连接失败或服务器未启动！");
			}
		} else if (e.getSource() == goBackButton) {//悔棋
			chessBoard.goBack();
		} else if (e.getSource() == giveUpButton) {//认输
			chessBoard.giveUp();
		} else if (e.getSource() == heQiButtion) {//和棋
			chessBoard.heQi();
		}
	}

	class MyListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//点击取消按钮后，取消退出
			JSONObject msg = new JSONObject();
			msg.put("role", role);
			try {
				if ("观战".equals(role)) {//观战者退出房间
					MsgUtil.sendMsgToServer(owner, "exitRoom", msg.toJSONString());
					GameFrame.this.dispose();
					owner.setVisible(true);
					MainFrame.game = null;
				} else {//房主或对手退出
					//游戏开始后逃跑
					if (chessBoard.gameStart) {
						if (JOptionPane.showConfirmDialog(GameFrame.this, "确认要退出游戏？", "退出", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							MsgUtil.sendMsgToServer(owner, "flee", msg.toJSONString());
							GameFrame.this.dispose();
							owner.setVisible(true);
							MainFrame.game = null;
						}
					} else {
						MsgUtil.sendMsgToServer(owner, "exitRoom", msg.toJSONString());
						GameFrame.this.dispose();
						owner.setVisible(true);
						MainFrame.game = null;
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(owner, "连接失败或服务器未启动！");
			}
		}
	}
}