package com.nghien.config;

import com.nghien.entities.Account;
import com.nghien.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Autowired
    BCryptPasswordEncoder pe;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(username -> {
           try {
               Account user = accountService.findById(username);
               String password =pe.encode(user.getPassword());
               String []roles = user.getAuthorities().stream()
                       .map(er -> er.getRole().getId())
                       .collect(Collectors.toList()).toArray(new String[0]);
               return User.withUsername(username).password(password).roles(roles).build();
           }catch (NoSuchElementException e){
               throw new UsernameNotFoundException("username not found"+username);
           }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        //ph??n quy???n s??? d???ng
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()//b???t bu???c ????ng nh???p
                .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")// ch??? 2 th???ng n??y ???????c vvaof admin
                .antMatchers("/rest/authorities").hasRole("DIRE") // ng?????c l???i
                .anyRequest().permitAll(); // c??n l???i ai c??ng ??c v??o
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success",false)
                .failureUrl("/security/login/error");
        http.rememberMe()
                .tokenValiditySeconds(86400);
        http.exceptionHandling()
                .accessDeniedPage("/security/unauthorized");
        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
