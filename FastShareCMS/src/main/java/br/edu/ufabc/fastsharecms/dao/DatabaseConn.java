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
    
    private static final String[] CREATE_STATEMENT = {
        "CREATE TABLE IF NOT EXISTS ent_Role (id SMALLINT NOT NULL,"
                                            + " role VARCHAR(64) NOT NULL,"
                                            + " PRIMARY KEY(id));",
        "CREATE TABLE IF NOT EXISTS ent_User (id BIGINT NOT NULL AUTO_INCREMENT,"
                                            + " username VARCHAR(128) NOT NULL,"
                                            + " name VARCHAR(256) NOT NULL UNIQUE,"
                                            + " email VARCHAR(256) NOT NULL,"
                                            + " nrole SMALLINT NOT NULL,"
                                            + " psalt CHAR(20) NOT NULL,"
                                            + " phash VARCHAR(255) NOT NULL,"
                                            + " approved TINYINT NOT NULL,"
                                            + " PRIMARY KEY(id),"
                                            + " FOREIGN KEY(nrole) REFERENCES ent_Role(id),"
                                            + " FULLTEXT (username));",
        "CREATE TABLE IF NOT EXISTS ent_Post (id BIGINT NOT NULL AUTO_INCREMENT,"
                                            + " date BIGINT NOT NULL,"
                                            + " flags BIGINT NOT NULL,"
                                            + " author BIGINT NOT NULL,"
                                            + " title VARCHAR(128) NOT NULL,"
                                            + " imgurl VARCHAR(512) NOT NULL,"
                                            + " postlink VARCHAR(512) NOT NULL,"
                                            + " description VARCHAR(512) NOT NULL,"
                                            + " PRIMARY KEY(id),"
                                            + " FOREIGN KEY(author) REFERENCES ent_User(id),"
                                            + " INDEX idx_date (date DESC));",
        "INSERT INTO ent_Role (id, role)"
            + " SELECT * FROM (SELECT 0, 'ADMIN') AS tmp"
                + " WHERE NOT EXISTS ("
                    + " SELECT id FROM ent_Role WHERE id = 0"
                + " ) LIMIT 1;",
        "INSERT INTO ent_Role (id, role)"
            + " SELECT * FROM (SELECT 1, 'POSTER') AS tmp"
                + " WHERE NOT EXISTS ("
                    + " SELECT id FROM ent_Role WHERE id = 1"
                + " ) LIMIT 1;"};
    
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
