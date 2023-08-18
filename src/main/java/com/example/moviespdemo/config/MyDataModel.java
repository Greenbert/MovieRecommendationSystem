package com.example.moviespdemo.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;


public class MyDataModel {

	public static JDBCDataModel myDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setDatabaseName("movie");


            ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
            // use JNDI
            dataModel = new MySQLJDBCDataModel(connectionPool,"movie_preferences", "userID", "movieID","preference","timestamp");


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    } 

}
