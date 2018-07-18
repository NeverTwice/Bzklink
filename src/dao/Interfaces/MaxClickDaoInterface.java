package dao.Interfaces;

import Bean.clicks.Max;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface MaxClickDaoInterface {
    Max insert(Max object) throws AlreadyExistException, SQLException;
    Max update(Max object) throws SQLException;
    List<Max> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(Max Max) throws SQLException;
    Max findBy(String key, String value) throws SQLException;
    Max findById(Long id) throws SQLException;
}
