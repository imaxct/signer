package imaxct.signer.bean;

import org.apache.commons.collections.map.HashedMap;

import java.io.Serializable;
import java.util.Map;

/**
 * Error msg for ModelAndView
 * Created by maxct on 2016/8/16.
 */
public class ErrorMsg implements Serializable {
    private Map<String, Object>map = new HashedMap();
    public ErrorMsg() {
    }

    public ErrorMsg(String title, String errmsg, boolean redirect, String url) {
        map.put("title", title);
        map.put("errmsg", errmsg);
        map.put("redirect", redirect);
        map.put("url", url);
    }

    public Map<String, Object> getMap(){
        return map;
    }

}
