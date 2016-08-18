package imaxct.signer.dao;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.User;
import imaxct.signer.misc.Lib;
import org.hibernate.Session;

import java.util.ArrayList;
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
        return this.update(account);
    }

    public int countSigned(final Account account){
        List<Integer> list = new ArrayList<>();
        this.template(new SessionProcessor() {
            @Override
            public void process(Session session) {
                int t = (int) session.createQuery("select count(*) from Tieba t where t.lastSign=? and t.account=?")
                        .setParameter(0, Lib.today())
                        .setParameter(1, account)
                        .uniqueResult();
                list.add(t);
            }
        });
        return list.get(0);
    }

    public Account getAccount(String bduss){
        return this.uniqueResult("from Account a where a.cookie=?", bduss);
    }

    public List<Account> getAccounts(User user){
        return this.listHql("from Account a where a.userId=?", user);
    }
}
