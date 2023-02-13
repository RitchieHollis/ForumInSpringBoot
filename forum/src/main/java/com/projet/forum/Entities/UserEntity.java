package com.projet.forum.Entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String mail;

    @Column
    private String password;

    @Column
    private Role role;

    @Column(columnDefinition = "boolean default false")
    private boolean archived;

    @OneToOne
    private UserInfoEntity user_info;

    @Column(nullable = true)
    private List<Category> user_category;
    
    public boolean equals(UserEntity u){

        if(u instanceof UserEntity && this.getId() == u.getId())
            return true;

        if(this.getMail().equals(u.getMail())                   &&
           this.getPassword().equals(u.getPassword())           &&
           this.getRole().equals(u.getRole())                   &&
           this.getUser_category().equals(u.getUser_category()) &&
           this.getUser_info().equals(u.getUser_info()))
           return true;
        
        return false;
    }
    
}
