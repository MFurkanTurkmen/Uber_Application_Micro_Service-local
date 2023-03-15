package com.furkan.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthSecurityConfig {
    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                //.antMatchers("/swagger-ui/**").authenticated() // swagger 'a ilk girisi engelledik.
               // .antMatchers("/auth/gatewaymesaj").authenticated() // apiden giden mesaj istegini engelledik
                //.antMatchers("/swagger-ui/index.html#/auth-controller/**").authenticated() //gelenleri engelle get isteklerini engellemedi sor
                .anyRequest().permitAll();

//                .antMatchers("/swagger-ui/**").permitAll() // çok değişik yerlere girdik
//                .anyRequest().authenticated(); // swagger'ın kendi deneme yerine gittik any request ve ant matchersi yer degiştirince patladı
        httpSecurity.formLogin();
        httpSecurity.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
