package com.mazharulsabbir.restapispring.service;

import com.mazharulsabbir.restapispring.data.model.auth.AuthCredential;
import com.mazharulsabbir.restapispring.data.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Transactional
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthCredential credential = null;
        try {
            credential = authRepo.findByUsername(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        if (credential != null) {
            if (credential.getUsername() != null && credential.getPassword() != null) {
                System.out.println("====Login Successful====");
                return new User(credential.getUsername(), credential.getPassword(), new ArrayList<>());
            } else return new User(" "," ", new ArrayList<>());
        } else return new User(" "," ", new ArrayList<>());
    }
}
