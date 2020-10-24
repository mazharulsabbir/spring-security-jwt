package com.mazharulsabbir.restapispring.data.controller

import com.mazharulsabbir.restapispring.data.model.ErrorResponse
import com.mazharulsabbir.restapispring.data.model.auth.AuthCredential
import com.mazharulsabbir.restapispring.data.model.auth.AuthResponse
import com.mazharulsabbir.restapispring.data.repository.AuthRepo
import com.mazharulsabbir.restapispring.service.MyUserDetailService
import com.mazharulsabbir.restapispring.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.jvm.Throws

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

    @PostMapping("/register")
    fun createNewCredential(authCredential: AuthCredential): Int {
        val encodePassword = jwtTokenUtil.passwordEncoder().encode(authCredential.password)
        val credential = AuthCredential(
                authCredential.id,
                authCredential.username,
                encodePassword,
                authCredential.role
        )
        return authRepo.save(credential).id
    }
}