package com.example.crisalisbackend.Security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.Security.entity.Usuario;
import com.example.crisalisbackend.Security.repository.UsersRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    //@Autowired
    //IPersona iPersona;


    public Optional<Usuario> getByUserName(String userName){
        return usersRepository.findByUserName(userName);
    }
    
    public boolean existByUserName(String userName){
        return usersRepository.existsByUserName(userName);
    }
    
    public boolean existByEmail(String email){
        return usersRepository.existsByEmail(email);
    }
    
    public void save(Usuario user){
        Usuario newUser = user;
       usersRepository.save(newUser);

       /*User newUser = user;
       Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(nuevoUsuario.getNombre());
       nuevoUsuario.addPerfil(nuevaPersona);
       usuarioRepository.save(nuevoUsuario);
       iPersona.save(nuevaPersona);
    }
    
    public void delete(String nombreUsuario){
        UsuarioLogin usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
        //Long id = usuario.getId();
        usuarioRepository.delete(usuario); el usuario no necesita borrar, solo dar el alta de registracion
    }*/

    }
    
}
