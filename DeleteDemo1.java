package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteDemo1 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		String query = null;
		int result = 0;
		String location = null;//,product2;
		try {
			sc = new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter the loaction of the student to be deleted");
				location = sc.nextLine();
				//System.out.println("Enter the product2 to be sorted");
				//product2 = sc.nextLine();
			}//if
			location = "'"+location+"'";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
				st = con.createStatement();
			//delete from student where loc = loaction
			query ="delete from student where loc ="+location;
			System.out.println(query);
			if(st!=null)
				result = st.executeUpdate(query);
			if(result==0)
				System.out.println("No records found for deletion");
			else
				System.out.println(result+" records found for deletion");
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
		}//final
	}//main
}//class