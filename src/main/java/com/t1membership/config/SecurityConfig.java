package com.t1membership.config;

import com.anpetna.auth.service.BlacklistServiceImpl;
import com.anpetna.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import jakarta.servlet.http.HttpServletResponse;                  // ★추가: 401/403 직접 전송용
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)                      // 메서드 단위 @PreAuthorize 등 사용
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtProvider jwtProvider,                               // JWT 파서/검증기
            BlacklistServiceImpl blacklistService,                 // Access 블랙리스트 조회 서비스
            CorsConfigurationSource corsConfigurationSource,        // CORS 설정 빈 (주입만 받음)
            MemberRepository memberRepository) throws Exception {
        http
                // ===== CORS / CSRF / 세션 전략 =====
                .cors(c -> c.configurationSource(corsConfigurationSource)) // CORS 활성화 + 아래 Bean 적용
                .csrf(csrf -> csrf.disable())                                // JWT 사용 → CSRF 비활성
                .formLogin(f -> f.disable())                                 // 폼 로그인 미사용
                .httpBasic(b -> b.disable())                                 // 기본 인증 미사용
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 없음

                // ===== 인가 규칙 =====
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ★추가: 프리플라이트 허용

                        // --- 기존 허용 경로 유지 ---
                        .requestMatchers("/jwt/**").permitAll()                  // 토큰 발급/재발급 엔드포인트
                        .requestMatchers(HttpMethod.POST, "/member/login").permitAll() // 기존 로그인 경로
//                        .requestMatchers("/member/join").permitAll()    // 회원가입
//                        .requestMatchers("/", "/signup", "/api/v1/**").permitAll()        // 기존 공개 경로들

                        // --- ★추가: anpetna 네임스페이스 로그인/회원가입 허용 ---
                        .requestMatchers("/member/login",
                                "/member/join").permitAll()  // 새 프론트 경로 허용

                        // === 역할 기반 인가 ===
                        .requestMatchers("/member/readOne","/member/readAll").hasRole("ADMIN")        // 관리자 전용
                        .requestMatchers("/member/my_page/","member/modify").hasAnyRole("USER") // 로그인 유저 전용

                        // --- 그 외 모든 요청은 인증 필요 ---
                        .anyRequest().authenticated()
                )

                // ===== 예외 응답 통일 =====
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((req, res, ex) ->              // 인증 실패(미인증) → 401
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((req, res, ex) ->                   // 인가 실패(권한없음) → 403
                                res.sendError(HttpServletResponse.SC_FORBIDDEN))
                )

                // ===== JWT 필터 =====
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtProvider, blacklistService,memberRepository), // 커스텀 JWT 인증 필터
                        UsernamePasswordAuthenticationFilter.class                  // UsernamePasswordAuthenticationFilter 앞에 둠
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();     // 스프링 기본 AuthenticationManager 노출
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();                                // Bcrypt 사용
    }

    /**
     * Security가 참조할 CORS 설정(Origin/Headers/Methods).
     * 프론트(React)와 연동할 도메인/포트만 명시하고, 자격증명(쿠키/인증헤더) 허용.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var cfg = new org.springframework.web.cors.CorsConfiguration();

        // === Origin 허용 목록 ===
        // 기존 IP 유지 + ★추가: localhost:3000 (개발 환경)
        cfg.setAllowedOrigins(java.util.List.of(
                "http://192.168.0.160:3000",
                "http://localhost:3000"                                  // ★추가
        ));

        // === 허용 메서드 ===
        cfg.setAllowedMethods(java.util.List.of(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        // === 허용 헤더 ===
        // 기존처럼 전체(*) 허용 (Authorization 포함)
        cfg.setAllowedHeaders(java.util.List.of("*"));

        // === 자격 증명 허용 ===
        // 쿠키/Authorization 헤더를 프론트에서 보낼 수 있게 함 (axios withCredentials 등)
        cfg.setAllowCredentials(true);

        // === 노출(Exposed) 헤더 ===
        // 브라우저에서 읽을 수 있게 허용할 응답 헤더들
        cfg.setExposedHeaders(java.util.List.of(
                "Authorization"                                           // ★추가: 토큰을 응답헤더로 내려줄 때 프론트에서 읽기 가능
        ));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);                     // 모든 경로에 위 CORS 설정 적용
        return source;
    }
}
