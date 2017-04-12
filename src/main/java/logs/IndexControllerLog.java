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
