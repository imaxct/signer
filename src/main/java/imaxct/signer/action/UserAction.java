package imaxct.signer.action;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import imaxct.signer.bean.Msg;
import imaxct.signer.dao.UserDao;
import imaxct.signer.domain.User;
import imaxct.signer.misc.Lib;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User actions
 * Created by maxct on 2016/8/16.
 */
@Controller
@RequestMapping("/User")
@SessionAttributes({"user", Constants.KAPTCHA_SESSION_KEY})
public class UserAction {
    private static final Logger logger = Logger.getLogger(UserAction.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String username,@RequestParam  String password,
                           @RequestParam  String pass,
                           ModelMap map, HttpServletRequest req){
        return "ajax";
    }

    @Autowired
    private Producer captchaProducer = null;

    @RequestMapping(value = "/vcode", method = RequestMethod.GET)
    public String vcode(HttpServletRequest req, HttpServletResponse rep){
        rep.setHeader("Expires", "0");
        rep.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        rep.setHeader("Cache-Control", "post-check=0, pre-check=0");
        rep.setHeader("Pragma", "no-cache");
        rep.setContentType("image/jpeg");
        String vcode = captchaProducer.createText();
        req.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, vcode);
        BufferedImage image = captchaProducer.createImage(vcode);
        ServletOutputStream out = null;
        try {
            out = rep.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("write vcode image failed, " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}