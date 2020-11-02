package com.sign_in.code.util;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Classname LoggerUtil
 * @Description TODO
 * @Date 2020/10/30 22:19
 * @Created by wgg
 */
@Component
public class LoggerUtil {
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
