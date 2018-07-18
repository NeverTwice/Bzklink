package Core.Database;

import java.sql.Connection;

import dao.Interfaces.*;
import dao.UserDao;

public abstract class DatabaseFactory {
    public abstract Connection openConnection();
    public abstract UserDaoInterface getUserDao();
    public abstract MaxClickDaoInterface getMaxClickDao();
    public abstract StatsClickDaoInterface getStatsClickDao();
    public abstract PasswordLinkDaoInterface getPasswordLinkDao();
    public abstract SimpleLinkDaoInterface getSimpleLinkDao();
    public abstract ComplexLinkDaoInterface getComplexLinkDao();

    public static DatabaseFactory getDatabase() {
        return new Mysql();
    }
}