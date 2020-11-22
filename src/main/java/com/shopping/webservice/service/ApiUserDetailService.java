package com.shopping.webservice.service;

import com.shopping.webservice.entity.UserEntity;
import com.shopping.webservice.repository.UserRepository;
import com.shopping.webservice.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ApiUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserEntity userDetails = userRepository.
                findByEmailIgnoreCase(username).orElseThrow(()-> new UsernameNotFoundException("Invalid User Details"));

        return JwtUserDetails.create(userDetails);
    }
}
