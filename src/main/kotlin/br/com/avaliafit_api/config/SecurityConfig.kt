package br.com.avaliafit_api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val accessDeniedHandler: CustomAccessDeniedHandler
) {

    // PASSWORD ENCODER
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // CONFIG PRINCIPAL
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}