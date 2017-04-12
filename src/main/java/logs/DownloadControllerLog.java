package logs;

import controller.DownloadController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class DownloadControllerLog {
    Logger logger = LoggerFactory.getLogger(DownloadController.class.getSimpleName());

    @Pointcut("execution(* controller.DownloadController.doGet(..))")
    public void doGetMethod() {}

    @Around("doGetMethod() && args(request)")
    public void execDoGet(ProceedingJoinPoint jp, HttpServletRequest request) {
        try {
            logger.info("execute do get method");
            String[] schoolNames = request.getParameterValues("schoolName");
            for (String a : schoolNames) {
                logger.debug(a);
            }
            jp.proceed();
            logger.info("executed do get method");
        } catch (Throwable e) {
            logger.error("failed to execute do get method");
        }
    }

}
