package com.mazharulsabbir.restapispring.data.repository

import com.mazharulsabbir.restapispring.data.model.donor.Donor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DonorRepository : CrudRepository<Donor, Int> {
}