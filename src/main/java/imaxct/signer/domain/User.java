package imaxct.signer.domain;

import imaxct.signer.misc.Reference;

import javax.persistence.*;
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
    private int role;
    public User(){}

    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 30, unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 30, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 10, nullable = false)
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
