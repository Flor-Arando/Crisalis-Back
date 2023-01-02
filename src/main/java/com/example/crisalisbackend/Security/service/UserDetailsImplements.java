package com.example.crisalisbackend.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.Security.entity.MainUser;
import com.example.crisalisbackend.Security.entity.Usuario;

@Service
public class UserDetailsImplements implements UserDetailsService{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        Usuario user = userService.getByUserName(userName).get();

        return MainUser.build(user);
    }

}
