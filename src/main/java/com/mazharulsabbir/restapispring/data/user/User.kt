package com.mazharulsabbir.restapispring.data.user

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
        @Id
        val uid: UUID,
        val username: String? = null,
        val email: String? = null,
        val blood_group: String? = null,
        val mobile: String? = null,
        val address: String? = null
) {
    constructor() : this(
            UUID.randomUUID(),
            null,
            null,
            null,
            null,
            null
    )
}