package com.mazharulsabbir.restapispring.data.repository

import com.mazharulsabbir.restapispring.data.model.Log
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : CrudRepository<Log, Int> {
}