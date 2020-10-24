package com.mazharulsabbir.restapispring.data.repository

import com.mazharulsabbir.restapispring.data.model.auth.LoginCredential
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepo : CrudRepository<LoginCredential, Int> {
    fun findByUsernameAndPassword(username: String?, password: String?): LoginCredential
    fun findByUsername(username: String?): LoginCredential
}