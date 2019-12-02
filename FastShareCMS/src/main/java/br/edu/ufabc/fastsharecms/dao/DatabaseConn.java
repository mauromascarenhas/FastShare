package br.edu.ufabc.fastsharecms.dao;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mauro
 */
public class DatabaseConn {
    
    private Integer port;
    private String hostname;
    private String database;
    private String username;
    private String password;
    private Connection m_connection;
    
    private static DatabaseConn db = null;
    
    private DatabaseConn(){
        hostname = "localhost";
        port = 3306;
        database = "fastshare";
        username = "root";
        password = "test";
        
        try {
            DriverManager.registerDriver(new Driver());
            String url = String.format("jdbc:mysql://%s:%d:%s", hostname, port, database);
            m_connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB Connection stablished successfully");
        } catch (SQLException e) {
            System.out.println("Could not connect");
            System.out.println("Message : " + e.getMessage());
        }
    }
    
    public static DatabaseConn getInstance(){
        if (db == null) db = new DatabaseConn();
        return db;
    }
    
    public Connection connection(){
        return this.m_connection;
    }
}
