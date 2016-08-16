package imaxct.signer.bean;

/**
 * Sign parameters bean
 * Created by maxct on 2016/8/11.
 */
public class SignParams extends BaseParams{
    private String tbs;
    private int fid;
    private String kw;

    public SignParams(String BDUSS, String tbs, int fid, String kw){
        super(BDUSS);
        this.tbs = tbs;
        this.fid = fid;
        this.kw = kw;
        addParams();
        setSIGN();
    }

    @Override
    void addParams() {
        map.put("fid", ""+fid);
        map.put("kw", kw);
        map.put("tbs", tbs);
    }
}
