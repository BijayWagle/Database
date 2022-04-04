package com.data.mysql.test;
import java.sql.SQLException;

import com.data.mysql.Data;
public class DataMain {
public static void main(String[]ars) throws ClassNotFoundException, SQLException {
	Data data=new Data();
	data.readDataBase();
}
	
}