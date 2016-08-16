package imaxct.signer.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by maxct on 2016/8/14.
 */
@Controller
@RequestMapping(value = "/Test")
public class Test {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
