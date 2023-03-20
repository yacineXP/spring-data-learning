package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false";
		String user = "sammy";
		String pass = "password";

		try {
			System.out.println("Connecting to DB.... " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connected successuly !");

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
