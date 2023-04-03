package com.projet.forum.Entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UserEntity extends BaseEntity implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String mail;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
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

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {

       /* 
        *  List<GrantedAuthority> authorities = new ArrayList<>();
        *  authorities.add(new SimpleGrantedAuthority(role.getName()));
        *
        *   return authorities;  
        */
        return Collections.singleton(new SimpleGrantedAuthority(getRole().getName()));
    }

    @Override public String getUsername() {

        //throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        
        return this.getUser_info().getLogin();
    }

    @Override public boolean isAccountNonExpired() {

        return true;
    }

    @Override public boolean isAccountNonLocked() {
 
        return !isArchived();
    }

    @Override public boolean isCredentialsNonExpired() {
   
        return true;
    }

    @Override public boolean isEnabled() {
 
        return true;
    }
    
}
