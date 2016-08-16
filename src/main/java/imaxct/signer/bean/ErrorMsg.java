package imaxct.signer.bean;

import java.io.Serializable;

/**
 * Error msg for ModelAndView
 * Created by maxct on 2016/8/16.
 */
public class ErrorMsg implements Serializable {
    private String title;
    private String errmsg;
    private boolean redirect;
    private String url;

    public ErrorMsg() {
    }

    public ErrorMsg(String title, String errmsg) {
        this.title = title;
        this.errmsg = errmsg;
    }

    public ErrorMsg(String title, String errmsg, boolean redirect, String url) {
        this.title = title;
        this.errmsg = errmsg;
        this.redirect = redirect;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public String getUrl() {
        return url;
    }

}
