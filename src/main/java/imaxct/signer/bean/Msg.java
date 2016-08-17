package imaxct.signer.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * CodeMessage
 * Created by maxct on 2016/8/11.
 */
public class Msg implements Serializable{
    public Map<String, Object> msg = null;

    public Msg(){
        msg = new HashMap<String, Object>();
    }

    public void put(String key, Object obj){
        msg.put(key, obj);
    }
}
