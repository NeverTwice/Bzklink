package dao;

import Bean.clicks.Max;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.MaxClickDaoInterface;

import java.sql.SQLException;
import java.util.List;

public class MaxClickDao implements MaxClickDaoInterface {
    @Override
    public Max insert(Max object) throws AlreadyExistException, SQLException {
        return null;
    }

    @Override
    public Max update(Max object) throws SQLException {
        return null;
    }

    @Override
    public List<Max> all() throws SQLException {
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        return 0;
    }

    @Override
    public int delete(Max Max) throws SQLException {
        return 0;
    }

    @Override
    public Max findBy(String key, String value) throws SQLException {
        return null;
    }

    @Override
    public Max findById(Long id) throws SQLException {
        return null;
    }
}
