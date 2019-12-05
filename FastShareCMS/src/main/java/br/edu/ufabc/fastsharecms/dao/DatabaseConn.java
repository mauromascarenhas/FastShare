package br.edu.ufabc.fastsharecms.dao;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
    
    // TODO : ADD constraints
    private static final String[] CREATE_STATEMENT = {
        "CREATE TABLE IF NOT EXISTS ent_User (id INTEGER(64) NOT NULL,"
                                            + " username VARCHAR(128) NOT NULL,"
                                            + " name VARCHAR(256) NOT NULL,"
                                            + " email VARCHAR(256) NOT NULL,"
                                            + " role VARCHAR(64) NOT NULL,"
                                            + " psalt CHAR(20) NOT NULL,"
                                            + " phash VARCHAR(255) NOT NULL,"
                                            + " approved TINYINT NOT NULL);",
        "CREATE TABLE IF NOT EXISTS ent_Post (id INTEGER(64) NOT NULL,"
                                            + " date BIGINT NOT NULL,"
                                            + " flags INTEGER(64) NOT NULL,"
                                            + " author INTEGER(64) NOT NULL,"
                                            + " title VARCHAR(128) NOT NULL,"
                                            + " imgurl VARCHAR(512) NOT NULL,"
                                            + " postlink VARCHAR(512) NOT NULL,"
                                            + " description VARCHAR(512) NOT NULL);"};
    
    @SuppressWarnings("ConvertToTryWithResources")
    private DatabaseConn(){
        hostname = "localhost";
        port = 3306;
        database = "fastshare";
        username = "root";
        password = "fastsharecms";
        
        try {
            DriverManager.registerDriver(new Driver());
            String url = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database);
            m_connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB Connection stablished successfully\n"
                    + "Creating relationships...");
            Statement stm = m_connection.createStatement();
            for (String QRY : CREATE_STATEMENT) stm.executeUpdate(QRY);
            stm.close();
            System.out.println("Relationships created successfully!");
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
