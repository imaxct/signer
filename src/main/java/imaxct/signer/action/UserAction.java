package imaxct.signer.action;

import imaxct.signer.bean.Msg;
import imaxct.signer.dao.UserDao;
import imaxct.signer.domain.User;
import imaxct.signer.misc.Lib;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * User actions
 * Created by maxct on 2016/8/16.
 */
@Controller
@RequestMapping(name = "/User")
@SessionAttributes({"user"})
public class UserAction {
    public String login(@RequestParam String username, @RequestParam String password,
                        ModelMap map, HttpServletRequest req){
        Msg m = new Msg();
        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);
        if (user != null){
            if (user.getPassword().equals(Lib.generatePassword(password))){
                map.put("user", user);
                m.put("errcode", 0);
                m.put("errmsg", "ok");
            }else{
                m.put("errcode", -1);
                m.put("errmsg", "密码错误");
            }
            req.setAttribute("msg", Lib.gsonToString(m.msg));
            return "ajax";
        }
        m.put("errcode", -1);
        m.put("errmsg", "用户不存在");
        req.setAttribute("msg", Lib.gsonToString(m.msg));
        return "ajax";
    }
}
