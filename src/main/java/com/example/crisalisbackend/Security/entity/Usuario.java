package com.example.crisalisbackend.Security.entity;


//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import javax.persistence.OneToOne;
//import com.example.crisalisbackend.model.Person;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    
    //private Set<Rol> roles = new HashSet<>();

    //@JsonManagedReference
    //@OneToOne(mappedBy="user", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    //private Person person; //preguntar a dani si esto agrega un user a cada persona
    

    public Usuario() {
    }

    public Usuario(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setNameUser(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }*/

    /*public void addPerfil(Person person) {
        // perfil.add(perfil);
        person.setUser(this); //preguntar a dani si esto agrega user a cada persona
     } */
}
