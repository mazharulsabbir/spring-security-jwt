package com.mazharulsabbir.restapispring.data.api

import com.mazharulsabbir.restapispring.data.model.AuthenticationRequest
import com.mazharulsabbir.restapispring.data.model.AuthenticationResponse
import com.mazharulsabbir.restapispring.data.model.ErrorResponse
import com.mazharulsabbir.restapispring.data.model.Log
import com.mazharulsabbir.restapispring.data.model.user.User
import com.mazharulsabbir.restapispring.data.repository.LogRepository
import com.mazharulsabbir.restapispring.data.repository.UserRepository
import com.mazharulsabbir.restapispring.service.MyUserDetailService
import com.mazharulsabbir.restapispring.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtUtil

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var logRepository: LogRepository

    @RequestMapping(value = ["/auth"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*>? {
        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            authenticationRequest.username,
                            authenticationRequest.password
                    )
            )
        } catch (e: BadCredentialsException) {
            println("Login Failed: " + e.localizedMessage)
            return ResponseEntity.ok(ErrorResponse(true, e.message, e.localizedMessage));
        }

        val userDetails = MyUserDetailService.loadUserByUsername(authenticationRequest.username)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }

    @RequestMapping("/users")
    fun users(): Iterable<User> {
        return repository.findAll()
    }

    @GetMapping("/logs")
    @ResponseBody
    fun logs(): Iterable<Log> {
        return logRepository.findAll()
    }
}