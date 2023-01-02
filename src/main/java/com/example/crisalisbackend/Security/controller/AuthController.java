package com.example.crisalisbackend.Security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.crisalisbackend.Security.dto.JwtDto;
import com.example.crisalisbackend.Security.dto.NewUser;
import com.example.crisalisbackend.Security.dto.UserLogin;
//import com.example.crisalisbackend.Security.entity.Rol;
import com.example.crisalisbackend.Security.entity.Usuario;
//import com.example.crisalisbackend.Security.enums.RolName;
import com.example.crisalisbackend.Security.jwt.JwtProvider;
//import com.example.crisalisbackend.Security.service.RolService;
import com.example.crisalisbackend.Security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    /*@Autowired
    RolService rolService;*/
    
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo (@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        System.out.println("hola");
        if (bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
            System.out.println("como va");
        if (userService.existByUserName(newUser.getUserName()))
            return new ResponseEntity<Message>(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
            System.out.println("sabia que");
        if (userService.existByEmail(newUser.getEmail()))
            return new ResponseEntity<Message>(new Message("Ese email ya está registrado"), HttpStatus.BAD_REQUEST);
            System.out.println("vendrias");
        Usuario user = new Usuario(newUser.getFullName(),newUser.getUserName(), 
            newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));
            System.out.println("por aca");
        
        //Set<Rol> roles = new HashSet<>();
        //roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        System.out.println("yo estoy");
        if(newUser.getRoles().contains("admin")) {
            //roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }
        //user.setRoles(roles);
        userService.save(user);
            System.out.println("muy bien"); 
        return new ResponseEntity<Message>(new Message("Usuario guardado"), HttpStatus.CREATED);
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        
        if(bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        
        JwtDto jwtDto = new JwtDto(jwt);
        
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }
    
}
