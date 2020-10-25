package com.mazharulsabbir.restapispring.data.model.auth

import javax.persistence.*

@Entity(name = "credentials")
data class AuthCredential(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        @Column(unique = true,nullable = false)
        val username: String?,
        @Column(nullable = false)
        var password: String?,
        val role: String?
) {
    constructor() : this(null, null, null, null)
}