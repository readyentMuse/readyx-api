package com.readyent.readyx.filter;

import com.readyent.readyx.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader("Authorization");
//        log.debug("토큰: {}", token);
//        System.out.println(String.format("토큰: %s", token));
        if (token != null && !token.isEmpty() && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtTokenUtil.getSecretKey())
                        .parseClaimsJws(token)
                        .getBody();

                String phoneNumber = claims.getSubject(); // JWT의 subject에서 phoneNumber를 가져옴

                // 인증 토큰 생성 및 권한 부여 (ROLE_USER)
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        phoneNumber, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );

                // 인증 객체를 SecurityContext에 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.debug("authentication {}", authentication);
                System.out.println(String.format("authentication %s", authentication));
            } catch (Exception e) {
//                log.debug("authentication {}", e.getMessage());
                System.out.println(String.format("에러 : %s", e.getMessage()));
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // JWT 파싱 실패 시 403 응답
                return;
            }
        }
        filterChain.doFilter(request, response); // 다음 필터로 요청 전달
    }
}

