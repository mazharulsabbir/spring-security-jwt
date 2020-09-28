package com.mazharulsabbir.restapispring

import com.mazharulsabbir.restapispring.data.AuthenticationRequest
import com.mazharulsabbir.restapispring.data.AuthenticationResponse
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
import java.util.Collections.emptyList

@RestController
class ApiEndPoints {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtUtil

    @Autowired
    private lateinit var userDetailsService: MyUserDetailService

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*>? {
        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
            )
        } catch (e: BadCredentialsException) {
            throw Exception("Incorrect username or password", e)
        }
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }

    @RequestMapping("/users")
    fun users(): List<String> {
        return emptyList()
    }
}