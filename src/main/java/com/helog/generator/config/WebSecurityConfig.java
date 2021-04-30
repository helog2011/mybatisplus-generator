package com.helog.generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public WebSecurityConfig() {
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity) ((HttpSecurity) ((FormLoginConfigurer) ((FormLoginConfigurer) ((FormLoginConfigurer) ((HttpSecurity) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) http.authorizeRequests().antMatchers(new String[]{"/static/**", "/login"})).permitAll().antMatchers(new String[]{"/admin/**"})).hasRole("ADMIN").anyRequest()).authenticated().and()).formLogin().loginPage("/login").defaultSuccessUrl("/index")).failureUrl("/login?error")).permitAll()).and()).logout().logoutSuccessUrl("/").permitAll().invalidateHttpSession(true).deleteCookies(new String[0]).and()).csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles(new String[]{"USER"});
    }
}
