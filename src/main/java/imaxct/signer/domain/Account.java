package imaxct.signer.domain;

import imaxct.signer.misc.Reference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TieBa Account POJO
 * Created by maxct on 2016/8/11.
 */
@Entity
@Table(name = Reference.DB_PREFIX + "_account")
public class Account implements Serializable{
    private int id;
    private int uid;
    private String name;
    private String cookie;
    private String openUid;
    private User userId;
    private int tiebaTotal = 0;
    private int signedTotal = 0;
    public Account(){}

    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 30, unique = true)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 250)
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Column(length = 50)
    public String getOpenUid() {
        return openUid;
    }

    public void setOpenUid(String openUid) {
        this.openUid = openUid;
    }

    @ManyToOne
    @JoinColumn(name = "userid")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Column(length = 30)
    public int getTiebaTotal() {
        return tiebaTotal;
    }

    public void setTiebaTotal(int tiebaTotal) {
        this.tiebaTotal = tiebaTotal;
    }

    @Column(length = 30)
    public int getSignedTotal() {
        return signedTotal;
    }

    public void setSignedTotal(int signedTotal) {
        this.signedTotal = signedTotal;
    }

    @Override
    public String toString() {
        return "Account[id="+id+", name="+name+", uid="+uid+"]";
    }
}
