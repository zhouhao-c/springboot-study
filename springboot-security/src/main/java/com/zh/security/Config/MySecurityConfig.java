package com.zh.security.Config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1");

        //开启自动配置的登录功能，效果，如果没有登录，没有权限就会来到登录页面
        http.formLogin().passwordParameter("pwd").usernameParameter("user").loginPage("/userlogin");
        //1./login来到登录页面
        //2.重定向/login?error表示登录失败

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功后来到的页面
        //1.访问/logout表示用户注销，清空session
        //2.注销成功会返回 /login?logout页面
        //默认post形式的/login代表登录处理
        //一旦定制loginPage，那么loginPage的post请求就是登录

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.jdbcAuthentication()
        //auth.inMemoryAuthentication().withUser("lisi").password("123456").roles("VIP1");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1");

    }

}
