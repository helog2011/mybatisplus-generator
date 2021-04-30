package com.helog.generator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public LoginController() {
    }

    /**
     * 进入登录页面
     */
    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "welcome login repal.com");
        mv.setViewName("login");
        return mv;
    }
}