package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class LoginDemo {
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter usrename");
			user=sc.nextLine(); //gives raja
			System.out.println("Enter password");
			pass=sc.nextLine();
			
		}
		//convert input values as required for the SQL query
		user="'"+user+"'";  //gives 'raja'
		pass="'"+pass+"'";  //gives 'rani'
		//register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		//create Statement object
		if(con!=null)
			st=con.createStatement();
		//prepare SQL query
		//select count(*) from userlist where username='raja' and pwd='rani'
		query="select count(*) from userlist where uname="+user+" and pwd="+pass;
			System.out.println(query);
			//execute the Query
			if(st!=null)
				rs=st.executeQuery(query);
		//process the ResultSet
			if(rs!=null) {
				if(rs.next())
					count=rs.getInt(1);
				System.out.println(count);
			}
			if(count==0)
				System.out.println("Invalid Credentials");
			else
				System.out.println("valid Credentials");
		}//try
	catch(SQLException se) {  //To handle known Exception
		se.printStackTrace();
		System.out.println("Record insertion failed");
	}
	catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
		System.out.println("Record insertion failed");
	}
	catch(Exception e) {   //To handle unknown exception
		System.out.println("Record Insertion failed");
		e.printStackTrace();
	}
	finally {
		//close JDBC objects
		try {
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se) {
		se.printStackTrace();
		}
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
		catch(Exception se) {
			se.printStackTrace();
		}
	}//finally
	}//main
}
