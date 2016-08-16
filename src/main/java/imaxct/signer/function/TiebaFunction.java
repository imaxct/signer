package imaxct.signer.function;

import imaxct.signer.bean.SignParams;
import imaxct.signer.domain.Account;
import imaxct.signer.domain.Tieba;
import imaxct.signer.misc.Lib;
import imaxct.signer.misc.Reference;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * Functions of Tieba
 * Created by maxct on 2016/8/11.
 */
public class TiebaFunction {
    private static Logger logger = Logger.getLogger(TiebaFunction.class);

    public int sign(Tieba tieba){

        String tbs = getTbs(tieba.getAccount());
        SignParams params = new SignParams(tieba.getAccount().getCookie(), tbs,
                tieba.getFid(), tieba.getName());
        InputStream inputStream = Lib.postStream("http://c.tieba.baidu.com/c/c/forum/sign",
                params.toString(), "BDUSS=" + tieba.getAccount().getCookie(), Reference.USERAGENT_CLIENT, 0, null);
        String res = Lib.streamToString(inputStream);
        if (res != null){
            JSONObject object = JSONObject.fromObject(res);
            System.out.println(res);
            return object.getInt("error_code");
        }else{
            logger.error("sign error, returned string is null");
            return -1;
        }
    }

    public String getTbs(Account account){
        InputStream inputStream = Lib.getStream("http://tieba.baidu.com/dc/common/tbs",
                "BDUSS="+account.getCookie(), Reference.USERAGENT_WEB, 0, null);
        String res = Lib.streamToString(inputStream);
        if (res != null){
            JSONObject jsonObject = JSONObject.fromObject(res);
            return jsonObject.getString("tbs");
        }else{
            logger.error("get tbs error, returned string is null");
            return null;
        }
    }
}
