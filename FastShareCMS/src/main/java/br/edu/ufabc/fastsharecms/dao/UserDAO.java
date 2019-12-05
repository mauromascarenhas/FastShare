package br.edu.ufabc.fastsharecms.dao;

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
public class UserDAO implements GenericDAO<User>{

    private static UserDAO udao = null;
    
    private final DatabaseConn db;

    private UserDAO() {
        this.db = DatabaseConn.getInstance();
    }
    
    public static UserDAO getInstance(){
        if (udao == null) udao = new UserDAO();
        return udao;
    }
    
    @Override
    public Boolean exists(User item) {
        return select(item) != null;
    }

    @Override
    public Boolean exists(Long index) {
        return select(index) != null;
    }
    
    @Override
    public Boolean insert(User item) {
        String sql = "INSERT INTO ent_User (username, name, role, email, psalt, phash, approved)"
                + " VALUES (?, ?, 'POSTER', ?, ?, ?, 0)";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, item.getUsername());
            stm.setString(2, item.getName());
            stm.setString(3, item.getEmail());
            stm.setString(4, item.getPsalt());
            stm.setString(5, item.getPhash());
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean remove(User item) {
        String sql = "DELETE FROM ent_User WHERE username = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, item.getUsername());
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean remove(Long index) {
        String sql = "DELETE FROM ent_User WHERE id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, index);
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean update(Long index, User newData) {
        String sql = "UPDATE ent_User SET username = ?, name = ?, role = ?,"
                + " email = ?, psalt = ?, phash = ?, approved = ?"
                + " WHERE id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, newData.getUsername());
            stm.setString(2, newData.getName());
            stm.setString(3, newData.getRole());
            stm.setString(4, newData.getEmail());
            stm.setString(5, newData.getPsalt());
            stm.setString(6, newData.getPhash());
            stm.setInt(7, newData.getApproved() ? 1 : 0);
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public User select(Long index) {
        User user = null;
        String sql = "SELECT * FROM ent_User WHERE id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, index);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") == 0 ? Boolean.FALSE : Boolean.TRUE);
            }
        } catch (SQLException e){
            return null;
        }
        return user;
    }
    
    @Override
    public User select(User item){
        User user = null;
        String sql = "SELECT * FROM ent_User WHERE username = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, item.getUsername());
            ResultSet res = stm.executeQuery();
            if (res.next()){
                user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") != 0);
            }
        } catch (SQLException e){
            return null;
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM ent_User";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            ResultSet res = stm.executeQuery();
            while (res.next()){
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setRole(res.getString("role"));
                user.setPsalt(res.getString("psalt"));
                user.setPhash(res.getString("phash"));
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("username"));
                user.setApproved(res.getInt("approved") != 0);
                users.add(user);
            }
        } catch (SQLException e){
            return new ArrayList<>();
        }
        return users;
    }
    
}
