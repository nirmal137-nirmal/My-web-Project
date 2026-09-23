package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class JDBCDataSource {

	public static Connection getConnection() {

		ResourceBundle rb = ResourceBundle.getBundle("com.bundle.system");

		Connection con = null;

		try {
			Class.forName(rb.getString("driver"));
			con = DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void trnRollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
