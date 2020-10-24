package com.mazharulsabbir.restapispring.data.model.auth

import javax.persistence.*

@Entity(name = "credentials")
data class LoginCredential(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        @Column(unique = true,nullable = false)
        val username: String?,
        @Column(nullable = false)
        val password: String?,
        val role: String?
) {
    constructor() : this(1, null, null, null)
}