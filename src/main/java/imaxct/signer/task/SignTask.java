package imaxct.signer.task;

import imaxct.signer.dao.TiebaDao;
import imaxct.signer.domain.Tieba;
import imaxct.signer.function.TiebaFunction;
import imaxct.signer.misc.Lib;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * Sign Cron task
 * Created by maxct on 2016/8/17.
 */
public class SignTask extends QuartzJobBean{

    private static final Logger logger = Logger.getLogger(SignTask.class);

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

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.run();
    }
}
