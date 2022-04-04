package com.data.mysql;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class Data {
	private Connection connect=null;
	private Statement statement=null;
	private PreparedStatement preparedStatement=null;
	private ResultSet resultSet=null;

	public void readDataBase() throws ClassNotFoundException, SQLException{
		//this will load mysql Driver each db has its own driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//setup the connection with the db
		connect =DriverManager.getConnection("jdbc:mysql://localhost:3306/javapractice","root","Beinghuman@5");
		
		//statement allow to issue Sql queries to database
		statement=connect.createStatement();
		
		//result set get the result of the sql query
		resultSet=statement.executeQuery("Select*from department");
		writeResultSet(resultSet);
		
		//PreparedStatement can use variables and are more efficient
		preparedStatement=connect.prepareStatement("insert into department values(?,?,?)");
		
		//parameter start with one
		preparedStatement.setInt(1, 6);
		preparedStatement.setString(2, "zan");
		preparedStatement.setString(3, "DataAnalyst");
		preparedStatement.executeUpdate();
		
		preparedStatement=connect.prepareStatement("select * from department");
		resultSet=preparedStatement.executeQuery();
		writeResultSet(resultSet);
		
		//remove again the insert comment
		preparedStatement=connect.prepareStatement("delete from department where departmentid=?;");
		preparedStatement.setInt(1, 1);
		preparedStatement.executeUpdate();
		
		resultSet=statement.executeQuery("select* from department");
		writeMetaData(resultSet);}
		
		private void writeMetaData(ResultSet resultSet)throws SQLException{
			//now get some metadata from the database
			//result set get the result of the SQL query
			
			System.out.println("The columns in the table are");
			 System.out.println("Table:"+resultSet.getMetaData().getTableName(1));
			 
			 for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++) {
				 System.out.println("Column"+i+""+
				 resultSet.getMetaData().getColumnName(i));
			 }
		}
		private void writeResultSet(ResultSet resultSet) throws SQLException{
			//resultSEt is initially before the first data set
			while (resultSet.next()) {
				//it is possible to get the column via name
				//also possible to get the column by column number
				//which start at 1
				//eg. resultSet.getString(2);
				
				int id =resultSet.getInt("departmentid");
				String user=resultSet.getString("name");
				String role=resultSet.getString("title");
				
				System.out.println("id:"+id);
				System.out.println("user:"+user);
				System.out.println("role:"+role);
			}
		}
		private void close() {
			try {
				if (resultSet!= null) {
					resultSet.close();
					}
				if (statement!= null) {
					statement.close();
				}
				if(connect != null) {
					connect.close();
				}
			}
				catch (Exception e) {
					
				}
		}
	}



