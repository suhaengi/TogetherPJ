package com.together.togetherpj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//    http.csrf().disable();
//
    http.formLogin()
        .loginPage("/member/login")
        .loginProcessingUrl("/member/login")
        .defaultSuccessUrl("/", true)
        .usernameParameter("email")
        .failureUrl("/member/login/error")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        .logoutSuccessUrl("/");


    http.authorizeHttpRequests()
        .mvcMatchers("/css/**", "/assets/**", "/forms/**", "/images/**").permitAll()  // static
        .mvcMatchers("/", "/recruit/**", "/member/login", "/member/register").permitAll()  // permit all templates
        .mvcMatchers("/member/**").hasRole("MEMBER")
        .mvcMatchers("/admin").hasRole("ADMIN")  // permit admin templates
        .anyRequest().authenticated();

    http.exceptionHandling()
        .authenticationEntryPoint(new CustomEntryPoint());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
