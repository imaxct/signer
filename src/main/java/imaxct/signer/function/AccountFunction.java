package imaxct.signer.function;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.Tieba;
import imaxct.signer.misc.Lib;
import imaxct.signer.misc.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Account functions
 * Created by maxct on 2016/8/11.
 */
public class AccountFunction {

    private static Logger logger = Logger.getLogger(AccountFunction.class);

    public Account getAccountInfo(Account account){
        if (account==null)
            return null;
        InputStream inputStream = Lib.getStream("http://tieba.baidu.com/f/user/json_userinfo",
                "BDUSS=" + account.getCookie(), Reference.USERAGENT_WEB, 0, null);
        String res;
        try {
            res = new String(Lib.streamToString(inputStream, "gb2312").getBytes("gb2312"), "gb2312");
            res = new String(res.getBytes("utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("encoding change failed");
            return null;
        }
        String url_name;
        if (res!=null){
            JSONObject object = JSONObject.fromObject(res);
            if (object.getInt("no")==0){
                JSONObject data = object.getJSONObject("data");
                account.setName(data.getString("user_name_show"));
                account.setOpenUid(data.getString("open_uid"));
                url_name = data.getString("user_name_url");
            }else{
                logger.error("fetch Json_userinfo error, err:" + object.getString("err"));
                return null;
            }
        }else
            return null;
        inputStream = Lib.getStream("http://tieba.baidu.com/home/get/panel?ie=utf-8&un=" + url_name,
                "BDUSS=" + account.getCookie(), Reference.USERAGENT_WEB, 0, null);
        res = Lib.streamToString(inputStream, "utf-8");
        if (res != null){
            JSONObject object = JSONObject.fromObject(res);
            if (object.getInt("no")==0){
                int tid = object.getJSONObject("data").getInt("id");
                account.setUid(tid);
            }else{
                logger.error("get user info error, " + object.getString("error"));
                return null;
            }
        }else
            return null;
        return account;
    }

    public List<Tieba> getLikedTieba(Account account){
        List<Tieba>list = new ArrayList<>();
        InputStream inputStream = Lib.getStream("http://tieba.baidu.com/p/getLikeForum?uid=" + account.getUid(),
                "BDUSS=" + account.getCookie(), Reference.USERAGENT_WEB, 0, null);
        String res = Lib.streamToString(inputStream, "utf-8");
        if (res!=null){
            JSONObject object = JSONObject.fromObject(res);
            if (object.getInt("errno") == 0){
                if (object.containsKey("data")) {
                    JSONObject data = object.getJSONObject("data");
                    if (data.containsKey("info")){
                        JSONArray info = data.getJSONArray("info");
                        for (int i=0; i<info.size(); ++i){
                            JSONObject forum = info.getJSONObject(i);
                            Tieba tieba = new Tieba();
                            tieba.setAccount(account);
                            tieba.setName(forum.getString("forum_name"));
                            tieba.setFid(forum.getInt("id"));
                            list.add(tieba);
                        }
                    }
                }else{
                    logger.error("getLikedTieba error, json doesn't have 'data'.");
                    return null;
                }
            }else{
                logger.error("getLikedTieba error, errmsg is " + object.getString("errmsg"));
                return null;
            }
        }else{
            logger.error("getLikedTieba error, returned string is null");
            return null;
        }
        return list;
    }

}
