package com.naccl.gobang.server.service;

import java.util.Vector;

/**
 * @Description: 用单例模式创建RoomManager，管理一个对局房间内的所有的连接
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class RoomManager {
	private static volatile RoomManager roomManager = null;
	public static volatile HandleMsg roomOwner = null;//房主
	public static volatile HandleMsg player = null;//对手
	public static Vector<HandleMsg> observers = null;
	public static boolean roomOwnerReady = false;
	public static boolean playerReady = false;

	private RoomManager() {
		this.observers = new Vector<>();
	}

	public static RoomManager getRoomManager() {
		if (roomManager == null) {
			synchronized (RoomManager.class) {
				if (roomManager == null) {
					roomManager = new RoomManager();
				}
			}
		}
		return roomManager;
	}

	public static void setRoomOwner(HandleMsg ro) {
		if (roomOwner == null) {
			synchronized (RoomManager.class) {
				if (roomOwner == null) {
					roomOwner = ro;
				}
			}
		}
	}

	public static void setPlayer(HandleMsg pl) {
		if (player == null) {
			synchronized (RoomManager.class) {
				if (player == null) {
					player = pl;
				}
			}
		}
	}

	public void addObserver(HandleMsg handleMsg) {
		observers.add(handleMsg);
	}

	public void removeObserver(HandleMsg handleMsg) {
		observers.remove(handleMsg);
	}

	public void notifyAllUser(String msg) {
		try {
			roomOwner.bfWriter.write(msg);
			roomOwner.bfWriter.flush();
			player.bfWriter.write(msg);
			player.bfWriter.flush();
			for (HandleMsg h : observers) {
				h.bfWriter.write(msg);
				h.bfWriter.flush();
			}
		} catch (Exception e) {
			System.err.println("发送消息异常");
			e.printStackTrace();
		}
	}
}
