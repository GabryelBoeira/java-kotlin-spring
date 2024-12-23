package com.gabryel.mercadolivro.secutity

import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.model.CustomerModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(val customerModel: CustomerModel) : UserDetails {

    val id: Long = customerModel.id!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
         return customerModel.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword(): String {
        return customerModel.password
    }

    override fun getUsername(): String {
        return customerModel.id.toString()
    }

    override fun isEnabled(): Boolean {
        return customerModel.status == CustomerStatus.ACTIVE
    }

}