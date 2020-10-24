package com.mazharulsabbir.restapispring.data.model.donor

import javax.persistence.*

@Entity(name = "donors")
data class Donor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val uid: Int?,

        @Column(unique = true, nullable = false)
        val username: String?,

        @Column(nullable = false)
        val email: String?,

        @Column(nullable = false)
        val blood_group: String?,

        @Column(nullable = false)
        val mobile: String?,

        @Column(nullable = false)
        val address: String?
) {
    constructor() : this(
            null,
            null,
            null,
            null,
            null,
            null
    )
}