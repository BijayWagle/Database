package com.data.mysql;
	import java.sql.*;
	public class MyData {
	public static void main(String []args) throws ClassNotFoundException, SQLException {
		
		//telling jdbc to use mysql driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//connecting database to java
		Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","Beinghuman@5");
		
		//create alter drop are ddl (data definition language)
		//select update delete insert  dml(data manipulation language)
		
		//select statement
		Statement statement=connection.createStatement();
		
		//create a string with query in it
		String query="select* from products";
		
		//calling executequery from statement object and passing parameter as query(variable name)
		ResultSet result=statement.executeQuery(query);
		
		
		while(result.next()) {
			System.out.println(result.getString("productcode")+" "+result.getString("productName")+" "+result.getDouble("MSRP"));
		}
		
	}
	}



