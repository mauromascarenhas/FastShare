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
        String sql = "INSERT INTO ent_User (username, name, nrole, email, psalt, phash, approved)"
                + " VALUES (?, ?, 1, ?, ?, ?, 0)";
        try(PreparedStatement stm = db.connection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            stm.setString(1, item.getUsername());
            stm.setString(2, item.getName());
            stm.setString(3, item.getEmail());
            stm.setString(4, item.getPsalt());
            stm.setString(5, item.getPhash());
            
            if (stm.executeUpdate() != 0){
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()){
                    if (rs.getLong(1) == 1L){
                        item.setRole("ADMIN");
                        return update(1L, item);
                    }
                    else return true;
                }
                return false;
            }
            else return false;
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
        String sql = "DELETE FROM ent_User WHERE ent_User.id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setLong(1, index);
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Boolean update(Long index, User newData) {
        String sql = "UPDATE ent_User SET username = ?, name = ?, nrole = ?,"
                + " email = ?, psalt = ?, phash = ?, approved = ?"
                + " WHERE ent_User.id = ?";
        try(PreparedStatement stm = db.connection().prepareStatement(sql)){
            stm.setString(1, newData.getUsername());
            stm.setString(2, newData.getName());
            stm.setShort(3, (short)(newData.getRole().equals("POSTER") ? 1 : 0));
            stm.setString(4, newData.getEmail());
            stm.setString(5, newData.getPsalt());
            stm.setString(6, newData.getPhash());
            stm.setByte(7, (byte)(newData.getApproved() ? 1 : 0));
            stm.setLong(8, index);
            return stm.executeUpdate() != 0;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public User select(Long index) {
        User user = null;
        String sql = "SELECT * FROM ent_User INNER JOIN ent_Role ON ent_User.nrole = ent_Role.id"
                + " WHERE ent_User.id = ?";
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
                user.setApproved(res.getByte("approved") != 0);
            }
        } catch (SQLException e){
            return null;
        }
        return user;
    }
    
    @Override
    public User select(User item){
        User user = null;
        String sql = "SELECT * FROM ent_User INNER JOIN ent_Role ON ent_User.nrole = ent_Role.id"
                    + " WHERE username = ?";
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
                user.setApproved(res.getByte("approved") != 0);
            }
        } catch (SQLException e){
            return null;
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM ent_User INNER JOIN ent_Role ON ent_User.nrole = ent_Role.id"
                    + " ORDER BY username ASC";
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
                user.setApproved(res.getByte("approved") != 0);
                users.add(user);
            }
        } catch (SQLException e){
            return new ArrayList<>();
        }
        return users;
    }
    
}
