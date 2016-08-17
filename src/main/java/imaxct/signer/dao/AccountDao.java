package imaxct.signer.dao;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.User;

import java.util.List;

/**
 * Account DAO
 * Created by maxct on 2016/8/17.
 */
public class AccountDao extends BaseDao<Account>{

    public boolean saveAccount(Account account){
        return this.create(account);
    }

    public Account getAccount(int id){
        return this.find(Account.class, id);
    }

    public boolean deleteAccount(Account account){
        return this.delete(account);
    }

    public boolean updateAccount(Account account){
        if (account == null)
            return false;
        Account a = getAccount(account.getId());
        if (a == null)
            return false;
        if (a.getCookie() != null && account.getCookie() != null && !a.getCookie().equals(account.getCookie()))
            a.setCookie(account.getCookie());
        if (!a.getUserId().equals(account.getUserId()))
            a.setUserId(account.getUserId());
        return this.update(a);
    }

    public List<Account> getAccounts(User user){
        return this.listHql("from Account a where a.userId=?", user);
    }
}
