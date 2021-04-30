package com.helog.generator.config;


import com.helog.generator.interceptor.CommonInterceptor;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    public WebMvcConfig() {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"classpath:/static/"});
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/static/**"});
        super.addInterceptors(registry);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
                factory.addErrorPages(error404Page);
                factory.addErrorPages(error403Page);
                factory.addErrorPages(error500Page);
            }
        };
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
//                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
//                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
//                container.addErrorPages(new org.springframework.boot.web.servlet.ErrorPage[]{error404Page});
//                container.addErrorPages(new org.springframework.boot.web.servlet.ErrorPage[]{error403Page});
//                container.addErrorPages(new org.springframework.boot.web.servlet.ErrorPage[]{error500Page});
//            }
//        };
//    }
}