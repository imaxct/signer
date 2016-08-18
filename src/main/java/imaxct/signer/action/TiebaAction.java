package imaxct.signer.action;

import imaxct.signer.bean.Msg;
import imaxct.signer.dao.TiebaDao;
import imaxct.signer.domain.Tieba;
import imaxct.signer.domain.User;
import imaxct.signer.misc.Lib;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Tieba Action
 * Created by maxct on 2016/8/18.
 */
@Controller
@RequestMapping("/Tieba")
@SessionAttributes({"user"})
public class TiebaAction {

    @RequestMapping(value = "/skip", method = RequestMethod.GET)
    public String skipTieba(@RequestParam int id,@RequestParam boolean skip, ModelMap session, HttpServletRequest req){
        Msg m = new Msg();
        if (session.containsAttribute("user")){
            User user = (User) session.get("user");
            TiebaDao tiebaDao = new TiebaDao();
            Tieba tieba = tiebaDao.getTieba(id);
            if (tieba != null && user != null &&
                    tieba.getAccount().getUserId().getId()==user.getId()){
                if (tiebaDao.setSkip(tieba, skip)){
                    m.put("errcode", 0);
                    m.put("errmsg", "ok");
                }else{
                    m.put("errcode", -1);
                    m.put("errmsg", "系统忙");
                }
            }else{
                m.put("errcode", -2);
                m.put("errmsg", "f**k");
            }
        }else{
            m.put("errcode", -1);
            m.put("errmsg", "Access Denied");
        }
        req.setAttribute("msg", Lib.gsonToString(m.msg));
        return "ajax";
    }
}
