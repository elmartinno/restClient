package com.example.democlient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/client/turnovers").permitAll()
                .antMatchers("/client/accounts").permitAll()
                .antMatchers("/client/addAccount").permitAll()
                .antMatchers("/client/addTurnover").permitAll()
                .antMatchers("/client/findByIban").permitAll()
                .antMatchers("/client/removeTurnovers").permitAll()
                .antMatchers("/client/removeAccount").permitAll()
                .antMatchers("/client/findTurnoversByIban").permitAll()
                .antMatchers("/client/updateTurnovers").permitAll()
                .antMatchers("/client/findAccountByIban").permitAll();
    }
}
