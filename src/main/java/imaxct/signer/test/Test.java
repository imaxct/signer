package imaxct.signer.test;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.Tieba;
import imaxct.signer.function.AccountFunction;
import imaxct.signer.function.TiebaFunction;
import imaxct.signer.misc.Reference;

import java.util.List;

/**
 * Test Class
 * Created by maxct on 2016/8/11.
 */
public class Test {
    public static void main(String[] args){
        Account account;
        account = new Account();
        account.setCookie(Reference.TEST_BDUSS);
        AccountFunction ac = new AccountFunction();
        account = ac.getAccountInfo(account);
        List<Tieba> list = ac.getLikedTieba(account);
        TiebaFunction function = new TiebaFunction();
        if (list != null){
            for (Tieba t : list){
                System.out.println(t.getName() + "\t" + t.getFid());
                System.out.println(t.getAccount().getName());
                function.sign(t);
            }
        }else
            System.out.println("null list");
    }
}
