package org.sopt.auth.api.config;

import lombok.RequiredArgsConstructor;
import org.sopt.auth.api.constants.AuthConstant;
import org.sopt.auth.api.filter.JwtAuthenticationEntryPoint;
import org.sopt.auth.api.filter.ExceptionHandlerFilter;
import org.sopt.auth.api.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                {
                    exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                });


        // 화이트리스트에 등록한 요청의 경우엔 허용, 나머지 API 들에 대해서는 인증 진행한다.
        http.authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers(AuthConstant.AUTH_WHITE_LIST).permitAll()
                            .anyRequest()
                            .authenticated();
                })
                // 필터 등록
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }
}

