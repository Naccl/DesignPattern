package com.naccl.gobang.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.server.dao.VerifyPassword;
import com.naccl.gobang.util.MsgUtil;

import java.io.*;
import java.net.Socket;

/**
 * @Description: 服务端处理客户端的消息数据
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class HandleMsg implements Runnable {
	private Socket socket;
	private BufferedReader bfReader;
	public BufferedWriter bfWriter;

	public HandleMsg(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("连接到服务器");
		try {
			bfReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));//当前服务器会不断读取当前客户端的数据
			bfWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));//向客户端发送数据
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
					break;
				}
			}
			bfReader.close();
			bfWriter.close();
			bfWriter = null;
			bfReader = null;
			System.out.println("断开了一个客户端链接");
			RoomManager.getRoomManager().roomOwnerReady = false;//有人退出，直接取消两人的准备
			RoomManager.getRoomManager().playerReady = false;
			if (RoomManager.getRoomManager().roomOwner == this) {
				RoomManager.getRoomManager().roomOwner = null;
			} else if (RoomManager.getRoomManager().player == this) {
				RoomManager.getRoomManager().player = null;
			} else {
				RoomManager.getRoomManager().removeObserver(this);
			}
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			RoomManager.getRoomManager().roomOwnerReady = false;//有人退出，直接取消两人的准备
			RoomManager.getRoomManager().playerReady = false;
			if (RoomManager.getRoomManager().roomOwner == this) {
				RoomManager.getRoomManager().roomOwner = null;
			} else if (RoomManager.getRoomManager().player == this) {
				RoomManager.getRoomManager().player = null;
			} else {
				RoomManager.getRoomManager().removeObserver(this);
			}
			e.printStackTrace();
		}
	}

	private void handleMsg(String msgType, String msgJSON) {
		JSONObject msgData = JSON.parseObject(msgJSON);
		if ("placeChess".equals(msgType)) {//转发棋子信息给其他客户端
			sendMsgToPlayer("other", "placeChess", msgData);
		} else if ("win".equals(msgType)) {//发送玩家获胜信息给其他客户端
			sendMsgToPlayer("other", "win", msgData);
		} else if ("login".equals(msgType)) {//验证登录并返回信息
			String username = msgData.getString("username");
			String password = msgData.getString("password");
			boolean ok = VerifyPassword.verifyPassword(username, password);
			sendMsgToPlayer("this", "login", String.valueOf(ok));
		} else if ("createRoom".equals(msgType)) {//创建房间
			if (RoomManager.getRoomManager().roomOwner == null) {
				RoomManager.getRoomManager().setRoomOwner(this);
				sendMsgToPlayer("this", "createRoom", "true");//房主，黑棋
			} else {
				sendMsgToPlayer("this", "createRoom", "false");//已有房间，不能创建第二个
			}
		} else if ("enterRoom".equals(msgType)) {//进入房间
			if (RoomManager.getRoomManager().roomOwner == null) {
				sendMsgToPlayer("this", "enterRoom", "false");//没有房间，不能进入
			} else if (RoomManager.getRoomManager().player == null) {
				RoomManager.getRoomManager().setPlayer(this);
				sendMsgToPlayer("this", "enterRoom", "player");//对手，白棋
			} else {
				RoomManager.getRoomManager().addObserver(this);
				sendMsgToPlayer("this", "enterRoom", "observer");//旁观者
			}
		} else if ("exitRoom".equals(msgType)) {//退出房间
			RoomManager.getRoomManager().roomOwnerReady = false;//有人退出，直接取消两人的准备
			RoomManager.getRoomManager().playerReady = false;
			String role = msgData.getString("role");
			if ("房主".equals(role)) {//房主退出
				if (RoomManager.getRoomManager().player != null) {//有对手在房间内，转让房主
					RoomManager.getRoomManager().roomOwner = RoomManager.getRoomManager().player;
					RoomManager.getRoomManager().player = null;
					sendMsgToPlayer("roomOwner", "becomeRoomOwner", "becomeRoomOwner");//通知对手成为房主
				} else {
					RoomManager.getRoomManager().roomOwner = null;
					if (!RoomManager.getRoomManager().observers.isEmpty()) {//没有对手，但之前有观战者加入，清空
						sendMsgToPlayer("observers", "exitRoom", "exitRoom");
						RoomManager.getRoomManager().observers.clear();
					}
				}
			} else if ("对手".equals(role)) {
				RoomManager.getRoomManager().player = null;
			} else if ("观战".equals(role)) {
				RoomManager.getRoomManager().removeObserver(this);
			}
		} else if ("flee".equals(msgType)) {//游戏开始后逃跑，必然存在房主和对手
			RoomManager.getRoomManager().roomOwnerReady = false;//有人退出，直接取消两人的准备
			RoomManager.getRoomManager().playerReady = false;
			String role = msgData.getString("role");
			if ("房主".equals(role)) {//房主逃跑
				if (RoomManager.getRoomManager().player != null) {//必然有对手在房间内，转让房主
					RoomManager.getRoomManager().roomOwner = RoomManager.getRoomManager().player;
					RoomManager.getRoomManager().player = null;
					sendMsgToPlayer("roomOwner", "flee", role);//通知对手获胜
					sendMsgToPlayer("roomOwner", "becomeRoomOwner", "becomeRoomOwner");//通知对手成为房主
					sendMsgToPlayer("observers", "flee", role);//通知观战者，房主逃跑，对手获胜
				}
			} else if ("对手".equals(role)) {//对手逃跑
				RoomManager.getRoomManager().player = null;
				sendMsgToPlayer("roomOwner", "flee", role);//通知房主获胜
				sendMsgToPlayer("observers", "flee", role);//通知观战者，对手逃跑，房主获胜
			}
		} else if ("ready".equals(msgType)) {//玩家准备
			String role = msgData.getString("role");
			if ("房主".equals(role)) {
				RoomManager.getRoomManager().roomOwnerReady = true;
				if (RoomManager.getRoomManager().roomOwnerReady && RoomManager.getRoomManager().playerReady) {
					sendMsgToPlayer("all", "gameStart", "gameStart");
					RoomManager.getRoomManager().roomOwnerReady = false;
					RoomManager.getRoomManager().playerReady = false;
				} else {
					sendMsgToPlayer("player", "ready", "ready");
				}
			} else if ("对手".equals(role)) {
				RoomManager.getRoomManager().playerReady = true;
				if (RoomManager.getRoomManager().roomOwnerReady && RoomManager.getRoomManager().playerReady) {
					sendMsgToPlayer("all", "gameStart", "gameStart");
					RoomManager.getRoomManager().roomOwnerReady = false;
					RoomManager.getRoomManager().playerReady = false;
				} else {
					sendMsgToPlayer("roomOwner", "ready", "ready");
				}
			}
		} else if ("giveUp".equals(msgType)) {//玩家认输
			sendMsgToPlayer("other", "giveUp", msgData);
		} else if ("heQi".equals(msgType)) {
			String role = msgData.getString("role");
			if ("房主".equals(role)) {
				sendMsgToPlayer("player", "heQi", msgJSON);
			} else if ("对手".equals(role)) {
				sendMsgToPlayer("roomOwner", "heQi", msgJSON);
			}
		} else if ("disagreeHeQi".equals(msgType)) {
			String role = msgData.getString("role");
			if ("房主".equals(role)) {
				sendMsgToPlayer("roomOwner", "disagreeHeQi", "disagreeHeQi");
			} else if ("对手".equals(role)) {
				sendMsgToPlayer("player", "disagreeHeQi", "disagreeHeQi");
			}
		} else if ("agreeHeQi".equals(msgType)) {
			sendMsgToPlayer("other", "agreeHeQi", msgData);
		} else if ("goBack".equals(msgType)) {
			String role = msgData.getString("role");
			if ("房主".equals(role)) {
				sendMsgToPlayer("player", "goBack", msgJSON);
			} else if ("对手".equals(role)) {
				sendMsgToPlayer("roomOwner", "goBack", msgJSON);
			}
		} else if ("disagreeGoBack".equals(msgType)) {
			String role = msgData.getString("role");
			if ("房主".equals(role)) {
				sendMsgToPlayer("roomOwner", "disagreeGoBack", "disagreeGoBack");
			} else if ("对手".equals(role)) {
				sendMsgToPlayer("player", "disagreeGoBack", "disagreeGoBack");
			}
		} else if ("agreeGoBack".equals(msgType)) {
			sendMsgToPlayer("other", "agreeGoBack", msgData);
		}
	}

	private void sendMsgToPlayer(String user, String msgType, String msg) {
		JSONObject msgJSON = MsgUtil.getSendJSON(msgType, msg);
		System.out.println("发送：" + msgJSON.toJSONString());
		try {
			if ("this".equals(user)) {
				bfWriter.write(msgJSON.toJSONString() + "\r\n");
				bfWriter.flush();
			} else if ("roomOwner".equals(user)) {
				RoomManager.getRoomManager().roomOwner.bfWriter.write(msgJSON.toJSONString() + "\r\n");
				RoomManager.getRoomManager().roomOwner.bfWriter.flush();
			} else if ("player".equals(user)) {
				RoomManager.getRoomManager().player.bfWriter.write(msgJSON.toJSONString() + "\r\n");
				RoomManager.getRoomManager().player.bfWriter.flush();
			} else if ("all".equals(user)) {
				RoomManager.getRoomManager().notifyAllUser(msgJSON.toJSONString() + "\r\n");
			} else if ("observers".equals(user)) {
				for (HandleMsg h : RoomManager.getRoomManager().observers) {
					h.bfWriter.write(msgJSON.toJSONString() + "\r\n");
					h.bfWriter.flush();
				}
			}
		} catch (Exception e) {
			System.err.println("发送消息异常");
			e.printStackTrace();
		}
	}

	private void sendMsgToPlayer(String user, String msgType, JSONObject msgData) {//发送棋子信息
		try {
			if ("placeChess".equals(msgType)) {
				sendMsgToOtherPlayer(user, msgType, msgData);
			} else if ("win".equals(msgType)) {
				sendMsgToOtherPlayer(user, msgType, msgData);
			} else if ("giveUp".equals(msgType)) {
				sendMsgToOtherPlayer(user, msgType, msgData);
			} else if ("agreeHeQi".equals(msgType)) {
				sendMsgToOtherPlayer(user, msgType, msgData);
			} else if ("agreeGoBack".equals(msgType)) {
				sendMsgToOtherPlayer(user, msgType, msgData);
			}
		} catch (Exception e) {
			System.err.println("发送消息异常");
			e.printStackTrace();
		}
	}

	private void sendMsgToOtherPlayer(String user, String msgType, JSONObject msgData) throws IOException {
		if ("other".equals(user)) {
			String role = msgData.getString("role");
			JSONObject msgJSON = MsgUtil.getSendJSON(msgType, msgData.toJSONString());
			if ("房主".equals(role)) {
				RoomManager.getRoomManager().player.bfWriter.write(msgJSON.toJSONString() + "\r\n");
				RoomManager.getRoomManager().player.bfWriter.flush();
			} else if ("对手".equals(role)) {
				RoomManager.getRoomManager().roomOwner.bfWriter.write(msgJSON.toJSONString() + "\r\n");
				RoomManager.getRoomManager().roomOwner.bfWriter.flush();
			}
			for (HandleMsg h : RoomManager.getRoomManager().observers) {
				h.bfWriter.write(msgJSON.toJSONString() + "\r\n");
				h.bfWriter.flush();
			}
		}
	}
}
