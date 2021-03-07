package com.naccl.gobang.client.view;

import com.alibaba.fastjson.JSONArray;
import com.naccl.gobang.client.control.ConnectManager;
import com.naccl.gobang.client.model.user.User;
import com.naccl.gobang.util.MsgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
	public static User currentUser = null;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;

	private JMenuBar menuBar = new JMenuBar();

	//用户菜单
	private JMenu userMenu = new JMenu("用户");
//	private JMenuItem changePasswordMenuItem = new JMenuItem("修改密码");
	private JMenuItem exitMenuItem = new JMenuItem("退出游戏");

	//游戏菜单
	private JMenu gameMenu = new JMenu("游戏");
	private JMenuItem createRoomItem = new JMenuItem("创建房间");
	private JMenuItem enterRoomItem = new JMenuItem("进入房间");

	private JPanel panel = new JPanel();
	private JButton createRoomButton = new JButton("创建房间");
	private JButton enterRoomButton = new JButton("进入房间");
	private JButton VIPButton = new JButton("3D棋子");

	//欢迎界面
	private JLabel welcomeLabel = new JLabel("五子棋");

	public static GameFrame game = null;

	private void addMenu() {
//		userMenu.add(changePasswordMenuItem);
		userMenu.add(exitMenuItem);
		menuBar.add(userMenu);

		gameMenu.add(createRoomItem);
		gameMenu.add(enterRoomItem);
		menuBar.add(gameMenu);

		this.setJMenuBar(menuBar);

		exitMenuItem.addActionListener(this);
		createRoomItem.addActionListener(this);
	}

	private void addButton() {
		panel.add(createRoomButton);
		panel.add(enterRoomButton);
		panel.add(VIPButton);

		this.getContentPane().add(panel, BorderLayout.SOUTH);

		createRoomButton.addActionListener(this);
		enterRoomButton.addActionListener(this);
		VIPButton.addActionListener(this);
	}

	private void addWelcome() {
		welcomeLabel.setFont(new Font(null, 1, 48));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(welcomeLabel);
	}

	public MainFrame() throws HeadlessException {
		//连接服务器
		ConnectManager.getConnectManager().connect();
		ConnectManager.getConnectManager().setMainFrame(this);

		new LoginDialog(this);

		addMenu();
		addButton();
		addWelcome();

		this.setTitle("五子棋  用户名:" + currentUser.getUsername());
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);//窗口置于屏幕中央
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitMenuItem) {
			if (JOptionPane.showConfirmDialog(this, "确认要退出游戏？", "退出", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		} else if (e.getSource() == createRoomButton || e.getSource() == createRoomItem) {
			Object object = JSONArray.toJSON(currentUser);
			String msg = object.toString();
			try {
				MsgUtil.sendMsgToServer(this, "createRoom", msg);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "连接失败或服务器未启动！");
			}
		} else if (e.getSource() == enterRoomButton || e.getSource() == enterRoomItem) {
			Object object = JSONArray.toJSON(currentUser);
			String msg = object.toString();
			try {
				MsgUtil.sendMsgToServer(this, "enterRoom", msg);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "连接失败或服务器未启动！");
			}
		} else if (e.getSource() == VIPButton) {
			currentUser.setVip(!currentUser.isVip());
			if (currentUser.isVip()) {
				VIPButton.setText("2D棋子");
			} else {
				VIPButton.setText("3D棋子");
			}
		}
	}

	public void createRoom(String role) {
		if (game == null) game = new GameFrame(this, role);
		this.setVisible(false);
		game.setVisible(true);
	}

	public void enterRoom(String role) {
		if (game == null) game = new GameFrame(this, role);
		this.setVisible(false);
		game.setVisible(true);
	}
}