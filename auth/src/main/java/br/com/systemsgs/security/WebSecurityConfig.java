package br.com.systemsgs.security;

import br.com.systemsgs.jwt.JWTTokenConfigure;
import br.com.systemsgs.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public WebSecurityConfig(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
             httpBasic().disable()
             .csrf().disable()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
             .authorizeRequests()
             .antMatchers("/api/v1/login").permitAll()
             .anyRequest().authenticated()
                .and()
             .apply(new JWTTokenConfigure(jwtTokenProvider));
    }

    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

}
