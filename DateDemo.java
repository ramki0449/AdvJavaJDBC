package com.rk.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateDemo {

	  private static final String INSERT_DATE_VALUES="INSERT INTO PERSON_DATES VALUES(PID_SQE.NEXTVAL,?,?,?,?)";
		
	public static void main(String[] args) {

				Scanner sc=null;
				//int pid=0;
				String pname=null;
				String sdob=null,sdoj=null,sdom=null;
				Connection con=null;
				PreparedStatement ps=null;
				SimpleDateFormat sdf1=null,sdf3=null;
				java.util.Date  udob=null,udom=null;
				java.sql.Date sqdob=null,sqdom=null,sqdoj=null;
				int result=0;
				try {
					//read inputs
					sc=new Scanner(System.in);
					if(sc!=null) {
						/*System.out.println("Enter Person Id");
						pid=sc.nextInt();*/
						System.out.println("Enter Person name::");
						pname=sc.next();
						System.out.println("Enter DOB::(dd-MM-yyyy)");
						sdob=sc.next();
						System.out.println("Enter DOJ::(yyyy-MM-dd)");
						sdoj=sc.next();
						System.out.println("Enter DOM::(MM-dd-yyyy)");
						sdom=sc.next();
					}
					//register JDBC driver s/w
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Establish the connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott","tiger");
					
				/*	//register JDBC driver s/w
					Class.forName("com.mysql.jdbc.Driver");
					//Establish the connection
					con=DriverManager.getConnection("jdbc:mysql:///ntaj410db1", "root","root");  */
					
					//create JDBC STatement object
					if(con!=null)
						ps=con.prepareStatement(INSERT_DATE_VALUES);
					//Convert String date values to java.sql.Date class objects
					    //for DOB
					      sdf1=new SimpleDateFormat("dd-MM-yyyy");
					      System.out.println(sdf1);
					      udob=sdf1.parse(sdob); 
					      System.out.println(sdob);//String date value to java.util.Date obj
					      sqdob=new java.sql.Date(udob.getTime());
					      System.out.println(sqdob);//java.util.Date class obj to java.sql.Date class obj
					   //for DOJ
					      sqdoj=java.sql.Date.valueOf(sdoj); //String date vlaue to java.sql.Date class obj
					    //for DOM
					      sdf3=new SimpleDateFormat("MM-dd-yyyy");
					      udom=sdf3.parse(sdom);
					      sqdom=new java.sql.Date(udom.getTime());
					    // set values to Query params
					      if(ps!=null) {
					    	  //ps.setInt(1, pid);
					    	  ps.setString(1,pname);
					    	  ps.setDate(2, sqdob);
					    	  ps.setDate(3,sqdoj);
					    	  ps.setDate(4, sqdom);
					      }
					      //execute the Query
					      if(ps!=null) {
					    	  result=ps.executeUpdate();
					      }
					      //process the Reuslt
					      if(result==0)
					    	  System.out.println("Record not inserted");
					      else
					    	  System.out.println("Record inserted");
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
	}
}