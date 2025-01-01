package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.exception.AuthenticationException
import com.gabryel.mercadolivro.repository.CustomerRepository
import com.gabryel.mercadolivro.secutity.CustomUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String?): UserDetails {
        return CustomUserDetails(
            customerRepository
                .findById(id!!.toLong())
                .orElseThrow { throw AuthenticationException("Usuário não encontrado", "user.not.found") }
        )
    }

}