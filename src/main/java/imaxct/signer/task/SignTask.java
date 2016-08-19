package imaxct.signer.task;

import imaxct.signer.dao.TiebaDao;
import imaxct.signer.domain.Tieba;
import imaxct.signer.function.TiebaFunction;
import imaxct.signer.misc.Lib;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Sign Cron task
 * Created by maxct on 2016/8/17.
 */
@Component
public class SignTask {

    private static final Logger logger = Logger.getLogger(SignTask.class);

    @Scheduled(cron = "0 0/10 1-23 * * ?")
    public void run(){
        logger.error("sign task run!");
        TiebaDao tiebaDao = new TiebaDao();
        TiebaFunction function = new TiebaFunction();
        List<Tieba> list = tiebaDao.getUnsignedTieba(Lib.today());
        if (list != null && !list.isEmpty()){
            for (Tieba t : list){
                int code = function.sign(t);
                t.setLastSign(Lib.today());
                t.setErrcode(code);
                tiebaDao.merge(t);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
