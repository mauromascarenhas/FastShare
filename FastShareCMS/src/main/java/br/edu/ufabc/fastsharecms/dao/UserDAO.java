package br.edu.ufabc.fastsharecms.dao;

import br.edu.ufabc.fastsharecms.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauro
 */
public class UserDAO implements GenericDAO<User>{

    private final DatabaseConn db;

    public UserDAO() {
        this.db = DatabaseConn.getInstance();
    }
    
    @Override
    public Boolean exists(User item) {
        return false;
    }

    @Override
    public boolean exists(Long index) {
        return false;
    }

    @Override
    public Boolean insert(User item) {
        return false;
    }

    @Override
    public Boolean remove(User item) {
        return false;
    }

    @Override
    public Boolean remove(Long index) {
        return false;
    }

    @Override
    public Boolean update(Long index, User newData) {
        return false;
    }

    @Override
    public User select(Long index) {
        return new User();
    }

    @Override
    public List<User> selectAll() {
        return new ArrayList<>();
    }
    
}
