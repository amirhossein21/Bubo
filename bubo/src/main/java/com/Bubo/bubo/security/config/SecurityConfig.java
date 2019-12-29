package com.Bubo.bubo.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * this class config basic security
 *
 * @author Farzan & amirhosein
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * This method logs every request
     * Using basic security
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     *This method defines the username
     *  and password for logging in.
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
             //   .and().withUser("sina").password("@sina").roles("USER");
    }


}