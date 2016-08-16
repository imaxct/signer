package imaxct.signer.dao;

import org.hibernate.Session;

/**
 * Session Processor
 * Created by maxct on 2016/8/16.
 */
public interface SessionProcessor {
    public void process(Session session);
}
