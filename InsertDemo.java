package com.rk.java;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertDemo {

	public static void main(String[] args) {
		Scanner sc = null;
		String name = null;
		String query = null;
		Connection con = null;
		Statement st = null;
		int result = 0;
		
		try {
			sc = new Scanner(System.in);
			System.out.println("The table name is table1 havinfg only one variable its is name");
			name = sc.nextLine();
			name = "'"+name+"'";
			//registering driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","anil","anil");
			//creating the statement
			if(con!=null)
				st = con.createStatement();
			//creating the query
			query = "insert into table1 values ("+name+")";
			System.out.println(query);
			if(st!=null)
				result = st.executeUpdate(query);
			if(result!=0)
				System.out.println(result+" records inserted");
			else
				System.out.println("no records inserted");
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
					if(st!=null)
						st.close();
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
	}//main
}//class
