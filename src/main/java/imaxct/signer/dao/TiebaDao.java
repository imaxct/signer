package imaxct.signer.dao;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.Tieba;

import java.util.Date;
import java.util.List;

/**
 * TIEBA DAO
 * Created by maxct on 2016/8/17.
 */
public class TiebaDao extends BaseDao<Tieba> {

    public boolean saveTieba(Tieba tieba) {
        return this.create(tieba);
    }

    public Tieba getTieba(int id) {
        return this.find(Tieba.class, id);
    }

    public boolean updateTieba(Tieba tieba) {
        if (tieba == null)
            return false;
        Tieba t = getTieba(tieba.getId());
        if (t == null)
            return false;
        t.setLastSign(tieba.getLastSign());
        t.setErrcode(tieba.getErrcode());
        return this.update(t);
    }

    public boolean setSkip(Tieba tieba, boolean flag) {
        if (tieba == null)
            return false;
        Tieba t = getTieba(tieba.getId());
        if (t == null)
            return false;
        t.setSkip(flag);
        return this.update(t);
    }

    public List<Tieba> getOnesTieba(Account account) {
        return this.listHql("from Tieba t where t.account=?", account);
    }

    public List<Tieba> getUnsignedTieba(Date date) {
        return this.listHql("from Tieba t where (t.lastSign<? or t.lastSign=null or t.errcode=110001 or t.errcode=160003 or t.errcode=160008) and t.skip=false", date);
    }

    public boolean deleteOnesTieba(Account account){
        return this.update("delete from Tieba where account=?", account);
    }
}
