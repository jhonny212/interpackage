package com.interpackage.users.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "internal_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_user")
    private Long idUser;

    @Column (nullable = false, length = 75, unique = true)
    private String name;

    @Column (nullable = false, length = 75)
    private String dpi;

    @Column (nullable = false, length = 500)
    private String address;

    @Column (nullable = false, length = 75)
    private String phone;

    @Column (nullable = false, length = 75, unique = true)
    private String email;

    @Column (nullable = false, length = 250)
    private String password;

    @ManyToOne
    @JoinColumn (name = "id_role", nullable = false)
    private Role role;

    public void merge(User usr){
        dpi = usr.dpi;
        address = usr.address;
        phone = usr.phone;
        email = usr.email;
        role = usr.role;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDpi() {
        return this.dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
