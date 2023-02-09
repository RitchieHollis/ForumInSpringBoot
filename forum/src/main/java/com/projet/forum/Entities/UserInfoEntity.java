package com.projet.forum.Entities;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserInfoEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_info_id")
    private Long id;

    @Column
    private String login;

    private String format = "png"; //for profile_picture
    @Column(nullable = true)
    private byte[] profile_picture;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private Date age;

    @Column(nullable = true)
    private List<Badge> badges;

    @Column
    private Status status;

    @Column(nullable = true)
    private List<String> warnings;

    //@Column
    //private List<Log> log_users;
    //import lombok.extern.java.Log;

}
