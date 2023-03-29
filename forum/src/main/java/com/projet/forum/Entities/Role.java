package com.projet.forum.Entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{

    ADMIN(InCode.ADMIN), 
    MODERATOR(InCode.MODERATOR), 
    USER(InCode.USER);

    private final String name;

    Role(String name){ this.name = name; }

    public String getName(){ return name; }

    @Override public String getAuthority() {return getName();}

    public class InCode{

        public static final String ADMIN = "ROLE_ADMIN";
        public static final String MODERATOR = "ROLE_MODERATOR";
        public static final String USER = "ROLE_USER";
    }
}
