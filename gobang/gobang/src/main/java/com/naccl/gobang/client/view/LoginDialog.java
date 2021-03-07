package com.naccl.gobang.client.view;

import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.client.control.ConnectManager;
import com.naccl.gobang.client.model.user.User;
import com.naccl.gobang.util.MsgUtil;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class LoginDialog extends JDialog implements ActionListener {
	private JFrame owner;
	private static final int DEFAULT_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 150;
	JTextField jTextFieldUserName = new JTextField(10);//用户名
	JPasswordField jPasswordFieldPassword = new JPasswordField(10);//密码
	JButton jButtonOK = new JButton("登录");
	JButton jButtonCancel = new JButton("退出");

	private void addJPanel() {
		JPanel p1 = new JPanel();
		p1.add(new JLabel("用户名："));
		p1.add(jTextFieldUserName);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("密    码："));
		p2.add(jPasswordFieldPassword);
		JPanel p3 = new JPanel();
		p3.add(jButtonOK);
		p3.add(jButtonCancel);

		this.getContentPane().add(p1, BorderLayout.NORTH);
		this.getContentPane().add(p2, BorderLayout.CENTER);
		this.getContentPane().add(p3, BorderLayout.SOUTH);

		jButtonOK.addActionListener(this);
		jButtonCancel.addActionListener(this);
	}

	public LoginDialog(JFrame owner) {
		ConnectManager.getConnectManager().setLoginDialog(this);
		this.owner = owner;
		setAlwaysOnTop(true);
		this.setTitle("登录");
		addJPanel();

		this.addWindowListener(new MyListener());//要放在前面
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);//窗口置于屏幕中央
		this.setResizable(false);//不可调整大小
		this.setModal(true);//不可点击其他界面
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButtonOK) {
			JSONObject msg = new JSONObject();
			msg.put("username", jTextFieldUserName.getText());
			msg.put("password", String.valueOf(jPasswordFieldPassword.getPassword()));
			try {
				MsgUtil.sendMsgToServer(owner, "login", msg.toJSONString());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "连接失败或服务器未启动！");
				System.exit(0);
			}
		} else if (e.getSource() == jButtonCancel) {
			System.exit(0);
		}
	}

	public void login(boolean ok) {
		if (ok) {
			JOptionPane.showMessageDialog(this, "登陆成功！");
			MainFrame.currentUser = User.getUser(jTextFieldUserName.getText());
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "用户名或密码不正确！");
			jTextFieldUserName.setText("");
			jPasswordFieldPassword.setText("");
		}
	}

	class MyListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}