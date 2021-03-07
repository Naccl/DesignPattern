package com.naccl.gobang.util;

import com.alibaba.fastjson.JSONObject;
import com.naccl.gobang.client.control.ConnectManager;

import javax.swing.*;
import java.io.IOException;

/**
 * @Description: 封装JSON的工具类
 * @Author: Naccl
 * @Date: 2020-04-09
 */
public class MsgUtil {
	public static JSONObject getSendJSON(String msgType, String msg) {
		JSONObject JSON = new JSONObject();
		JSON.put("msgType", msgType);
		// 如果消息是json字符，那么应该先包装成json对象 防止发送的时候把引号转义
		if (JSONObject.isValidObject(msg)) {
			JSON.put("msg", JSONObject.parse(msg));
		} else {
			JSON.put("msg", msg);
		}
		return JSON;
	}

	public static void sendMsgToServer(JFrame jFrame, String msgType, String msg) {
		try {
			//包装要发送的消息
			JSONObject msgJSON = getSendJSON(msgType, msg);
			System.out.println("发送：" + msgJSON.toJSONString());
			//发送消息
			ConnectManager.bfWriter.write(msgJSON + "\r\n");
			ConnectManager.bfWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jFrame, "您可能已经与服务器断开了连接");
		}
	}
}
