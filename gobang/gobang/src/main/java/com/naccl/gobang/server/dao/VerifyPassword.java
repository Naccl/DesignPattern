package com.naccl.gobang.server.dao;

import static com.naccl.gobang.server.dao.config.DataBaseConfig.*;

import java.sql.*;

/**
 * @Description: 验证登录密码
 * @Author: Naccl
 * @Date: 2020-04-10
 */
public class VerifyPassword {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;

	public static boolean verifyPassword(String username, String password) {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "SELECT id, username, password FROM user WHERE username=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
