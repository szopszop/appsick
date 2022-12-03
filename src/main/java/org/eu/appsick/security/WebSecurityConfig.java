package org.eu.appsick.security;


import org.eu.appsick.security.jwt.AuthEntryPointJwt;
import org.eu.appsick.security.jwt.AuthTokenFilter;
import org.eu.appsick.security.services.UserDetailsServiceImpl;
import org.eu.appsick.user.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final String ADMIN = Role.ADMIN.toString();
    private static final String PATIENT = Role.PATIENT.toString();
    private static final String DOCTOR = Role.DOCTOR.toString();

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
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.PATCH, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.PUT, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.DELETE, "/api/visit/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.POST, "/api/visit").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/clinic/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/patient/**").hasAnyAuthority(ADMIN, PATIENT, DOCTOR)
                .antMatchers(HttpMethod.GET, "/api/doctor/**").hasAnyAuthority(ADMIN, DOCTOR)
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/**").permitAll().anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider(userDetailsService));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
