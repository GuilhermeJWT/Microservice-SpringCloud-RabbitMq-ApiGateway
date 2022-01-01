package br.com.systemsgs.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTTokenFilter extends GenericFilterBean {

    private final JWTTokenProvider jwttokenProvider;

    @Autowired
    public JWTTokenFilter(JWTTokenProvider jwttokenProvider) {
        this.jwttokenProvider = jwttokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       String token = jwttokenProvider.resolveToken((HttpServletRequest) servletRequest);

       if(token != null && jwttokenProvider.validateToken(token)){
           Authentication auth = jwttokenProvider.getAuthentication(token);

           if(auth != null){
               SecurityContextHolder.getContext().setAuthentication(auth);
           }
       }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
