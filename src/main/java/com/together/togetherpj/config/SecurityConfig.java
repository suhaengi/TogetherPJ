package com.together.togetherpj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

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
        .mvcMatchers("/", "/member/login", "/member/register", "/member/othersProfile",
                "/developer", "/recruit/view", "/board/recruitBoard/**", "/board/search", "/recruit/apply").permitAll()  // permit all templates
        .mvcMatchers("/member/**", "/recruit/write-form").hasRole("MEMBER")
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
