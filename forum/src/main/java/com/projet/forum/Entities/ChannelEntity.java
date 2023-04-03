package com.projet.forum.Entities;

import java.util.LinkedList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    @Column
    private String title;

    @Column
    private Category category;

    @Column(columnDefinition = "boolean default false")
    private boolean archived;
/* 
    @OneToMany(mappedBy = "channel")
    private LinkedList<PostEntity> posts;*/
}
