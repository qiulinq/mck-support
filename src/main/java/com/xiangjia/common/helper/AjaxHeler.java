package com.xiangjia.common.helper;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.xiangjia.base.dto.JSonDtoModel;

public class AjaxHeler {

    private final static Logger logger = Logger.getLogger(AjaxHeler.class);

    public static void ajaxOut(HttpServletResponse response, JSonDtoModel dto) {
        String outString = dto.toJSonString();
        try {
            response.setContentType("text/html; charset=\"UTF-8\"");
            PrintWriter out = response.getWriter();
            out.print(outString);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.info("Ajax输出异常 : " + outString);
        }
    }

    public static void ajaxOut(HttpServletResponse response, String outString) {

        try {
            response.setContentType("text/html; charset=\"UTF-8\"");
            PrintWriter out = response.getWriter();
            out.print(outString);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.info("Ajax输出异常 : " + outString);
        }
    }
}
