package imaxct.signer.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * CodeMessage
 * Created by maxct on 2016/8/11.
 */
public class Msg implements Serializable{
    Map<String, Object> map = null;

    public Msg(){
        map = new HashMap<String, Object>();
    }

    public void put(String key, Object obj){
        map.put(key, obj);
    }
}
