package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Multiply {

	public static void main(String[] args) {
		Scanner sc = null;
		String desg = null;
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		int count = 0;
		int percent = 0;
		try {
			sc = new Scanner(System.in);
			//taking the emp desg from user
			System.out.println("Enter % of sal to be hiked");
			percent = sc.nextInt();
			percent = percent*(percent/100);
			System.out.println("Enter the designation of the employee");
			desg = sc.next();
			desg = desg.toUpperCase();
			desg = "'"+desg+"'";
			//System.out.println(desg);
			//registering jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//estbalishing the connecton
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//creating statement object
			if(con!=null)
				st = con.createStatement();
			//creating sql query
			query = "select empno,ename,job,sal+"+percent+" from employee1 where job ="+desg;
			//creating resultset varibale
			if(st!=null)
				rs = st.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next()!=false)
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
					flag=true;
					count++;
				}//while
				if(flag==true)
					System.out.println(count+" Records found");
				else
					System.out.println("No records found");
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
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
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
			
		
	}

}
