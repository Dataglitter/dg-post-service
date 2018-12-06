package io.dataglitter.PostService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static io.dataglitter.PostService.security.SecurityConstants.*;

/**
 * Created by reddys on 14/12/2017.
 */

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, HEALTH_URL).permitAll()
                .antMatchers(HttpMethod.GET, POST_GET_URLS).permitAll()
                .antMatchers(HttpMethod.PUT, POST_PUT_LIKED).permitAll()
                .antMatchers(HttpMethod.PUT, POST_PUT_SHARED).permitAll()
                .antMatchers(HttpMethod.PUT, POST_PUT_VIEWED).permitAll()
                .antMatchers(HttpMethod.GET, USER_GET_URLS).permitAll()
                .antMatchers(HttpMethod.GET, INDEX).permitAll()
                .antMatchers(HttpMethod.GET, CSR_TXT).permitAll()
                .antMatchers(HttpMethod.POST, USER_GET_ALL_BY_IDS).permitAll()
                .antMatchers(HttpMethod.POST, USER_GET_CHECK_BY_EMAIL).permitAll()
                .antMatchers(HttpMethod.POST, USER_GET_BY_EMAIL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
