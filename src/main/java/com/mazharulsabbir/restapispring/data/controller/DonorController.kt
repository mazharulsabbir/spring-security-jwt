package com.mazharulsabbir.restapispring.data.controller

import com.mazharulsabbir.restapispring.data.model.ErrorResponse
import com.mazharulsabbir.restapispring.data.model.donor.Donor
import com.mazharulsabbir.restapispring.data.repository.DonorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class DonorController {
    @Autowired
    private lateinit var repository: DonorRepository

    @RequestMapping("/donors")
    fun getAllDonor(): Iterable<Donor> {
        return repository.findAll()
    }

    @PostMapping("/donor")
    fun createDonor(@RequestBody donor: Donor): Any {
        println(donor)
        return try {
            repository.save(donor)
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorResponse(true, "Failed to create account", e.toString())
        }
    }
}