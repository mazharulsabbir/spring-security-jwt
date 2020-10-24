package com.mazharulsabbir.restapispring.data.controller

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
    fun createDonor(donor: Donor): Donor {
        println(donor)
        return repository.save(donor)
    }
}