package com.mazharulsabbir.restapispring.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
object MyUserDetailService : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val password = "\$2y\$10\$6QW1ATLIGw.VQ6n/YdmxT.bjEYGOVm7UD9Z08aR/UXVHTVH6Y26bi" // encrypt of admin using BCrypt
        return User("admin", password, mutableListOf())
    }
}