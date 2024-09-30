package com.readyent.readyx.config;

import com.readyent.readyx.filter.JwtAuthenticationFilter;
import com.readyent.readyx.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SecurityConfig {
    private final JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/**"
                                , "/api/swagger-ui/**"
                                , "/v3/api-docs/**"
                        )
                        .permitAll()
                        .requestMatchers("/api/v1/**").hasRole("USER") // /member/** 경로는 ROLE_USER 권한이 필요
                        .anyRequest().authenticated()
                );

        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

