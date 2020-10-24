package com.mazharulsabbir.restapispring.data.model.log

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "log")
data class Log(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        val timestamp: Timestamp? = null,
        val message: String? = null
) {
    constructor() : this(null, null, null)
}