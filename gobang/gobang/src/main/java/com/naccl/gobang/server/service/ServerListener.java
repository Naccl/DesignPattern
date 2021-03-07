package com.naccl.gobang.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 服务端监听端口的连接请求，并为之创建新线程
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class ServerListener implements Runnable {
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9999);//服务端监听9999端口
			while (true) {//保存多个客户端的socket
				socket = serverSocket.accept();//阻塞方法
				System.out.println("一个客户端已连接");
				HandleMsg handleMsg = new HandleMsg(socket);
				new Thread(handleMsg).start();//创建新线程接收当前连接的socket传来的消息
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
