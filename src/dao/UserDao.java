package dao;

import Bean.User;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.UserDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface {
    private static final String INSERT = "INSERT INTO users (login, email, password, type, token) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE users SET login = ?, email = ?, password = ?, type = ?, enabled = ? WHERE id = ?";

    private static final String ALL = "SELECT * FROM users";

    private static final String FIND_BY = "SELECT * FROM users WHERE ";

    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String DELETE = "DELETE FROM users where id = ?";

    private static final String DELETE_ALL = "DELETE FROM users";

    public User insert(User user) throws AlreadyExistException, SQLException {
        User userByLogin = findBy("login", user.getLogin());
        User userByEmail = findBy("email", user.getEmail());

        if (userByLogin != null || userByEmail != null) {
            throw new AlreadyExistException("User already exist");
        }

        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getType());
        pstmt.setString(5, user.getToken());

        pstmt.executeUpdate();

        ResultSet rset = pstmt.getGeneratedKeys();

        rset.next();
        Long idGenerated = rset.getLong(1);
        user.setId(idGenerated);

        pstmt.close();
        c.close();

        return user;
    }

    public User update(User user) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(UPDATE);

        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getType());
        pstmt.setBoolean(5, user.isEnabled());
        pstmt.setLong(6, user.getId());

        pstmt.executeUpdate();

        pstmt.close();
        c.close();

        return user;
    }

    public List<User> all() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        Connection c = DatabaseFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(ALL);

        ResultSet rset = pstmt.executeQuery();
        while (rset.next()){
            users.add(createUser(rset));
        }

        pstmt.close();
        c.close();

        return users;
    }

    public int deleteAll() throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(DELETE_ALL);

        int rowsAffected = pstmt.executeUpdate();

        pstmt.close();
        c.close();

        return rowsAffected;
    }

    public int delete(User user) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(DELETE);
        pstmt.setLong(1, user.getId());

        int rowsAffected = pstmt.executeUpdate();

        pstmt.close();
        c.close();

        return rowsAffected;
    }

    public User findBy(String key, String value) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(FIND_BY + key + " = ?");
        pstmt.setString(1, value);

        User user = null;
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            user = createUser(rset);
        }

        pstmt.close();
        c.close();

        return user;
    }

    public User findById(Long id) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
        pstmt.setLong(1, id);

        User user = null;
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            user = createUser(rset);
        }

        pstmt.close();
        c.close();

        return user;
    }

    /* method to create users **/
    private User createUser(ResultSet rset) throws SQLException{
        User user = new User(
            rset.getString("login"),
            rset.getString("email"),
            rset.getString("password"),
            rset.getString("type")
        );

        user.setEnabled(rset.getBoolean("enabled"));
        user.setToken(rset.getString("token"));
        user.setId(rset.getLong("id"));

        return user;
    }
}
