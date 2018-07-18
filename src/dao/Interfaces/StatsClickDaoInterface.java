package dao.Interfaces;

import Bean.clicks.Stats;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.List;

public interface StatsClickDaoInterface {
    Stats insert(Stats object) throws AlreadyExistException, SQLException;
    Stats update(Stats object) throws SQLException;
    List<Stats> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(Stats Stats) throws SQLException;
    Stats findBy(String key, String value) throws SQLException;
    Stats findById(Long id) throws SQLException;
}
