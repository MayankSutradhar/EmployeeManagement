package com.net.emp.model.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcDriverSetup {
	private static final String USER_NAME="root";
	private static final String PASSWORD="1234";
	
	public static Connection getJdbcConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management",USER_NAME,PASSWORD);
		return con;
	}
}
