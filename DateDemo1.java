package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DateDemo1 {
public static final String DATE_VALUE = "select dob from person_dates where pid = ?";
	public static void main(String[] args) {
		Scanner sc = null;
		int id = 0;
		boolean flag = false;
		//int result = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		//float age=0;
		java.sql.Date sqdob=null;
		java.util.Date udob=null;
		long  dobMs=0,sysDateMs=0;
		
		try {
			sc = new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter the person id");
				id = sc.nextInt();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
				ps = con.prepareStatement(DATE_VALUE);
			if(ps!=null)
				ps.setInt(1,id);
			if(ps!=null)
				rs = ps.executeQuery();
			if(rs!=null)
			{
				if(rs.next()) {
					sqdob=rs.getDate(1);
					udob=(java.util.Date)sqdob;
					dobMs=udob.getTime();
					sysDateMs=new java.util.Date().getTime();
					System.out.println("Person age (in Ms)"+(sysDateMs-dobMs));
					System.out.println("Person age age in Secs"+(sysDateMs-dobMs)/1000.0f);
					System.out.println("Person age  in Min"+ (sysDateMs-dobMs)/(1000.0f*60));
					System.out.println("Person age  in Hours"+ (sysDateMs-dobMs)/(1000.0f*60*60));
					System.out.println("Person age  in days"+ (sysDateMs-dobMs)/(1000.0f*60*60*24));
					System.out.println("Person age  in months"+ (sysDateMs-dobMs)/(1000.0f*60*60*24*30));
					System.out.println("Person age  in years"+ (sysDateMs-dobMs)/(1000.0f*60*60*24*365));
				}
				if(flag!=false)
					System.out.println("No records found");
			}//end of if
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
			//close jdbc objs
			try {
				if(ps!=null)
					ps.close();
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
/*SQL> create table person_dates(pid number(5) primary key,pname varchar2(20),DOB date,DOJ date,DOM date);*/
	}//main
}//class