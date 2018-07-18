package dao.Interfaces;

import Bean.links.Complex;
import dao.Exceptions.AlreadyExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ComplexLinkDaoInterface {
    Complex insert(Complex object) throws AlreadyExistException, SQLException;
    Complex update(Complex object) throws SQLException;
    List<Complex> all() throws SQLException;
    int deleteAll() throws SQLException;
    int delete(Complex complex) throws SQLException;
    Complex findBy(String key, String value) throws SQLException;
    ArrayList<Complex> findByUserId(Long id) throws SQLException;
    Complex findById(Long id) throws SQLException;
}
