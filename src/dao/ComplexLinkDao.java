package dao;

import Bean.User;
import Bean.links.Complex;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.ComplexLinkDaoInterface;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexLinkDao implements ComplexLinkDaoInterface {
    private static final String INSERT = "INSERT INTO complex_link (origin, target, is_enabled, date_available, expiration, created_at, user_id, password, max_click) VALUES (?, ?, ?, ?, ?, now(), ?, ?, ?)";
    private static final String FIND_BY = "SELECT * FROM complex_link WHERE ";

    public Complex insert(Complex complex) throws AlreadyExistException, SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, complex.getOrigin());
        pstmt.setString(2, complex.getTarget());
        pstmt.setBoolean(3, true);
        pstmt.setDate(4, complex.getDateAvailable());
        pstmt.setDate(5, complex.getExpiration());
        pstmt.setLong(6, complex.getUser().getId());
        pstmt.setString(7, complex.getPassword());
        pstmt.setInt(8, complex.getMaxClick());

        pstmt.executeUpdate();

        ResultSet rset = pstmt.getGeneratedKeys();

        rset.next();
        Long idGenerated = rset.getLong(1);
        complex.setId(idGenerated);

        pstmt.close();
        c.close();

        return complex;
    }

    public Complex findBy(String key, String value) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(FIND_BY + key + " = ?");
        pstmt.setString(1, value);

        Complex complex = null;
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            complex = createComplexLink(rset);
        }

        pstmt.close();
        c.close();

        return complex;
    }

    public ArrayList<Complex> findByUserId(Long id) throws SQLException {
        ArrayList<Complex> complexes = new ArrayList<>();

        Connection c = DatabaseFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(FIND_BY + "user_id" + "= ?");
        pstmt.setLong(1, id);

        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            complexes.add(createComplexLink(rset));
        }

        pstmt.close();
        c.close();

        return complexes;
    }

    /* method to create users **/
    private Complex createComplexLink(ResultSet rset) throws SQLException{
        Long userId = rset.getLong("user_id");

        UserDao userDao = new UserDao();
        User user = userDao.findById(userId);

        Date available_date = rset.getDate("date_available");
        Date expiration = rset.getDate("expiration");

        java.sql.Date start = null;
        java.sql.Date expire = null;

        if (available_date != null) {
            start = new java.sql.Date(available_date.getTime());
        }

        if (expiration != null) {
            expire = new java.sql.Date(expiration.getTime());
        }

        Complex complex = new Complex(
                rset.getString("origin"),
                start,
                expire,
                user,
                rset.getString("password"),
                rset.getInt("max_click")
        );

        complex.setEnabled(rset.getBoolean("is_enabled"));
        complex.setId(rset.getLong("id"));

        return complex;
    }

    @Override
    public List<Complex> all() {
        return null;
    }

    @Override
    public Complex update(Complex object) {
        return null;
    }

    @Override
    public Complex findById(Long id) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(Complex complex) {
        return 0;
    }
}
