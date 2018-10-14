package com.rk.java;

//SampleTest5.java

//package com.rk.java.jdbc;
/*Application to get the details based on the given employee number
Version: 1.0
Author: Team-Rk
*/
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class SampleTest4
{
	public static void main(String args[])
	{
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		int enumber = 0;
		ResultSet rs = null;
		boolean flag = false;
		try{
			//reading inputs
			sc = new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter the employee to be reterived");
				//reading the required employee number
				enumber = sc.nextInt();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing connecion
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
				//creating statement object
				st = con.createStatement();
			//Sql query as we write in sql plus
			//select ENAME,EMPNO,SAL from emp where empno=enumber
			String query = "select ENAME,EMPNO,SAL from emp where empno="+enumber;
			System.out.println(query);
			//execute sql query
			if(st!=null)
				rs = st.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
					flag=true;
				}//end of while
				if(flag==false)
					System.out.println("No records found");
			}//end of if
		}//end of try
		catch(SQLException se){//known sql exception
			se.printStackTrace();
		}//catch end
		catch(ClassNotFoundException cnf){//known classnotfoundexception
			cnf.printStackTrace();
		}
		catch(Exception e){//unknown exception
			e.printStackTrace();
		}
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
	}//main
}//class