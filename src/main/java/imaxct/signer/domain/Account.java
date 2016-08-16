package imaxct.signer.domain;

import imaxct.signer.misc.Reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TieBa Account POJO
 * Created by maxct on 2016/8/11.
 */
@Entity
@Table(name = Reference.DB_PREFIX + "_account")
public class Account implements Serializable{
    private int id;
    private String name;
    private String cookie;
    private String openUid;
    private User userId;
    public Account(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getOpenUid() {
        return openUid;
    }

    public void setOpenUid(String openUid) {
        this.openUid = openUid;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
