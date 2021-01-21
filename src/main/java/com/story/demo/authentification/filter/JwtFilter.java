package com.story.demo.authentification.filter;

import com.story.demo.authentification.provider.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    private static final String authorizationHeaderName = "Authorization";
    private static final String authorizationHeaderPrefix = "Bearer ";
    private static final int getAuthorizationHeaderStartIdx = 7;

    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String token = resolveAuthorizationHeader(request);

        if (token != null && JwtProvider.validateToken(token)) {
            Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveAuthorizationHeader(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String headerContent = httpServletRequest.getHeader(authorizationHeaderName);

        if (headerContent != null && headerContent.startsWith(authorizationHeaderPrefix)) {
            return headerContent.substring(getAuthorizationHeaderStartIdx);
        }

        return null;
    }

}
