package br.edu.ufabc.fastsharecms.dao;

/**
 *
 * @author Mauro
 */
public class DatabaseConn {
    
    private static DatabaseConn db = null;
    
    private DatabaseConn(){}
    public static DatabaseConn getInstance(){
        if (db == null) db = new DatabaseConn();
        return db;
    }
    
}
