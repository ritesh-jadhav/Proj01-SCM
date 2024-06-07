package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.boot.services.SecurityCustomUserServiceImpl;

@Configuration
public class SecurityConfig {
    // user creation and in memory login (not for prod)

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user = User
    // .withDefaultPasswordEncoder()
    // .username("ritesh")
    // .password("pass@123")
    // .build();

    // var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    // return InMemoryUserDetailsManager;
    // }
    @Autowired
    private SecurityCustomUserServiceImpl userService;

    // password encoder obj
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider DaoAuthenticationProvider = new DaoAuthenticationProvider();
        // user details service
        DaoAuthenticationProvider.setUserDetailsService(userService);
        // password encoder
        DaoAuthenticationProvider.setPasswordEncoder(encoder());
        return DaoAuthenticationProvider;

    }

    // security filter chain for protecting application routes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configer url
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll(); ->
            // expose included routes
            // protects few eoutes
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form login
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
