package com.mazharulsabbir.restapispring.data.controller

import com.mazharulsabbir.restapispring.data.model.ErrorResponse
import com.mazharulsabbir.restapispring.data.model.auth.AuthCredential
import com.mazharulsabbir.restapispring.data.model.auth.AuthResponse
import com.mazharulsabbir.restapispring.data.repository.AuthRepo
import com.mazharulsabbir.restapispring.service.MyUserDetailService
import com.mazharulsabbir.restapispring.utils.JwtUtil
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.sql.SQLIntegrityConstraintViolationException
import java.util.*
import kotlin.jvm.Throws

private const val TAG = "AuthController"

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtUtil

    @Autowired
    private lateinit var authRepo: AuthRepo

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authCredential: AuthCredential): ResponseEntity<*>? {

        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            authCredential.username,
                            authCredential.password
                    )
            )
        } catch (e: BadCredentialsException) {
            println("Login Failed: " + e.localizedMessage)
            return ResponseEntity.ok(ErrorResponse(true, e.message, e.localizedMessage))
        }

        val userDetails = MyUserDetailService().loadUserByUsername(authCredential.username)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthResponse(
                jwt,
                Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        )
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun createNewCredential(authCredential: AuthCredential): Any {
        val encodePassword = jwtTokenUtil.passwordEncoder()
                .encode(authCredential.password)

        val credential = AuthCredential(
                authCredential.id,
                authCredential.username,
                encodePassword,
                authCredential.role
        )

        val response: AuthCredential
        try {
            response = authRepo.save(credential)
            response.password = "SECRET_PASSWORD"
        } catch (e: Exception) {
            return ErrorResponse(true, "Failed to create account", e.localizedMessage)
        }
        return response
    }
}