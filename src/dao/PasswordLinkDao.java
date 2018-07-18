package dao;

import Bean.links.Password;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.PasswordLinkDaoInterface;

import java.sql.SQLException;
import java.util.List;

public class PasswordLinkDao implements PasswordLinkDaoInterface {
    @Override
    public Password insert(Password object) throws AlreadyExistException, SQLException {
        return null;
    }

    @Override
    public Password update(Password object) throws SQLException {
        return null;
    }

    @Override
    public List<Password> all() throws SQLException {
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        return 0;
    }

    @Override
    public int delete(Password Password) throws SQLException {
        return 0;
    }

    @Override
    public Password findBy(String key, String value) throws SQLException {
        return null;
    }

    @Override
    public Password findById(Long id) throws SQLException {
        return null;
    }
}
