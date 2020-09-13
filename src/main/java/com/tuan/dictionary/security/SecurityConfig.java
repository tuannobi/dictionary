package com.tuan.dictionary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tuan.dictionary.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private UserDetailsService userDetailsService;
//    private PasswordEncoder passwordEncoder;
//    private JwtRequestFilter jwtRequestFilter;
//    
    @Bean
    public AuthenticationManager AuthenticationManager() throws Exception {
    	return super.authenticationManagerBean();
    }

//    @Autowired
//    public  SecurityConfig(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder,JwtRequestFilter jwtRequestFilter){
//        this.userDetailsService=userDetailsService;
//        this.passwordEncoder=passwordEncoder;
//        this.jwtRequestFilter=jwtRequestFilter;
//    }
    
    @Configuration
    @Order(1)
    public static class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter
    {
        private UserDetailsService userDetailsService;
        private PasswordEncoder passwordEncoder;
        private JwtRequestFilter jwtRequestFilter;
        
      @Autowired
      public  BasicAuthSecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder,JwtRequestFilter jwtRequestFilter){
          this.userDetailsService=userDetailsService;
          this.passwordEncoder=passwordEncoder;
          this.jwtRequestFilter=jwtRequestFilter;
      }
      
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	    }
                

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.csrf().disable()
                .antMatcher("/rest/**")
                .authorizeRequests()
//                	.antMatchers(HttpMethod.POST,"/authenticate").permitAll()
                	.antMatchers("/rest/**").authenticated()
            .and().httpBasic().disable();

            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }


    @Configuration
    @Order(2)
    public static class LoginFormSecurityConfig extends WebSecurityConfigurerAdapter
    {
    	private UserDetailsService userDetailsService;
        private PasswordEncoder passwordEncoder;
        
      @Autowired
      public  LoginFormSecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
          this.userDetailsService=userDetailsService;
          this.passwordEncoder=passwordEncoder;
      }
      
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	    }

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
        	http.antMatcher("/**").authorizeRequests()
          .antMatchers("/resources/**").permitAll()
          .antMatchers(HttpMethod.POST,"/authenticate").permitAll()
          .antMatchers("/web/register").permitAll()
          .antMatchers("/web/admin/**").hasRole("ADMIN")
          .antMatchers("/**").authenticated()
//          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/web/login")
          .permitAll()
          .usernameParameter("email")
          .passwordParameter("password")
          .loginProcessingUrl("/web/login")
          .defaultSuccessUrl("/web/admin/collections")
          .failureUrl("/web/login?error")
          .and()
          .logout()
          .logoutUrl("/web/logout")
          .logoutSuccessUrl("/login")
          .and()
          .exceptionHandling()
          .accessDeniedPage("/web/error/403").and().csrf().disable();
            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
        }
    }
    
}
