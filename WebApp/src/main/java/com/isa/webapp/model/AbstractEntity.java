package com.isa.webapp.model;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
