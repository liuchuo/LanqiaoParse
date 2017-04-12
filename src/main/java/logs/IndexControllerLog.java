package logs;

import controller.IndexController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class IndexControllerLog {
    Logger logger = LoggerFactory.getLogger(IndexController.class.getSimpleName());

    @Pointcut("execution(* controller.IndexController.doGet(..))")
    public void doGetMethod() {}

    @Around("doGetMethod()")
    public void execDoGet(ProceedingJoinPoint jp) {
        try {
            logger.info("execute do get method");
            jp.proceed();
            logger.info("executed to get method");
        } catch (Throwable e) {
            logger.error("Error to execute do get method");
        }
    }
}
