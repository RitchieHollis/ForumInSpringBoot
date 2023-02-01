package com.projet.forum.Entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

public abstract class BaseEntity {
    
    protected CreatedDate created_at;

    protected LocalDateTime modified_at;
}
