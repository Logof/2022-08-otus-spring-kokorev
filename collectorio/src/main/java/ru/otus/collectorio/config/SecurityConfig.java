package ru.otus.collectorio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.otus.collectorio.service.impl.JWTService;

@EnableWebSecurity
public class SecurityConfig  {
    @Autowired
    JWTService jwtService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable().csrf().disable()
                    .authorizeRequests().antMatchers("/api/auth/register","/api/auth/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().apply(new JwtConfigure(jwtService));
            //http.addFilterBefore(new JwtFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
                http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) ->
                {
                    //TODO payload response.error
                    /*response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    try {
                        response.getWriter().write(new JSONObject()
                                .put("timestamp", LocalDateTime.now())
                                .put("message", "Access denied")
                                .toString());
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }*/
                });
            return  http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
