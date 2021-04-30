package com.helog.generator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Controller
public class ErrorPagesController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorPagesController.class);

    public ErrorPagesController() {
    }

    @RequestMapping({"/404"})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "/404";
    }

    @RequestMapping({"/403"})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden() {
        return "/403";
    }

    @RequestMapping({"/500"})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError() {
        return "/500";
    }
}
