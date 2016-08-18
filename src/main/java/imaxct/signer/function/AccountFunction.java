package imaxct.signer.function;

import imaxct.signer.domain.Account;
import imaxct.signer.domain.Tieba;
import imaxct.signer.misc.Lib;
import imaxct.signer.misc.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Account functions
 * Created by maxct on 2016/8/11.
 */
public class AccountFunction {

    private static Logger logger = Logger.getLogger(AccountFunction.class);

    public Account getAccountInfo(Account account){
        Account newAccount = account;
        InputStream inputStream = Lib.getStream("http://tieba.baidu.com/f/user/json_userinfo",
                "BDUSS=" + newAccount.getCookie(), Reference.USERAGENT_WEB, 0, null);
        String res = Lib.streamToString(inputStream);
        if (res!=null){
            JSONObject object = JSONObject.fromObject(res);
            if (object.getInt("no")==0){
                JSONObject data = object.getJSONObject("data");
                newAccount.setName(data.getString("user_name_show"));
                newAccount.setOpenUid(data.getString("open_uid"));
            }else{
                logger.error("fetch Json_userinfo error, err:" + object.getString("err"));
                return null;
            }
        }else
            return null;
        inputStream = Lib.getStream("http://tieba.baidu.com/home/get/panel?ie=utf-8&un=" + Lib.urlEncode(newAccount.getName()),
                null, Reference.USERAGENT_WEB, 0, null);
        res = Lib.streamToString(inputStream);
        if (res != null){
            JSONObject object = JSONObject.fromObject(res);
            if (object.getInt("no")==0){
                int tid = object.getJSONObject("data").getInt("id");
                newAccount.setUid(tid);
            }else{
                logger.error("get user info error, " + object.getString("error"));
                return null;
            }
        }else
            return null;
        return newAccount;
    }

    public List<Tieba> getLikedTieba(Account account){
        List<Tieba>list = new ArrayList<>();
        InputStream inputStream = Lib.getStream("http://tieba.baidu.com/p/getLikeForum?uid=" + account.getUid(),
                "BDUSS=" + account.getCookie(), Reference.USERAGENT_WEB, 0, null);
        String res = Lib.streamToString(inputStream);
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
