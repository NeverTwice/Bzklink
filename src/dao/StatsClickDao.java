package dao;

import Bean.clicks.Stats;
import Bean.links.Complex;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.StatsClickDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsClickDao implements StatsClickDaoInterface {
    private static final String INSERT = "INSERT INTO click_stats (created_at, complex_link_id) VALUES (now(), ?)";

    private static final String FIND_BY = "SELECT * FROM click_stats WHERE ";

    public Stats insert(Stats stats) throws AlreadyExistException, SQLException {
        Connection c = DatabaseFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setLong(1, stats.getComplex().getId());

        pstmt.executeUpdate();

        ResultSet rset = pstmt.getGeneratedKeys();

        rset.next();
        Long idGenerated = rset.getLong(1);
        stats.setId(idGenerated);

        pstmt.close();
        c.close();

        return stats;
    }

    @Override
    public Stats update(Stats object) throws SQLException {
        return null;
    }

    @Override
    public List<Stats> all() throws SQLException {
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        return 0;
    }

    @Override
    public int delete(Stats Stats) throws SQLException {
        return 0;
    }

    @Override
    public Stats findBy(String key, String value) throws SQLException {
        return null;
    }

    public ArrayList<Stats> findByComplex(Long id) throws SQLException {
        ArrayList<Stats> complexes = new ArrayList<>();

        Connection c = DatabaseFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(FIND_BY + "complex_link_id" + "= ?");
        pstmt.setLong(1, id);

        ResultSet rset = pstmt.executeQuery();

        while (rset.next()){
            complexes.add(createStatsClick(rset));
        }

        pstmt.close();
        c.close();

        return complexes;
    }

    @Override
    public Stats findById(Long id) throws SQLException {
        return null;
    }

    private Stats createStatsClick(ResultSet rset) throws SQLException{
        Long complexId = rset.getLong("complex_link_id");

        ComplexLinkDao complexLinkDao = new ComplexLinkDao();
        Complex complex = complexLinkDao.findById(complexId);

        Stats stats = new Stats(complex);

        stats.setId(rset.getLong("id"));

        return stats;
    }
}
