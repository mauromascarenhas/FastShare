package br.edu.ufabc.fastsharecms.dao;

import br.edu.ufabc.fastsharecms.model.Post;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauro
 */
public class PostDAO implements GenericDAO<Post>{
    
    private final DatabaseConn db;
    private static PostDAO dao = null;
    
    private PostDAO(){
        this.db = DatabaseConn.getInstance();
    }
    
    public static PostDAO getInstance(){
        if (dao == null) dao = new PostDAO();
        return dao;
    }

    @Override
    public Boolean exists(Post item) {
        return false;
    }

    @Override
    public boolean exists(Long index) {
        return false;
    }

    @Override
    public Boolean insert(Post item) {
        return false;
    }

    @Override
    public Boolean remove(Post item) {
        return false;
    }

    @Override
    public Boolean remove(Long index) {
        return false;
    }

    @Override
    public Boolean update(Long index, Post newData) {
        return false;
    }

    @Override
    public Post select(Long index) {
        return new Post();
    }

    @Override
    public List<Post> selectAll() {
        return new ArrayList<>();
    }
}
