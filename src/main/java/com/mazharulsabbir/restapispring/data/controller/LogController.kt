package com.mazharulsabbir.restapispring.data.controller

import com.mazharulsabbir.restapispring.data.model.log.Log
import com.mazharulsabbir.restapispring.data.repository.LogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LogController {
    @Autowired
    private lateinit var logRepository: LogRepository

    @GetMapping("/logs")
    @ResponseBody
    fun logs(): Iterable<Log> {
        return logRepository.findAll()
    }
}