package com.security.configuration;

import com.security.jwt.JwtTokenVerifier;
import com.security.jwt.JwtUserNameAuthenticationFilter;
import com.security.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Created by CAPTAN GHOURI on 31/03/2020.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  UserAccountService userAccountService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()*/
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserNameAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(),JwtUserNameAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userAccountService);
        return daoAuthenticationProvider;
    }
}
