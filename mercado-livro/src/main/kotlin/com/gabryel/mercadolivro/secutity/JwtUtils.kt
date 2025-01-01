package com.gabryel.mercadolivro.secutity

import com.gabryel.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtils {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null


    /**
     * Generates a JWT token for a given user ID.
     *
     * @param id the user ID for which the token is generated
     * @return a JWT token as a String
     */
    fun generateToken(id: Long): String {
        return Jwts.builder()
            .issuedAt(Date())
            .subject(id.toString())
            .expiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(Keys.hmacShaKeyFor(secret?.toByteArray()))
            .compact()
    }


    /**
     * Verifies if a given JWT token is invalid.
     *
     * A token is invalid if:
     *  1. The subject (user ID) is null;
     *  2. The expiration date is null;
     *  3. The expiration date is before the current date.
     *
     * @param token the token to be verified.
     * @return true if the token is invalid, false otherwise.
     */
    fun isInvalidToken(token: String): Boolean {
        val claims = getClaims(token)
        return when {
            claims.subject == null -> true
            claims.expiration == null -> true
            Date().after(claims.expiration) -> true
            else -> false
        }
    }

    /**
     * Returns the subject of the JWT token.
     *
     * @param token the JWT token from which the subject is extracted
     * @return the subject of the JWT token as a String
     */
    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

    /**
     * Parses the specified JWT token and retrieves the claims contained within it.
     *
     * @param token the JWT token to parse
     * @return the claims extracted from the token
     * @throws AuthenticationException if the token is invalid or cannot be parsed
     */
    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secret?.toByteArray()))
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            throw AuthenticationException("Token inválido", "invalid.token")
        }
    }

}