package imaxct.signer.domain;

import imaxct.signer.misc.Reference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Tieba POJO
 * Created by maxct on 2016/8/10.
 */
@Entity
@Table(name = Reference.DB_PREFIX + "_tieba")
public class Tieba implements Serializable{
    private int id;
    private int fid;
    private String name;
    private Account account;
    private Date lastSign;
    private int errcode;
    private boolean skip = false;

    public Tieba() {}

    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 40)
    public int getFid() {
        return fid;
    }

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    public Date getLastSign() {
        return lastSign;
    }

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    @Column(columnDefinition = "int(20) default 233")
    public int getErrcode() {
        return errcode;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setLastSign(Date lastSign) {
        this.lastSign = lastSign;
    }
}
