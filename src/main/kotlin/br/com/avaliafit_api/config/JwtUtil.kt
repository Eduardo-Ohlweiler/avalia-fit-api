package br.com.avaliafit_api.config

import br.com.avaliafit_api.usuario.enums.UsuarioRole
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
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration}")
    private val expiration: Long
) {

    private val key: Key = Keys.hmacShaKeyFor(secret.toByteArray());

    fun gerar(id: Long, role: UsuarioRole): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .claim("usuario_role", role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    fun getId(token: String) : Long {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        return claims.subject.toLong();
    }

    fun getUsuarioRole(token: String) : UsuarioRole {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        return UsuarioRole.valueOf(claims.get("usuario_role", String::class.java));
    }
}