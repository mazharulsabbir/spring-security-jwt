package com.mazharulsabbir.restapispring.data.controller

import com.mazharulsabbir.restapispring.data.model.ErrorResponse
import com.mazharulsabbir.restapispring.data.model.auth.LoginCredential
import com.mazharulsabbir.restapispring.data.model.auth.LoginResponse
import com.mazharulsabbir.restapispring.data.repository.AuthRepo
import com.mazharulsabbir.restapispring.service.MyUserDetailService
import com.mazharulsabbir.restapispring.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.jvm.Throws
import kotlin.math.log

private const val TAG = "AuthController"

@RestController
class AuthController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtUtil

    @Autowired
    private lateinit var authRepo: AuthRepo

    @RequestMapping(value = ["/auth"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody loginCredential: LoginCredential): ResponseEntity<*>? {

        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            loginCredential.username,
                            loginCredential.password
                    )
            )
        } catch (e: BadCredentialsException) {
            println("Login Failed: " + e.localizedMessage)
            return ResponseEntity.ok(ErrorResponse(true, e.message, e.localizedMessage))
        }

        val userDetails = MyUserDetailService().loadUserByUsername(loginCredential.username)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(LoginResponse(
                jwt,
                Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        )
    }
}