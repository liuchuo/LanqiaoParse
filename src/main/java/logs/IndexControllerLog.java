package logs;

import controller.IndexController;
import controller.Utils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


@Aspect
@Component
public class IndexControllerLog {
    Logger logger = LoggerFactory.getLogger(IndexController.class.getSimpleName());

    @Pointcut("execution(* controller.IndexController.doGet(..))")
    public void doGetMethod() {}

    @Around("doGetMethod() && args(request)")
    public void execDoGet(ProceedingJoinPoint jp, HttpServletRequest request) {
        try {
            logger.info("execute do get method");

            jp.proceed();
            logger.info("executed to get method");
        } catch (Throwable e) {
            logger.error("Error to execute do get method");
        }
    }

    @Pointcut("execution(* controller.IndexController.getSchoolSet(..))")
    public void getSchoolSetMethod() {}

    @Around("getSchoolSetMethod()")
    public void execGetSchoolSet(ProceedingJoinPoint jp) {
        try {
            logger.info("execute to get school set method");
            jp.proceed();
            logger.info("executed to get school set method");
        } catch (Throwable throwable) {
            logger.error("failed to executed get school set method");
        }
    }
}
