package Core.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.*;
import dao.Interfaces.*;

public class Mysql extends DatabaseFactory {

    private static String driver = "com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://127.0.0.1:8889/";
    private static String database = "urls";
    private static String user = "root";
    private static String password = "root";

    public Connection openConnection() {
        try {
            Class.forName(driver);

            return DriverManager.getConnection(url + database, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            System.err.println("Driver not found");
        }
        return null;
    }

    @Override
    public UserDaoInterface getUserDao() {
        return new UserDao();
    }

    @Override
    public MaxClickDaoInterface getMaxClickDao() {
        return new MaxClickDao();
    }

    @Override
    public PasswordLinkDaoInterface getPasswordLinkDao() {
        return new PasswordLinkDao();
    }

    @Override
    public SimpleLinkDaoInterface getSimpleLinkDao() {
        return new SimpleLinkDao();
    }

    @Override
    public StatsClickDaoInterface getStatsClickDao() {
        return new StatsClickDao();
    }

    @Override
    public ComplexLinkDaoInterface getComplexLinkDao() {
        return new ComplexLinkDao();
    }
}