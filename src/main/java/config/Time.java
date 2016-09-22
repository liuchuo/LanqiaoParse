package config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by JoyHwong on 9/22/16.
 * copyright @ 2016 All right reserved.
 * follow me on github https://github.com/JoyHwong
 */
@Aspect
public class Time {
    private long start, end;

    @Before("execution(* servlet.DownloadServlet.doGet(...))")
    public void statisticStart() {
        start = System.currentTimeMillis();
    }

    @After("execution(* servlet.DownloadServlet.doGet(...))")
    public void statisticEnd() {
        end = System.currentTimeMillis();

        System.out.println("Speed time: " + (end - start) + "ms");
    }
}
