package br.com.avaliafit_api.config

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil
): OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request:        HttpServletRequest,
        response:       HttpServletResponse,
        filterChain:    FilterChain
    ) {

        val header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!header.isNullOrBlank() && header.startsWith("Bearer ")) {
            val token = header.substring(7);

            try {
                val id          = jwtUtil.getId(token);
                val usuarioRole = jwtUtil.getUsuarioRole(token);

                val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_${usuarioRole}"));
                val auth: Authentication                = UsernamePasswordAuthenticationToken(id, null, authorities);

                SecurityContextHolder.getContext().authentication = auth;
            } catch (e: ExpiredJwtException) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED;
                response.writer.write("Token expirado. Faça login novamente");
                return;
            } catch (e: JwtException) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Token inválido.")
                return;
            } catch (e: IllegalArgumentException) {

                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Token inválido.")
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}