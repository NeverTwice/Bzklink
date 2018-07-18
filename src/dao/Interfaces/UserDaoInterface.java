package dao.Interfaces;

import Bean.User;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInterface {
    User insert(User object) throws AlreadyExistException, SQLException;
    User update(User object) throws SQLException;
    List<User> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(User user) throws SQLException;
    User findBy(String key, String value) throws SQLException;
    User findById(Long id) throws SQLException;
}
