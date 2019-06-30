package com.alxmcr.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

public class App {

	private static final String CONN_STRING = "jdbc:mysql://192.168.1.5:3306/hibernate001";

	public static void main(String[] args) {
		System.out.println(new Date());
		connectToMYSQL();
	}

	private static void connectToMYSQL() {

		Connection conn = null;

		try {
			System.out.println("CONN_STRING: " + CONN_STRING);

			Properties info = new Properties();
			info.put("user", "root");
			info.put("password", "root");
			
			System.out.println(info);

			conn = DriverManager.getConnection(CONN_STRING, info);

			// Do something with the Connection

			Statement stmt = null;
			ResultSet rs = null;

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM test");

				// or alternatively, if you don't know ahead of time that
				// the query will be a SELECT...

				if (stmt.execute("SELECT * FROM test")) {
					rs = stmt.getResultSet();
				}

				// Now do something with the ResultSet ....
				while(rs.next())  {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			} finally {
				// it is a good idea to release
				// resources in a finally{} block
				// in reverse-order of their creation
				// if they are no-longer needed

				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					} // ignore

					rs = null;
				}

				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					} // ignore

					stmt = null;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		System.out.println("--- END ---");
	}

}
