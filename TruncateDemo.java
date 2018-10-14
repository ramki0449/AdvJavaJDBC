package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TruncateDemo {

	public static void main(String[] args) {
		Scanner sc = null;
		String table;
		Connection con = null;
		Statement st = null;
		int result=0;
		String query = null;
		try {
			sc = new Scanner(System.in);
			//taking the table to be truncated
			System.out.println("Enter the table to be dropped:");
			table = sc.nextLine();
			//registering the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//creating the statement
			if(con!=null)
				st = con.createStatement();
			//executing query
			query = "truncate table "+table;
			if(st!=null) {
				result = st.executeUpdate(query);
				//result=1;
			}
			//System.out.println(result);
			if(result!=0)
				System.out.println("No table found to be deleted");
			else
				System.out.println(table+" dropped");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class