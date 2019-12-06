package br.edu.ufabc.fastsharecms.dao;

import br.edu.ufabc.fastsharecms.model.Post;
import br.edu.ufabc.fastsharecms.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return select(item) != null;
    }

    @Override
    public Boolean exists(Long index) {
        return select(index) != null;
    }

    @Override
    public Boolean insert(Post item) {
        String sql = "INSERT INTO ent_Post (date, flags, author, title, imgurl, postlink, description)"
                + " VALUES (?, 0, ?, ?, ?, ?, ?)";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){        
            stm.setLong(1, item.getDate());
            stm.setLong(2, item.getAuthor().getId());
            stm.setString(3, item.getTitle());
            stm.setString(4, item.getImgURL());
            stm.setString(5, item.getPostLink());
            stm.setString(6, item.getDescription());
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean remove(Post item) {
        String sql = "DELETE FROM ent_Post WHERE title = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, item.getTitle());
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean remove(Long index) {
        String sql = "DELETE FROM ent_Post WHERE id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, index);
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean update(Long index, Post newData) {
        String sql = "UPDATE ent_Post SET date = ?, flags = ?, author = ?,"
                + " title = ?, imgurl = ?, postlink = ?, description = ?"
                + " WHERE id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, newData.getDate());
            stm.setLong(2, newData.getFlags());
            stm.setLong(3, newData.getAuthor().getId());
            stm.setString(4, newData.getTitle());
            stm.setString(5, newData.getImgURL());
            stm.setString(6, newData.getPostLink());
            stm.setString(7, newData.getDescription());
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Post select(Long index) {
        Post post = null;
        String sql = "SELECT *"
                   + " FROM ent_Post INNER JOIN ent_User"
                   + " ON ent_Post.author = ent_User.id"
                   + " WHERE ent_Post.id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, index);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                post = new Post();
                post.setId(res.getLong("ent_Post.id"));
                post.setDate(res.getLong("date"));
                post.setFlags(res.getLong("flags"));
                post.setTitle(res.getString("title"));
                post.setImgURL(res.getString("imgurl"));
                post.setPostLink(res.getString("postlink"));
                post.setDescription(res.getString("description"));
                
                User user = new User();
                user.setId(res.getLong("ent_User.id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
                post.setAuthor(user);
            }
        } catch (SQLException e){
            return null;
        }
        return post;
    }
    
    @Override
    public Post select(Post item) {
        Post post = null;
        String sql = "SELECT *"
                   + " FROM ent_Post INNER JOIN ent_User"
                   + " ON ent_Post.author = ent_User.id"
                   + " WHERE ent_Post.id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, item.getTitle());
            ResultSet res = stm.executeQuery();
            if (res.next()){
                post = new Post();
                post.setId(res.getLong("ent_Post.id"));
                post.setDate(res.getLong("date"));
                post.setFlags(res.getLong("flags"));
                post.setTitle(res.getString("title"));
                post.setImgURL(res.getString("imgurl"));
                post.setPostLink(res.getString("postlink"));
                post.setDescription(res.getString("description"));
                
                User user = new User();
                user.setId(res.getLong("ent_User.id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
                post.setAuthor(user);
            }
        } catch (SQLException e){
            return null;
        }
        return post;
    }

    @Override
    public List<Post> selectAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT *"
                   + " FROM ent_Post INNER JOIN ent_User"
                   + " ON ent_Post.author = ent_User.id"
                   + " ORDER BY date DESC";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            ResultSet res = stm.executeQuery();
            if (res.next()){
                Post post = new Post();
                post.setId(res.getLong("ent_Post.id"));
                post.setDate(res.getLong("date"));
                post.setFlags(res.getLong("flags"));
                post.setTitle(res.getString("title"));
                post.setImgURL(res.getString("imgurl"));
                post.setPostLink(res.getString("postlink"));
                post.setDescription(res.getString("description"));
                
                User user = new User();
                user.setId(res.getLong("ent_User.id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
                post.setAuthor(user);
                posts.add(post);
            }
        } catch (SQLException e){
            return null;
        }
        return posts;
    }
    
    public List<Post> selectAllLike(String query) {
        query = String.format("%%%s%%", query);
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT *"
                   + " FROM ent_Post INNER JOIN ent_User"
                   + " ON ent_Post.author = ent_User.id"
                   + " WHERE title LIKE ? OR description LIKE ?"
                   + " ORDER BY date DESC";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, query);
            stm.setString(2, query);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                Post post = new Post();
                post.setId(res.getLong("ent_Post.id"));
                post.setDate(res.getLong("date"));
                post.setFlags(res.getLong("flags"));
                post.setTitle(res.getString("title"));
                post.setImgURL(res.getString("imgurl"));
                post.setPostLink(res.getString("postlink"));
                post.setDescription(res.getString("description"));
                
                User user = new User();
                user.setId(res.getLong("ent_User.id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
                post.setAuthor(user);
                posts.add(post);
            }
        } catch (SQLException e){
            return null;
        }
        return posts;
    }
    
    public List<Post> selectAllFromDate(Long date) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT *"
                   + " FROM ent_Post INNER JOIN ent_User"
                   + " ON ent_Post.author = ent_User.id"
                   + " WHERE date < ?"
                   + " ORDER BY date DESC";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, date);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                Post post = new Post();
                post.setId(res.getLong("ent_Post.id"));
                post.setDate(res.getLong("date"));
                post.setFlags(res.getLong("flags"));
                post.setTitle(res.getString("title"));
                post.setImgURL(res.getString("imgurl"));
                post.setPostLink(res.getString("postlink"));
                post.setDescription(res.getString("description"));
                
                User user = new User();
                user.setId(res.getLong("ent_User.id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
                post.setAuthor(user);
                posts.add(post);
            }
        } catch (SQLException e){
            return null;
        }
        return posts;
    }
}
