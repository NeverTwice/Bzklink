package dao.Interfaces;

import Bean.links.Password;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface PasswordLinkDaoInterface {
    Password insert(Password object) throws AlreadyExistException, SQLException;
    Password update(Password object) throws SQLException;
    List<Password> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(Password Password) throws SQLException;
    Password findBy(String key, String value) throws SQLException;
    Password findById(Long id) throws SQLException;
}
