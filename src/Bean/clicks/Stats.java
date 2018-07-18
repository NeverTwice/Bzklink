package Bean.clicks;

import Bean.links.Complex;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.StatsClickDaoInterface;
import dao.StatsClickDao;

import java.sql.SQLException;
import java.util.Date;

public class Stats {
    private Long id = null;
    private Date createdAt;
    private Complex complex;

    public Stats(Complex complex) {
        this.complex = complex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public boolean save() throws SQLException {
        if (this.getId() != null) {
            statsClickDao().update(this);
            return true;
        }

        try {
            statsClickDao().insert(this);
            return true;
        } catch (AlreadyExistException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static StatsClickDaoInterface statsClickDao(){
        DatabaseFactory dao = DatabaseFactory.getDatabase();

        return dao.getStatsClickDao();
    }
}
