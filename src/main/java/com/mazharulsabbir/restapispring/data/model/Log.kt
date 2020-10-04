package com.mazharulsabbir.restapispring.data.model

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "log")
data class Log(@Id val id: Int? = null, val timestamp: Timestamp? = null, val message: String? = null) {
    constructor() : this(null, null, null)
}