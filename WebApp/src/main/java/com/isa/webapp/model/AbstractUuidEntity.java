package com.isa.webapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractUuidEntity extends AbstractEntity {

    @Column(name = "uuid", unique = true, nullable = false)
    private final String uuid = UUID.randomUUID().toString();
}
