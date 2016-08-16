package imaxct.signer.domain;

import imaxct.signer.misc.Reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User POJO
 * Created by maxct on 2016/8/10.
 */
@Entity
@Table(name = Reference.DB_PREFIX + "_user")
public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
