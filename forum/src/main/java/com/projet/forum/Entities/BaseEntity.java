package com.projet.forum.Entities;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    
    @CreatedDate
    protected LocalDate created_at;

    @LastModifiedDate
    protected LocalDate modified_at;
}
