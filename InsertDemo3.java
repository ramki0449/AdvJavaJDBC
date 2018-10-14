package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class InsertDemo3 {

	public static void main(String[] args) {
		Scanner sc = null;
		int count = 0;
		Connection con = null;
		String query = null;
		PreparedStatement ps = null;
		int no = 0;
		String name = null;
		String addrs = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			System.out.println("Enter the student count");
			if(sc!=null)
				count = sc.nextInt();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","anil","anil");
			query = "insert into student values(?,?,?)";
			if(con!=null)
				ps = con.prepareStatement(query);
			if(sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter "+i+"student details");
					System.out.println("enter number");
					no = sc.nextInt();
					System.out.println("enter name");
					name = sc.next();
					System.out.println("enter address");
					addrs = sc.next();
					ps.setInt(1,no);
					ps.setString(2,name);
					ps.setString(3,addrs);
					result = ps.executeUpdate();
					if(result==0)
						System.out.println("student details are not inserted");
					else
						System.out.println("details are inserted");
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
	}//main
}//class
