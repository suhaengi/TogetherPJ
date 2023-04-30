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
        .defaultSuccessUrl("/")
        .usernameParameter("email")
//        .passwordParameter("pw")
        .failureUrl("/member/login/error")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        .logoutSuccessUrl("/");

    http.authorizeHttpRequests()
        .antMatchers("/css/**", "/assets/**", "/forms/**", "/images/**").permitAll()  // static
        .antMatchers("/", "/recruit/**", "/member/**").permitAll()  // permit all templates
        .antMatchers("/user/mypage").hasRole("MEMBER")
//        .antMatchers("/member/**", "/recruit").hasRole("MEMBER")  // permit user templates
        .antMatchers("/admin").hasRole("ADMIN")  // permit admin templates
        .anyRequest().authenticated();
//
//    http.exceptionHandling()
//        .authenticationEntryPoint(new CustomEntryPoint());
//
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
