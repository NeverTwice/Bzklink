package dao;

import Bean.User;
import Bean.links.Simple;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.SimpleLinkDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SimpleLinkDao implements SimpleLinkDaoInterface {
    private static final String INSERT = "INSERT INTO simple_link (origin, target, is_enabled, created_at) VALUES (?,?,?,now())";

    private static final String FIND_BY = "SELECT * FROM simple_link WHERE ";



    public Simple findBy(String key, String value) throws SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(FIND_BY + key + " = ?");
        pstmt.setString(1, value);

        Simple simple = null;
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            simple = createSimple(rset);
        }

        pstmt.close();
        c.close();

        return simple;
    }

    @Override
    public Simple update(Simple object) {
        return null;
    }

    public Simple insert(Simple simple) throws AlreadyExistException, SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, simple.getOrigin());
        pstmt.setString(2, simple.getTarget());
        pstmt.setBoolean(3, true);

        pstmt.executeUpdate();

        ResultSet rset = pstmt.getGeneratedKeys();

        rset.next();
        Long idGenerated = rset.getLong(1);
        simple.setId(idGenerated);

        pstmt.close();
        c.close();

        return simple;
    }

    /* method to create users **/
    private Simple createSimple(ResultSet rset) throws SQLException{
        Simple simple = new Simple(
                rset.getString("origin")
        );

        simple.setEnabled(rset.getBoolean("is_enabled"));
        simple.setTarget(rset.getString("target"));
        simple.setId(rset.getLong("id"));

        return simple;
    }

    @Override
    public Simple findById(Long id) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(Simple Simple) {
        return 0;
    }

    @Override
    public List<Simple> all() {
        return null;
    }
}
