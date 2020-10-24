package com.mazharulsabbir.restapispring.data.model.donor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "donors")
data class Donor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val uid: Int?,
        val username: String? = null,
        val email: String? = null,
        val blood_group: String? = null,
        val mobile: String? = null,
        val address: String? = null
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