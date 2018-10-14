package com.rk.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo {

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		Connection con = null;
		CallableStatement cs = null;
		String query = null;
		int result = 0;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Number");
				no = sc.nextInt();
			}
			// sql query preparation
			query = "{call p_first_pro1(?,?)}";
			// jdbc driver registeration
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection establishment
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			// creating callable statement
			if (con != null)
				cs = con.prepareCall(query);
			if (cs != null) {
				// registering out params with jdbc
				cs.registerOutParameter(2, Types.INTEGER);
				// setting values in in params
				cs.setInt(1, no);
				// pl/sql queryexecution
				cs.execute();
				// gathering result from out params
				result = cs.getInt(2);
				System.out.println("Square value " + result);
			} // if
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
				if(cs!=null)
					cs.close();
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
