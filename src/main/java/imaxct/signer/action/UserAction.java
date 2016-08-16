package imaxct.signer.action;

import imaxct.signer.bean.ErrorMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * User actions
 * Created by maxct on 2016/8/16.
 */
@Controller
@RequestMapping(name = "/User")
@SessionAttributes({"user"})
public class UserAction {
    public ModelAndView login(@RequestParam String username,@RequestParam String password, ModelMap map){
        ErrorMsg msg = new ErrorMsg();
        return new ModelAndView("error", "msg", msg);
    }
}
