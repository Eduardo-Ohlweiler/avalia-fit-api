package br.com.avaliafit_api.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expiration: Long
) {

    private val key: Key = Keys.hmacShaKeyFor(secret.toByteArray())

    // gerar token com lista de roles
    fun gerar(id: Long, roles: List<String>): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .claim("roles", roles)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    // pegar o id
    fun getId(token: String): Long {
        val claims = getClaims(token)
        return claims.subject.toLong()
    }

    // pegar roles
    fun getRoles(token: String): List<String> {
        val claims = getClaims(token)
        @Suppress("UNCHECKED_CAST")
        return claims["roles"] as List<String>
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}