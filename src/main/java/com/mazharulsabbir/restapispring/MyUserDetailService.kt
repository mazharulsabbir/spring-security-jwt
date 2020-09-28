package com.mazharulsabbir.restapispring

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
object MyUserDetailService : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return User("admin", "admin", mutableListOf())
    }
}