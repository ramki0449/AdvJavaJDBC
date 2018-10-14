package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertDemo1 {
	//public static final QUERY = "insert into table1 values(?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int number = 0;
		String query = null;
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		int count = 0;
		try {
			sc = new Scanner(System.in);
			query = "insert into table1 values(?)";
			System.out.println("Enter no.of values to be inserted");
			count = sc.nextInt();
			//registering driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","anil","anil");
			//creating the statement
			if(con!=null)
				ps = con.prepareStatement(query);
			if(sc!=null) {
				for(int i=1;i<=count;i++) {
					//System.out.println("The table name is table1 having only one variable its is name");
					System.out.println("enter number");
					number = sc.nextInt();
					ps.setInt(1,number);
					result = ps.executeUpdate();
					if(result!=0)
						System.out.println("data inserted");
					else
						System.out.println("data not inserted");
				}//for
			}//if
		}//try
		catch(SQLException se){//known sql exception
			se.printStackTrace();
		}//catch end
		catch(ClassNotFoundException cnf){//known classnotfoundexception
			cnf.printStackTrace();
		}//catch
		catch(Exception e){//unknown exception
			e.printStackTrace();
		}//catch
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//finally	
	}
}
