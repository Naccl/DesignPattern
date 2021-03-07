package com.naccl.gobang.server;

import com.naccl.gobang.server.service.ServerListener;

/**
 * @Description: 启动端口监听线程
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class Server {
	public static void main(String[] args) {
		ServerListener serverListener = new ServerListener();
		new Thread(serverListener).start();
	}
}
