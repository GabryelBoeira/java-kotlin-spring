package com.gabryel.mercadolivro.secutity

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtUtils {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Long): String {
       return Jwts.builder()
            .issuedAt(Date())
            .subject(id.toString())
            .expiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(Keys.hmacShaKeyFor(secret?.toByteArray()))
            .compact()
    }
}