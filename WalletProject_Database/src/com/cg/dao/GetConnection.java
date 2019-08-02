package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetConnection {
	public static Connection getConnection() throws SQLException {

		Connection con=null;
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
	try {
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());//Java8 automatically load the driver so no need for this statement
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String pass="hr";
		con=DriverManager.getConnection(url,user,pass);
		System.out.println("Connected");
		con.setAutoCommit(false);
		return con;
		} catch (SQLException e) {
		con.rollback();
		System.out.println(e.getMessage()+""+e.getErrorCode()+""+e.getSQLState());
		e.printStackTrace();
	}
	return con;}

}
