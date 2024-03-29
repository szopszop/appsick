package org.eu.appsick.security;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eu.appsick.security.jwt.AuthEntryPointJwt;
import org.eu.appsick.security.jwt.AuthTokenFilter;
import org.eu.appsick.security.jwt.JwtUtils;
import org.eu.appsick.security.services.UserDetailsServiceImpl;
import org.eu.appsick.user.CustomOAuth2User;
import org.eu.appsick.user.CustomOAuth2UserService;
import org.eu.appsick.user.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CustomOAuth2UserService oAuth2UserService;

    @Autowired
    VisitAccessFilter visitAccessFilter;
    @Autowired
    JwtUtils jwtUtils;

    @Value("${appsick.frontend.url}")
    private String frontendUrl;

    private static final String ADMIN = Role.ADMIN.toString();
    private static final String PATIENT = Role.PATIENT.toString();
    private static final String DOCTOR = Role.DOCTOR.toString();
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(visitAccessFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.PATCH, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.PUT, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.DELETE, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.PUT, "/api/visit/status/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.POST, "/api/visit").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/clinic/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/patient/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/doctor/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers("/api/auth/**", "/oauth/**", "/login/oauth2/**").permitAll()
                .antMatchers("/**").permitAll().anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl(frontendUrl, true)
                    .userInfoEndpoint()
                    .userService(oAuth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oAuth2User = ((CustomOAuth2User) authentication.getPrincipal());
                        userDetailsService.processOAuthPostLogin(oAuth2User);
                        String jwtToken = jwtUtils.generateTokenFromUserEmail(oAuth2User.getEmail());
                        response.setHeader("Set-Cookie", "appsick=" + jwtToken + "; Path=/api; Max-Age=604800; Secure; SameSite=None");
                        response.sendRedirect(frontendUrl);
                    }
                });
        http.authenticationProvider(authenticationProvider(userDetailsService));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
