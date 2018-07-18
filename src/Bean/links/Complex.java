package Bean.links;

import Bean.User;
import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.ComplexLinkDaoInterface;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Complex {
    private Long id = null;
    private String origin;
    private String target;
    private Boolean enabled;
    private Date dateAvailable;
    private Date expiration;
    private Date createdAt;
    private User user;
    private String password;
    private Integer maxClick;

    public Complex(String origin, Date dateAvailable, Date expiration, User user, String password, Integer maxClick) {
        this.origin = origin;
        setTarget(null);
        this.dateAvailable = dateAvailable;
        this.expiration =  expiration;
        this.user = user;
        this.password = password;
        this.maxClick = maxClick;
    }

    public Integer getMaxClick() {
        return maxClick;
    }

    public void setMaxClick(Integer maxClick) {
        this.maxClick = maxClick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        if (target == null) {
            this.target = generateString();
        } else {
            this.target = target;
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static ArrayList<Complex> findByUser(User user) throws SQLException {
        return compleLinkxDao().findByUserId(user.getId());
    }

    public boolean save() throws SQLException {
        if (this.getId() != null) {
            compleLinkxDao().update(this);
            return true;
        }

        try {
            compleLinkxDao().insert(this);
            return true;
        } catch (AlreadyExistException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static ComplexLinkDaoInterface compleLinkxDao(){
        DatabaseFactory dao = DatabaseFactory.getDatabase();

        return dao.getComplexLinkDao();
    }

    private String generateString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();
    }
}
