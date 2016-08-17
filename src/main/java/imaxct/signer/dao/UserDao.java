package imaxct.signer.dao;

import imaxct.signer.domain.User;

import java.util.List;

/**
 * User DAO
 * Created by maxct on 2016/8/17.
 */
public class UserDao extends BaseDao<User>{

    public boolean saveUser(User user){
        return this.create(user);
    }

    public User getUser(int id){
        return this.find(User.class, id);
    }

    public User getUser(String username){
        return this.uniqueResult("from User u where u.username=?", username);
    }

    public boolean deleteUser(User user){
        return this.delete(user);
    }

    public List<User> getAllUsers(){
        return this.listHql("from User");
    }
}
