package com.helog.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Controller
@EnableWebMvc
@ServletComponentScan
@SpringBootApplication
public class MybatisplusGeneratorApplication {
    private static final Logger logger = LoggerFactory.getLogger(MybatisplusGeneratorApplication.class);


    @RequestMapping({"/"})
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/api")
    public String index() {
        return "redirect:/doc.html";
    }

    public static void main(String[] args) {
        logger.warn("#############启动mybatisplus-generator服务开始##############################");
        SpringApplication.run(MybatisplusGeneratorApplication.class, args);
        logger.warn("#############启动mybatisplus-generator服务完毕###########################");

    }
}
