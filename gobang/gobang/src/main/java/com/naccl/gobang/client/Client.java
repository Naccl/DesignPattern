package com.naccl.gobang.client;

import com.naccl.gobang.client.view.MainFrame;

import java.awt.*;

/**
 * @Description: 客户端
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class Client {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
					//线程被MainFrame占用，不能在这创建连接
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
