package com.mazharulsabbir.restapispring.data.repository

import com.mazharulsabbir.restapispring.data.model.auth.AuthCredential
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepo : CrudRepository<AuthCredential, Int> {
    fun findByUsernameAndPassword(username: String?, password: String?): AuthCredential
    fun findByUsername(username: String?): AuthCredential
}