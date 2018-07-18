package dao.Interfaces;

import Bean.links.Simple;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface SimpleLinkDaoInterface {
    Simple insert(Simple object) throws AlreadyExistException, SQLException;
    Simple update(Simple object) throws SQLException;
    List<Simple> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(Simple Simple) throws SQLException;
    Simple findBy(String key, String value) throws SQLException;
    Simple findById(Long id) throws SQLException;
}
