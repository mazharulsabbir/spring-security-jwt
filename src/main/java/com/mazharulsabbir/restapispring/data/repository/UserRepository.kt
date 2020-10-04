package com.mazharulsabbir.restapispring.data.repository

import com.mazharulsabbir.restapispring.data.model.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, UUID> {
}