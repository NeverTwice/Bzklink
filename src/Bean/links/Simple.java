package Bean.links;

import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.SimpleLinkDaoInterface;
import dao.Interfaces.UserDaoInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class Simple {
    private Long id = null;
    private String origin;
    private String target;
    private Boolean enabled;
    private Date createdAt;

    public Simple(String origin) {
        this.origin = origin;
        setTarget(null);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean save() throws SQLException {
        if (this.getId() != null) {
            simpleLinkDao().update(this);
            return true;
        }

        try {
            simpleLinkDao().insert(this);
            return true;
        } catch (AlreadyExistException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static SimpleLinkDaoInterface simpleLinkDao(){
        DatabaseFactory dao = DatabaseFactory.getDatabase();

        return dao.getSimpleLinkDao();
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
