package com.naccl.gobang.server.dao.config;

/**
 * @Description: 数据库配置信息
 * @Author: Naccl
 * @Date: 2020-04-10
 */
public class DataBaseConfig {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/gobang?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
	public static final String USER = "root";
	public static final String PASS = "root";
}
