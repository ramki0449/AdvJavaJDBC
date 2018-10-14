package com.rk.java;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SampleTest5 {

	public static void main(String[] args) {
		Connection con = null;
		Scanner sc = null;
		int number = 0;
		String query = null;
		Statement st = null;
		ResultSet rs = null;
		//boolean flag = false;
		try {
			sc = new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter employee number");
				number = sc.nextInt();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@loaclhost:1521:orcl","scott","tiger");			
			if(con!=null) {
				st = con.createStatement();
			}
			//select * from bonus where empno = number
			query ="select * from bonus where empno ="+number;
			System.out.println(query);
			if(st!=null)
				rs = st.executeQuery(query);
			if(rs!=null)
			{
				if(rs.next())
				{
					System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
					//flag=true;
				}
				else
					System.out.println("No records found");
			}
		}
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
