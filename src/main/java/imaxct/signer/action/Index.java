package imaxct.signer.action;

import imaxct.signer.bean.ErrorMsg;
import imaxct.signer.dao.AccountDao;
import imaxct.signer.domain.Account;
import imaxct.signer.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Index action
 * Created by maxct on 2016/8/16.
 */
@Controller
@SessionAttributes({"user"})
public class Index {
    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public ModelAndView Index(ModelMap map){
        if (!map.containsAttribute("user")) {
            ErrorMsg msg = new ErrorMsg("Error", "还没有登录,即将跳转到登录页面.", true, "index.jsp");
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("map", msg.getMap());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("Index");
        User user = (User) map.get("user");
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.getAccounts(user);
        if (accounts != null){
            for (Account account : accounts){
                int tot = accountDao.countSigned(account);
                account.setSignedTotal(tot);
                accountDao.update(account);
            }
        }
        modelAndView.addObject("accounts", accounts);
        return modelAndView;
    }
}
