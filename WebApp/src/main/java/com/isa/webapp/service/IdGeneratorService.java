package com.isa.webapp.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorService {
    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
