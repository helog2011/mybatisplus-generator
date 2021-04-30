package com.helog.generator.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.toString().replaceAll("\n", "<br/>"));
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("500");
        e.printStackTrace();
        return mav;
    }
}
