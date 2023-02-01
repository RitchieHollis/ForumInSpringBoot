package com.projet.forum.Entities;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String format; //for profile_picture
    @Column
    private byte[] profile_picture;

    @Column
    private String bio;

    @Column
    private Date age;

    @Column
    private List<Badge> badges;

    @Column
    private Status status;

    @Column
    private List<String> warnings;

    //@Column
    //private List<Log> log_users;
    //import lombok.extern.java.Log;

}
